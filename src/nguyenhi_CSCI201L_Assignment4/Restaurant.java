package nguyenhi_CSCI201L_Assignment4;

public class Restaurant {
	private String imgLink;
	private String name;
	private String address;
	private String yelpLink;
	private String phoneNum;
	private String cuisine;
	private String price;
	private String rating;
	
	public Restaurant(String name, String imgLink, String address, String yelpLink, String phoneNum, String cuisine, String price, String rating) {
		this.name = name;
		this.imgLink = imgLink;
		this.address = address;
		this.yelpLink = yelpLink;
		this.phoneNum = phoneNum;
		this.cuisine = cuisine;
		this. price = price;
		this.rating = rating;
	}

	public String getImgLink() {
		return imgLink;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getYelpLink() {
		return yelpLink;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getPrice() {
		return price;
	}

	public String getCuisine() {
		return cuisine;
	}

	public String getRating() {
		return rating;
	}
	
	
	
}
