/**
 * Class InitServlet
 * - doGet : display orders
 * - doPost : doGet
 * @author Philippot Grégoire
 */
package car.tp4.servlet.ordereds;

import java.io.IOException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.managers.ManagerOrdereds;

@WebServlet("/ordereds")
public class OrderedsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4438572724571577830L;

	public OrderedsServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Book, Integer> ordereds = (Map<Book, Integer>) this.getOrdereds(request).getOrdereds();
		request.setAttribute("ordereds", ordereds);
		this.getServletContext().getRequestDispatcher("/jsp/ordereds.jsp").forward(request, response);
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
