/**
 * Class InitServlet
 * - doGet : remove quantity from a book
 * - doPost : doGet
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

@WebServlet("/ordereds-remove")
public class OrderedsRemoveServlet extends HttpServlet {

	private static final long serialVersionUID = 7358945201585168923L;

	public OrderedsRemoveServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		ManagerOrdereds ordereds = this.getOrdereds(request);
		if(ordereds.removePurchase(id, quantity)) {
			response.sendRedirect("/ordereds");
		}else {
			request.setAttribute("status", 404);
			request.setAttribute("error", "item to remove isn't find !");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);	
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
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
