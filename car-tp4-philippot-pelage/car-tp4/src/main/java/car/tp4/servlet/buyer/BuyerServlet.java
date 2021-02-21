/**
 * Class InitServlet
 * - doGet : displays the items of a buyer if the id attribute is valide
 * - doPost : send an email to the buyer
 * @author Philippot Grégoire
 */
package car.tp4.servlet.buyer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Buyer;
import car.tp4.entity.bean.BuyerBean;
import car.tp4.entity.bean.OrdersBean;
import car.tp4.managers.ManagerOrdereds;

@WebServlet("/buyer")
public class BuyerServlet extends HttpServlet {

	private static final long serialVersionUID = -7683154992424010286L;

	@EJB
	private OrdersBean ordersBean;
	
	@EJB
	private BuyerBean buyerBean;
	
	public BuyerServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			request.setAttribute("ordereds", this.buyerBean.getAllOrders());
			this.getServletContext().getRequestDispatcher("/jsp/ordereds.jsp").forward(request, response);
		}else {
			int id = Integer.valueOf(request.getParameter("id"));
			Buyer buyer = this.buyerBean.findBuyerById(id);
			request.setAttribute("buyer", buyer);
			request.setAttribute("orders", this.ordersBean.findOrdersByBuyer(buyer));
			this.getServletContext().getRequestDispatcher("/jsp/ordered.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Buyer buyer = this.getOrdereds(request).confirmPurchase(email);
		if(buyer == null) {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Un livre dans votre panier n'est plsu en stock. Revenez un autre jour");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}else {
			request.getSession().removeAttribute(ManagerOrdereds.SESSION_KEY);
			request.setAttribute("buyer", buyer);
			request.setAttribute("orders", this.ordersBean.findOrdersByBuyer(buyer));
			this.getServletContext().getRequestDispatcher("/jsp/ordered.jsp").forward(request, response);
		}
	}
	
	/**
	 * Get a manager orders to make action 
	 * @param request request
	 * @return orders
	 * @throws ServletException exception
	 */
	private ManagerOrdereds getOrdereds(HttpServletRequest request) throws ServletException{
		ManagerOrdereds ordereds = (ManagerOrdereds) request.getSession().getAttribute(ManagerOrdereds.SESSION_KEY);
		if(ordereds == null) {
			try {
				InitialContext cont = new InitialContext();
				ordereds = (ManagerOrdereds) cont.lookup("java:global//ManagerOrdereds");
				request.getSession().setAttribute(ManagerOrdereds.SESSION_KEY, ordereds);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return ordereds;
	}
}
