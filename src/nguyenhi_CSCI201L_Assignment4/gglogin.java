package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ggsignin
 */
@WebServlet("/gglogin")
public class gglogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("loginUsername");
		String email = request.getParameter("loginEmail");
		System.out.print("hi " + username);
		
		JDBCTest.initConnection();
		//out.flush();
		

		if(!JDBCTest.usernameExist(username)) { // Username does not exist
			JDBCTest.addUser(username, email, "123", "yes");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
