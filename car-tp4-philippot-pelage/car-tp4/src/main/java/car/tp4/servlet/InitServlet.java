/**
 * Class InitServlet
 * - doGet : doPost
 * - doPost : initialize the project and send the user to BookServlet
 * @author Philippot Grégoire
 */
package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.managers.ProjectManager;

@WebServlet("/init")
public class InitServlet extends HttpServlet{

	private static final long serialVersionUID = 2224472225241990432L;

	@EJB
	private ProjectManager projectManager;
	
	public InitServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.projectManager.initProject();
		response.sendRedirect("/books");
	}
}
