package datastructs;

public class Address {
	public String street;
	public String city;
	public String state;
	public String zip;
	
	public Address() {
		
	}
	public Address(String s, String c, String st, String z){
		street = s;
		city = c;
		state = st;
		zip = z;
	}

	public String toString(){
		return street + ", "+city+" "+state+", "+zip;
	}
}
