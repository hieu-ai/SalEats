package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signupServlet
 */
@WebServlet("/signup")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("signupEmail");
		String username = request.getParameter("signupUsername");
		String pw = request.getParameter("signupPassword");
		String confirmPw = request.getParameter("signupConfirmPassword");
		String agree = request.getParameter("agreeBox");
		
		PrintWriter out = response.getWriter();
		JDBCTest.initConnection();
		
		// Check if email in format
		// Check if email is exist
		// Check if data is missing
		// check if password is match
		// check if username is exist
		
		
		
		if(email == null || username == null || pw == null || confirmPw == null || agree == null ||
				email.trim().length() == 0 || username.trim().length() == 0 || pw.trim().length() == 0 ||
				confirmPw.trim().length() ==0 || agree.trim().length() == 0) {
			out.println("<span style='color: #990000'>- Some of the field are empty<span>");
		}
		
		else if(!isValid(email)) {
			out.println("<span style='color: #990000'>- email is not in a correct format<span>");
		}
		else if(JDBCTest.emailExist(email)) {
			out.println("<span style='color: #990000'>- email is already exist<span>");
		}
		else if (!pw.equals(confirmPw)) {
			out.println("<span style='color: #990000'>- passwords not match<span>");
		}
		else if (JDBCTest.usernameExist(username)) {
			out.println("<span style='color: #990000'>- username is already exist<span>");
		}
		else if(!agree.equals("true")){
			out.println("<span style='color: #990000'>- Please check agree term box <span>");
		}else {
			JDBCTest.addUser(username, email, pw, "");
			out.write("success");
		}
		
		//out.flush();
		/*
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
			
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public static boolean isValid(String email) 
	    { 
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
	                            "[a-zA-Z0-9_+&*-]+)*@" + 
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
	                            "A-Z]{2,7}$"; 
	                              
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (email == null) 
	            return false; 
	        return pat.matcher(email).matches(); 
	    } 
}
