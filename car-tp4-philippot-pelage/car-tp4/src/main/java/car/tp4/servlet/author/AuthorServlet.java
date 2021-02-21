/**
 * Class AuthorServlet
 * - doGet : display the authors or return the user to an author
 * - doPost : doGet
 * @author Philippot Grégoire
 */
package car.tp4.servlet.author;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Author;
import car.tp4.entity.bean.AuthorBean;
import car.tp4.entity.bean.BookBean;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

	private static final long serialVersionUID = 271173460567602473L;

	@EJB
	private AuthorBean authorBean;
	@EJB
	private BookBean bookBean;
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			request.setAttribute("authors", this.authorBean.getAllAuthors());
			this.getServletContext().getRequestDispatcher("/jsp/authors.jsp").forward(request, response);
		}else {
			int id = Integer.valueOf(request.getParameter("id"));
			Author author = this.authorBean.findAuthorById(id);
			request.setAttribute("author", author);
			boolean asc = (request.getParameter("asc") != null) ? Boolean.parseBoolean(request.getParameter("asc")) : false;
			boolean desc = (request.getParameter("desc") != null) ? Boolean.parseBoolean(request.getParameter("desc")) : false;
			if(asc && !desc) {
				request.setAttribute("books", this.bookBean.findBooksByAuthorByASCYear(author));
			}else if(!asc && desc) {
				request.setAttribute("books", this.bookBean.findBooksByAuthorByDESCYear(author));
			}else{
				request.setAttribute("books", this.bookBean.findBooksByAuthor(author));
			}
			this.getServletContext().getRequestDispatcher("/jsp/author.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
