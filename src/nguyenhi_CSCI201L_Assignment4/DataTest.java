package nguyenhi_CSCI201L_Assignment4;

public class DataTest {
	public static void main(String[] args) {
		JDBCTest.initConnection();
		/*
		JDBCTest.addUser("hieu", "nmh0202@gmail.com", "123");
		System.out.println(JDBCTest.usernameExist("j"));
		System.out.println(JDBCTest.usernameExist("hieu"));
		JDBCTest.addFavorite("abc", "Sushi");
		System.out.println(JDBCTest.emailExist("nmh0202@gmail.com"));
		System.out.println(JDBCTest.getFavorites("abc"));
		*/
		System.out.println(JDBCTest.usernameExist("abc"));
		JDBCTest.removeFavorite("abc", "Tatsu Ramen");
		
	}
}
