/**
 * Class OrderedsAddServlet
 * - doGet : redirect to books
 * - doPost : add a book with a quantity
 * @author Philippot Grégoire
 */
package car.tp4.servlet.ordereds;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.managers.ManagerOrdereds;

@WebServlet("/ordereds-add")
public class OrderedsAddServlet extends HttpServlet {

	private static final long serialVersionUID = -3112795519109033639L;

	public OrderedsAddServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/books");		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		int id = Integer.valueOf(request.getParameter("id"));
		this.getOrdereds(request).addPurchase(id, quantity);
		response.sendRedirect("/ordereds");
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
