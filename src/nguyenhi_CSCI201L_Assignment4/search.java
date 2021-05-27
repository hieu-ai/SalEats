package nguyenhi_CSCI201L_Assignment4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	private static String searchName = "";
	
	
	
	private static final String API_KEY = "6Ejia9fbdOjaa0aioZhesWRYNKK-7ZwTlIsbFHdozzvGxp9dwT_gtRO33PzWs9KK5Uq21WDb-RqcHibWMlbwpAnjxejZAom-3bZxDZ9aWpI73SEcqbf4MEp9uWOAXnYx";

    
	
	private static String buildURL(String name, double latitude, double longitude, String sort_by) {

    	// Handling special cases for the Yelp API
    	name = name.replaceAll("\\s+", "-");
		name = name.replaceAll(" ", "-");
		name = name.replaceAll("’", "'");

        return "https://api.yelp.com/v3/businesses/search?" +
                "term=" + name +
                "&latitude=" + latitude +
                "&longitude=" + longitude +
                "&sort_by=" + sort_by + 
                "&categories=restaurants";
    }

    private static String getRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization","Bearer " + API_KEY);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return in.readLine();
    }

    public static ArrayList<Restaurant> getRestaurants(String name, double latitude, double longitude, String sort_by) throws IOException, ParseException {
        ArrayList<Restaurant> result = restaurants;
    	
    	// Get JSON string
        String jsonString = getRequest(buildURL(name, latitude, longitude, sort_by));

        // Parse the JSON using JSON.Simple
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) ((JSONObject) parser.parse(jsonString)).get("businesses");

        // Go through array of restaurants and pick the first match
        for (Object restaurant: data) {
            String restaurantName = (String) ((JSONObject) restaurant).get("name");

            // If we have a match, grab the location data and return a Restaurant object
            /*
            if(restaurantName.toLowerCase().contains(name.toLowerCase())) {
                JSONObject coordinates = (JSONObject) ((JSONObject) restaurant).get("coordinates");
                double restaurantLat = (double) coordinates.get("latitude");
                double restaurantLong = (double) coordinates.get("longitude");

                return new Restaurant(name, restaurantLat, restaurantLong);
            }*/
            String restaurantImg = (String) ((JSONObject) restaurant).get("image_url");
            String restaurantAdress = "";
            JSONObject location = (JSONObject) ((JSONObject)restaurant).get("location"); 
            restaurantAdress = (String) location.get("address1") + ", " + 
            					(String) location.get("city") + " " +
            					(String) location.get("state") + " " +
            					(String) location.get("zip_code");
            
            String yelpLink = (String) ((JSONObject) restaurant).get("url");
            yelpLink = yelpLink.substring(0, yelpLink.indexOf("?"));
            
            String phoneNum = (String) ((JSONObject) restaurant).get("phone");
            if(phoneNum.length() > 3) {
            	phoneNum = phoneNum.substring(2);
                phoneNum = phoneNum.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            }
            JSONArray categories = (JSONArray) ((JSONObject) restaurant).get("categories");
            String cuisine = (String) ((JSONObject) categories.get(0)).get("title");
            String price = (String) ((JSONObject) restaurant).get("price");
            String rating = ((JSONObject) restaurant).get("rating").toString();
            
            result.add(new Restaurant(restaurantName, restaurantImg, restaurantAdress, yelpLink, phoneNum, cuisine, price, rating));
        }

        // Return null if restaurant not found
        return result;
    }
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		if (restaurants.size() > 0) {
			out.println("<br><h2 style='color: gray'>Results for " + '"' +searchName + '"' + "<h2><br>");
		}
		
		
		int count = 0;
		for (int i = 0; i < restaurants.size();i++){// Username or password are empty
			out.println("<br><div class=\"row\"  name =\"restaurant" +i+ '"'+">\n" + 
					"      <div class=\"col-4\" >\n" + 
					"        <!-- content for column 1 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
					"        \n" + 
					"      <a  onclick=\"getdetails("+i+")\" ><img name = \"image\" class=\"form-control rounded-corners\" src=" + restaurants.get(i).getImgLink() +"  /> </a>" +
					"        \n" + 
					"      </div>\n" + 
					"      <div class=\"col-8\" >\n" + 
					"        <!-- content for column 2 use class = \"form-control\" for it to stretch the size of the column -->\n" + 
					"        \n" + 
					"         <h4 name=\"name"+i+"\" class=\" borderless\" >" + restaurants.get(i).getName() +"</h6>" + 
					"         <h6 name=\"address"+i+"\" class=\" borderless\" >" + restaurants.get(i).getAddress() +"</h6>" + 
					"         <h6 name=\"url"+i+"\" class=\" borderless\" >" + restaurants.get(i).getYelpLink() +"</h6>" + 
					"         <h6 name=\"phone"+i+"\" class=\" borderless\" hidden>" + restaurants.get(i).getPhoneNum() +"</h6>" + 
					"         <h6 name=\"cuisine"+i+"\" class=\" borderless\" hidden>" + restaurants.get(i).getCuisine() +"</h6>" + 
					"         <h6 name=\"price"+i+"\" class=\" borderless\" hidden>" + restaurants.get(i).getPrice() +"</h6>" + 
					"         <h6 name=\"rating"+i+"\" class=\" borderless\" hidden>" + restaurants.get(i).getRating() +"</h6>" + 
					"      </div>\n" + 
					"\n" + 
					"    </div> <br><hr class=\"new2\">");
			count++;
			if(count == 10) {
				break;
			}
		}

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantname = request.getParameter("restaurantname").toLowerCase();
		String choice = request.getParameter("choice");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		searchName = restaurantname;
		PrintWriter out = response.getWriter();
		//JDBCTest.initConnection();
		//out.flush();
		
		if(restaurantname == null || restaurantname.trim().length() == 0) {// Username or password are empty
			out.println("<span style='color: #990000'>Please enter Restaurant Name<span>");
		} else if (choice == null || choice.trim().length() == 0) {
			out.println("<span style='color: #990000'>Please Choose one of the options<span>");
		}else if (latitude == null || latitude.trim().length() == 0 || longitude == null || longitude.trim().length() ==0) {
			out.println("<span style='color: #990000'>Please Enter Latitude and Longitude<span>");
		}
		else { 
			
			try {
				if(restaurants.size() > 0)
					restaurants.clear();
				restaurants = getRestaurants(restaurantname, Double.valueOf(latitude), Double.valueOf(longitude), choice);
			} catch (NumberFormatException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.write("success");
			
		}
	}
}
