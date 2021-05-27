package nguyenhi_CSCI201L_Assignment4;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCTest {
	public static final String CREDENTIALS_STRING = "jdbc:mysql://localhost/SalEats?user=root&password=2105&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	static Connection connection;
	
	public static void initConnection() {
		if(connection != null) {
			System.out.println("[Warn] Connection has already been established. ");
			return;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(CREDENTIALS_STRING);
			
		}catch (ClassNotFoundException |SQLException e){
			System.out.println("Exception in initConnection()" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void addUser(String username, String email, String password, String gglogin) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO UserInfo(username, email, pw, gglogin) VALUES (?, ?, ?, ?);");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, gglogin);
			preparedStatement.execute();
			preparedStatement.close();
		} catch(SQLException e) {
			System.out.println("Exception in addUser()" +e.getMessage());
		}
	}
	public static void addFavorite(String username, String restaurant, String imglink, String address, String yelplink, String phone, String cuisine, String price, String rating) {
		try {
			if( favoriteExist(username, restaurant)) {
				removeFavorite(username, restaurant);
				System.out.print("Remove " +restaurant +"before add");
			}
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Favorites(username, restaurant, imglink, address, yelplink, phone, cuisine, price, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, restaurant);
			preparedStatement.setString(3, imglink);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, yelplink);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, cuisine);
			preparedStatement.setString(8, price);
			preparedStatement.setString(9, rating);
			
			System.out.print("Add " +restaurant +" " + imglink + " " + address);
			preparedStatement.execute();
			preparedStatement.close();
		}catch(SQLException e) {
			System.out.println("Exception in adFavorite()" + e.getMessage());
		}
	}
	
	public static void removeFavorite(String username, String restaurant) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE From Favorites WHERE username=? AND restaurant=?;");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, restaurant);
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Exception in removeFavorite()" + e.getMessage());
		}
	}
	
	public static Boolean favoriteExist(String username, String restaurant) {
		Boolean r = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Favorites WHERE username=?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				//System.out.println(resultSet.getString("restaurant"));
				if(restaurant.equals(resultSet.getString("restaurant"))) {
					return true;
				}
			}
		}catch (SQLException e) {
			System.out.println("Exception in usernameExist()" + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("Exception in exist() null" + e.getMessage());
		}
		return r;
	}
	
	public static Boolean usernameExist(String username) {
		Boolean r = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserInfo WHERE username=?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			r =  resultSet.next();
		}catch (SQLException e) {
			System.out.println("Exception in usernameExist()" + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("Exception in exist() null" + e.getMessage());
		}
		return r;
	}
	
	
	public static Boolean matchPassword(String username, String pw) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserInfo WHERE username=?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				if(pw.equals(resultSet.getString("pw"))) {
					return true;
				}
			}
		}catch (SQLException e) {
			System.out.println("Exception in matchPassword()" + e.getMessage());
		}
		return false;
	}
	
	public static Boolean emailExist(String email) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserInfo WHERE email=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();
		}catch (SQLException e) {
			System.out.println("Exception in exist()" + e.getMessage());
		}
		return false;
	}
	
	public static ArrayList<Restaurant> getFavorites(String username){
		ArrayList<Restaurant> result = new ArrayList<Restaurant>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Favorites WHERE username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String restaurant = resultSet.getString("restaurant");
				String imgLink = resultSet.getString("imglink");
				String address = resultSet.getString("address");
				String yelpLink = resultSet.getString("yelplink");
				String phone = resultSet.getString("phone");
				String cuisine = resultSet.getString("cuisine");
				String price = resultSet.getString("price");
				String rating = resultSet.getString("rating");
				result.add(new Restaurant(restaurant, imgLink, address, yelpLink, phone, cuisine, price, rating));
			}
		}catch (SQLException e) {
			System.out.println("Exception in getFavorites " +e.getMessage());
		}
		return result;
	}
	
	public static String isgglogin(String username) {
		String result ="";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UserInfo WHERE username=?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getString("gglogin");
			}
		}catch (SQLException e) {
			System.out.println("Exception in usernameExist()" + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("Exception in exist() null" + e.getMessage());
		}
		return result;
	}
}

