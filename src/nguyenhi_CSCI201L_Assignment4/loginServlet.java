package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("loginUsername");
		String pw = request.getParameter("loginPassword");
		
		PrintWriter out = response.getWriter();
		JDBCTest.initConnection();
		//out.flush();
		
		if(username == null || username.trim().length() == 0 || pw == null || pw.trim().length() == 0) {// Username or password are empty
			out.println("<span style='color: #990000'>Username or Password are empty<span>");
		}
		else if(!JDBCTest.usernameExist(username)) { // Username does not exist
			out.println("<span style='color: #990000'>Username does not exist<span>");
		}
		else if(!JDBCTest.matchPassword(username, pw)) { // Username and password does not match
				out.println("<span style='color: #990000'>Wrong Password! Please try Again<span>");
		}
		
		else if(JDBCTest.matchPassword(username, pw)) { // Username and password does not match
			//response.sendRedirect("home.html");
			//out.println("<span style='color: #990000'>Sucess<span>");
			out.write("success");
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
