/**
 * Class AuthorFormServlet
 * - doGet : if the id attribute is not null allows to edit an existing author otherwise allows to create a new author
 * - doPost : creates a new author and then returns the user to the list of existing authors
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

@WebServlet("/author-form")
public class AuthorFormServlet extends HttpServlet {

	private static final long serialVersionUID = -9168534145039959161L;
	
	@EJB
	private AuthorBean authorBean;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Author author = null;
		if(request.getParameter("id") == null) {
			author = new Author();
			author.setFirstname("");
			author.setLastname("");
		}else {
			int id = Integer.valueOf(request.getParameter("id"));
			author = this.authorBean.findAuthorById(id);
		}
		if(author == null) {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Author not found");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}else {
			request.setAttribute("author", author);
			this.getServletContext().getRequestDispatcher("/jsp/author-form.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fistname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		int id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : -1;
		if(this.authorBean.createOrUpdateAnAuthor(id, fistname, lastname)) {
			response.sendRedirect("/authors");
		}else {
			request.setAttribute("status", 400);
			request.setAttribute("error", "Vous n'avez pas renseigné de firstname ou de lastname");
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}
}
