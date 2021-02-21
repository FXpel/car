/**
 * Class InitServlet
 * - doGet : searches for books with a word in their title containing the valid title attribute
 * - doPost : doGet
 * @author Philippot Grégoire
 */
package car.tp4.servlet.book;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.bean.BookBean;

@WebServlet("/book-title")
public class SearchBookWithTitleServlet extends HttpServlet {

	private static final long serialVersionUID = -7258184039118276300L;
	
	@EJB
	private BookBean bookBean;
	
	public SearchBookWithTitleServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		List<Book> books = null;
		boolean asc = (request.getParameter("asc") != null) ? Boolean.parseBoolean(request.getParameter("asc")) : false;
		boolean desc = (request.getParameter("desc") != null) ? Boolean.parseBoolean(request.getParameter("desc")) : false;
		if(asc && !desc) {
			books = this.bookBean.findBooksByTitleByASCYear(title);
		}else if(!asc && desc) {
			books = this.bookBean.findBooksByTitleByDESCYear(title);
		}else{
			books = this.bookBean.findBooksByTitle(title);
		}
		if(books == null) {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Title missing !");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}else {
			request.setAttribute("title", title);
			request.setAttribute("books", books);
			this.getServletContext().getRequestDispatcher("/jsp/book-title.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
