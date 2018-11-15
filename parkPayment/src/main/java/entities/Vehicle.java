package entities;
import datastructs.LicensePlate;

public class Vehicle {
	private String type;
	LicensePlate lp;
	
	public Vehicle(String t, LicensePlate l) {
		type = "";
		if(isValidType(t))
			type = t;
		lp = l;
	}
	public static boolean isValidType(String type) {
		return type.equals("rv") || type.equals("car") || type.equals("motorcycle");
	}
	public String getType() {
		return type;
	}
	public String toString() {
		return "Type: "+type+" License Plate: "+lp.toString();
	}
}
