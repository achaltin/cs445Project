package datastructs;

public class Address {
	public String street;
	public String city;
	public String state;
	public String zip;
	
	public String toString(){
		return street + ", "+city+" "+state+", "+zip;
	}
}
