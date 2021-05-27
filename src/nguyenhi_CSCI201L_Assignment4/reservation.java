package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reservation
 */
@WebServlet("/reservation")
public class reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String restaurant = request.getParameter("restaurant");

		PrintWriter out = response.getWriter();
		JDBCTest.initConnection();
		
		
		if (!JDBCTest.isgglogin(username).equals("yes") || JDBCTest.isgglogin(username) == null) {
			out.println("<span style='color: #990000'>- Only google login account can make reservation!<span>");
		}else {
			out.println("		<div class=\"row\">\n" + 
					"		\n" + 
					"			<div class=\"col-6\">\n" + 
					"				<input type=\"date\" placeholder=\"Date\" class=\"form-control\"></input>\n" + 
					"			</div>\n" + 
					"			<div class=\"col-6\">\n" + 
					"				<input type=\"time\" placeholder=\"Time\" class=\"form-control\"></input>\n" + 
					"			</div>\n" + 
					"<div class=\"row\">\n" + 
					"</div>\n" + 
					"		\n" + 
					"\n" + 
					"			<div class=\"col-12\">\n" + 
					"			<textarea name=\"message\" class=\"form-control\">Reservation Notes\n" + 
					"			</textarea>\n" + 
					"			\n" + 
					"			\n" + 
					"			</div>\n" + 
					"\n" + 
					"\n" + 
					"</div>\n" + 
					"		\n" + 
					"			<div class=\"row\">\n" + 
					"			<div class=\"col-12\">\n" + 
					"        <button  type=\"submit\" class=\"btn  form-control\" id=\"myBtn\" style=\"background-color:#990000;color:white\"><i class=\"fa fa-calendar-plus-o\" aria-hidden=\"true\" style=\"color: white;\"></i> Submit Reservation</button>\n" + 
					"\n" + 
					"			\n" + 
					"			</div>\n" + 
					"	\n" + 
					"			\n" + 
					"			\n" + 
					"			\n" + 
					"			\n" + 
					"			</div>");		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
