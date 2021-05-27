package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;

/**
 * Servlet implementation class favoritesservlet
 */
@WebServlet("/favoritesservlet")
public class favoritesservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Restaurant> favRestaurants;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		System.out.println(username);
		JDBCTest.initConnection();
		favRestaurants = JDBCTest.getFavorites(username);
		
		if (favRestaurants.size() > 0) {
			//out.println("<br><h2 style='color: gray'>" +username+  "'s Favorites:<h2><br>");
		}
		
		

		for (int i = 0; i < favRestaurants.size();i++){// Username or password are empty
			out.println("<br><div class=\"row\"  name =\"restaurant" +i+ '"'+">\n" + 
					"      <div class=\"col-4\" >\n" + 
					"        <!-- content for column 1 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
					"        \n" + 
					"      <a  onclick=\"getdetails("+i+")\" ><img name = \"image\" class=\"form-control rounded-corners\" src=" + favRestaurants.get(i).getImgLink() +"  /> </a>" +
					"        \n" + 
					"      </div>\n" + 
					"      <div class=\"col-8\" >\n" + 
					"        <!-- content for column 2 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
					"        \n" + 
					"         <h4 name=\"name"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getName() +"</h6>" + 
					"         <h6 name=\"address"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getAddress() +"</h6>" + 
					"         <h6 name=\"url"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getYelpLink() +"</h6>" + 
					"         <h6 name=\"phone"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getPhoneNum() +"</h6>" + 
					"         <h6 name=\"cuisine"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getCuisine() +"</h6>" + 
					"         <h6 name=\"price"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getPrice() +"</h6>" + 
					"         <h6 name=\"rating"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getRating() +"</h6>" + 
					"      </div>\n" + 
					"\n" + 
					"    </div> <br><hr class=\"new2\">");
		}

	
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		String username = request.getParameter("username");
		
		
		JDBCTest.initConnection();
		if(favRestaurants.size()> 0) {
			if (command.equals("notrecent")) {
				
			}else if(command.equals("recent")) {
				
			}else if (command.equals("atoz")) {
				Collections.sort(favRestaurants, compareByName);
			}else if (command.equals("ztoa")) {
				Collections.sort(favRestaurants, compareByName.reversed());
			}else if (command.equals("highrank")) {
				Collections.sort(favRestaurants, compareByRank);
			}else if (command.equals("lowrank")) {
				Collections.sort(favRestaurants, compareByRank.reversed());
			}
			
			for (int i = 0; i < favRestaurants.size();i++){// Username or password are empty
				out.println("<br><div class=\"row\"  name =\"restaurant" +i+ '"'+">\n" + 
						"      <div class=\"col-4\" >\n" + 
						"        <!-- content for column 1 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
						"        \n" + 
						"      <a  onclick=\"getdetails("+i+")\" ><img name = \"image\" class=\"form-control rounded-corners\" src=" + favRestaurants.get(i).getImgLink() +"  /> </a>" +
						"        \n" + 
						"      </div>\n" + 
						"      <div class=\"col-8\" >\n" + 
						"        <!-- content for column 2 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
						"        \n" + 
						"         <h4 name=\"name"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getName() +"</h6>" + 
						"         <h6 name=\"address"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getAddress() +"</h6>" + 
						"         <h6 name=\"url"+i+"\" class=\" borderless\" >" + favRestaurants.get(i).getYelpLink() +"</h6>" + 
						"         <h6 name=\"phone"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getPhoneNum() +"</h6>" + 
						"         <h6 name=\"cuisine"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getCuisine() +"</h6>" + 
						"         <h6 name=\"price"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getPrice() +"</h6>" + 
						"         <h6 name=\"rating"+i+"\" class=\" borderless\" hidden>" + favRestaurants.get(i).getRating() +"</h6>" + 
						"      </div>\n" + 
						"\n" + 
						"    </div> <br><hr class=\"new2\">");
			}

			
		}

		
	
	}
	Comparator<Restaurant> compareByName = new Comparator<Restaurant>() {
	    @Override
	    public int compare(Restaurant o1, Restaurant o2) {
	        return o1.getName().compareTo(o2.getName());
	    }
	};
	Comparator<Restaurant> compareByRank = new Comparator<Restaurant>() {
	    @Override
	    public int compare(Restaurant o1, Restaurant o2) {
	        return o1.getRating().compareTo(o2.getRating());
	    }
	};

}
