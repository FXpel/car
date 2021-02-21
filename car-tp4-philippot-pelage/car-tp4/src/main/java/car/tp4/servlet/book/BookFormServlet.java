/**
 * Class BookFormServlet
 * - doGet : if the id attribute is not null allows to edit an existing book otherwise allows to create a new book
 * - doPost : creates a new book and then returns the user to the list of existing books
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

import car.tp4.entity.Author;
import car.tp4.entity.Book;
import car.tp4.entity.bean.AuthorBean;
import car.tp4.entity.bean.BookBean;
import car.tp4.managers.ProjectManager;

@WebServlet("/book-form")
public class BookFormServlet extends HttpServlet {

	private static final long serialVersionUID = 5139856186694244370L;

	@EJB
	private BookBean bookBean;
	@EJB
	private AuthorBean authorBean;
	@EJB
	private ProjectManager projectManager;
	
	public BookFormServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Author> authors = this.authorBean.getAllAuthors();
		request.setAttribute("authors", authors);
		Book book = null;
		if(request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			book = this.bookBean.findABookById(id);
			if(book == null) {
				book = new Book();
				book.setTitle("");
				book.setQuantity(0);
			}
		}else {
			book = new Book();
			book.setTitle("");
			book.setQuantity(0);
		}
		request.setAttribute("book", book);
		this.getServletContext().getRequestDispatcher("/jsp/book-form.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		int authorID = Integer.valueOf(request.getParameter("author"));
		int bookID = Integer.valueOf(request.getParameter("id"));
		int year = Integer.valueOf(request.getParameter("year"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		if(this.projectManager.createOrUpdateABook(bookID, authorID, title, year, quantity)) {
			response.sendRedirect("/books");
		}else {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Book is not valid.");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}
}
