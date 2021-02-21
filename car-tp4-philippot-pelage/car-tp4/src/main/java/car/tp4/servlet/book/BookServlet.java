/**
 * Class BookServlet
 * - doGet : display the books
 * @author Philippot Grégoire
 */
package car.tp4.servlet.book;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.bean.BookBean;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = -936468174575482665L;
	
	@EJB
	private BookBean bookBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean asc = (request.getParameter("asc") != null) ? Boolean.parseBoolean(request.getParameter("asc")) : false;
		boolean desc = (request.getParameter("desc") != null) ? Boolean.parseBoolean(request.getParameter("desc")) : false;
		if(asc && !desc) {
			request.setAttribute("books", this.bookBean.getAllBooksByASCYear());
		}else if(!asc && desc) {
			request.setAttribute("books", this.bookBean.getAllBooksByDESCYear());
		}else{
			request.setAttribute("books", this.bookBean.getAllBooks());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
		dispatcher.forward(request, response);
	}
}
