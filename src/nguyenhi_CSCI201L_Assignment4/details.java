package nguyenhi_CSCI201L_Assignment4;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class detais
 */
@WebServlet("/detais")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer i = Integer.parseInt(request.getParameter("index"));
		
		PrintWriter out = response.getWriter();
		Restaurant restaurant = search.restaurants.get(i);
		
		out.println("<div class=\"row\">\n" + 
				
				"<br><h4 id=\"rname\" class=\" borderless\" >" + restaurant.getName() +"</h4>" + 
				
				"</div>" +
				"<br><div class=\"row\"  name =\"restaurant" +i+ '"'+">\n" + 
				"      <div class=\"col-4\" >\n" + 
				"        <!-- content for column 1 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
				"        \n" + 
				"      <a id =\"yelplink\" href="+restaurant.getYelpLink()+"><img id = \"imglink\" class=\"form-control rounded-corners\" src=" + restaurant.getImgLink() +"  /> </a>" +
				"        \n" + 
				"      </div>\n" + 
				"      <div class=\"col-8\" >\n" + 
				"        <!-- content for column 2 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
				"        \n" + 
				
				"         <h6 id=\"address\" class=\" borderless\" >Adress: " + restaurant.getAddress() +"</h6>" + 
				"         <h6 id=\"phone\" class=\" borderless\" >Phone No: " + restaurant.getPhoneNum() +"</h6>" + 
				"         <h6 id=\"cuisine\" class=\" borderless\" >Cuisine: " + restaurant.getCuisine() +"</h6>" + 
				"         <h6 id=\"price\" class=\" borderless\" >Price: " + restaurant.getPrice() +"</h6>" + 
				"         <h6>Rating: <span id='stars'>"+restaurant.getRating()+"</span>"+
				"</h6>" + 

				"      </div>\n" + 
				"\n" + 
				"    </div> <br>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
