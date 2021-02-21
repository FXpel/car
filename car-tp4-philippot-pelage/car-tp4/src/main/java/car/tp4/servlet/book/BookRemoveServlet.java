/**
 * Class BookRemoveServlet
 * - doGet : delete a book if the id attribute is valid
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

@WebServlet("/book-remove")
public class BookRemoveServlet extends HttpServlet {

	private static final long serialVersionUID = 5014490603551863453L;
	
	@EJB
	private BookBean bookBean;
	
	public BookRemoveServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			request.setAttribute("status", 400);
			request.setAttribute("error", "No book to remove provided.");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}else {
			int id = Integer.valueOf(request.getParameter("id"));
			this.bookBean.removeBook(id);
			request.setAttribute("books", this.bookBean.getAllBooks());
			this.getServletContext().getRequestDispatcher("/jsp/book.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
