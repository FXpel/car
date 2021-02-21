/**
 * Class InitServlet
 * - doGet : displays information from a book if the attribute id is valid
 * - doPost : doGet
 * @author Philippot Grégoire
 */
package car.tp4.servlet.book;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.bean.BookBean;

@WebServlet("/book-recap")
public class BookRecapServlet extends HttpServlet {

	private static final long serialVersionUID = 9032020744395492056L;

	@EJB
	private BookBean bookBean;
	
	public BookRecapServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : -1;
		if(id == -1) {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Book cannot be find.");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}else {
			request.setAttribute("book", this.bookBean.findABookById(id));
			this.getServletContext().getRequestDispatcher("/jsp/book-recap.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
