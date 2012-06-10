package de.stusti.GotoConsideredUseful.contacts;


public class Address {

	protected String postBox;
	protected String street;
	protected String city;
	protected String state;
	protected String postalCode;
	protected String country;
	protected String type;
	
	protected String dataSource; 
	
	public String getAddressString() {
		String addressString = "";
		
		addressString += getStreet();
		addressString += " " + getPostalCode();
		addressString += " " + getCity();
		addressString += " " + getState();
		addressString += " " + getPostBox();
		
		return addressString;
	}
	
	public String getPostCodeAndCity() {
		String postCodeAndCity = "";
		
		postCodeAndCity += getPostalCode();
		postCodeAndCity.trim();
		postCodeAndCity += " " + getCity();
		postCodeAndCity.trim();
		
		return postCodeAndCity;
	}
	
	public String getPostBox() {
		return postBox;
	}
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCountry() {
		return country;
	}
	public String getType() {
		return type;
	}
	public String getDataSource() {
		return dataSource;
	}
	
	public void setPostBox(String postBox) {
		this.postBox = postBox;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
}
