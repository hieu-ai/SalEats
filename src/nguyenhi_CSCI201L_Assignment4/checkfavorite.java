package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.PrimitiveIterator.OfDouble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.webkit.ThemeClient;

/**
 * Servlet implementation class checkfavorite
 */
@WebServlet("/checkfavorite")
public class checkfavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String restaurant = request.getParameter("restaurant");
		PrintWriter out = response.getWriter();
		JDBCTest.initConnection();
		if (JDBCTest.favoriteExist(username, restaurant)) {
			out.println("<button name=\"favoriteaction\" value=\"add\" type=\"submit\" onclick =\"removefavorite()\" class=\"btn  form-control\" id=\"myBtn\" style=\"background-color:#FFCC00;\"><i class=\"fa fa-star\" aria-hidden=\"true\" style=\"color: gray;\"></i> Remove from Favorites</button>\n");
		}else {
			out.println("<button name=\"favoriteaction\" value=\"remove\" type=\"submit\" onclick =\"addfavorite()\" class=\"btn  form-control\" id=\"myBtn\" style=\"background-color:#FFCC00;\"><i class=\"fa fa-star\" aria-hidden=\"true\" style=\"color: gray;\"></i>Add to Favorites </button>\n");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String username = request.getParameter("username");
		String restaurant = request.getParameter("restaurant");
		System.out.println(username+restaurant +command);
		PrintWriter out = response.getWriter();
		JDBCTest.initConnection();
		
		if (command.equals("add") && username.trim().length() != 0) {
			
			String imglink = request.getParameter("imglink");
			String address = request.getParameter("address");
			String yelplink = request.getParameter("yelplink");
			String phone = request.getParameter("phone");
			String cuisine = request.getParameter("cuisine");
			String price = request.getParameter("price");
			String rating = request.getParameter("rating");
			System.out.println("Check value" + restaurant + imglink +address);
			
			JDBCTest.addFavorite(username, restaurant, imglink, address, yelplink, phone, cuisine, price, rating);
			out.println("<button name=\"favoriteaction\" value=\"add\" type=\"submit\" onclick =\"removefavorite()\" class=\"btn  form-control\" id=\"myBtn\" style=\"background-color:#FFCC00;\"><i class=\"fa fa-star\" aria-hidden=\"true\" style=\"color: gray;\"></i> Remove from Favorites</button>\n");
		} else if(command.equals("remove") && username.trim().length() !=0) {
			JDBCTest.removeFavorite(username, restaurant);
			out.println("<button name=\"favoriteaction\" value=\"remove\" type=\"submit\" onclick =\"addfavorite()\" class=\"btn  form-control\" id=\"myBtn\" style=\"background-color:#FFCC00;\"><i class=\"fa fa-star\" aria-hidden=\"true\" style=\"color: gray;\"></i>Add to Favorites </button>\n");
		}
	}
}
