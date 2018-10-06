package entities;
import datastructs.LicensePlate;

public class Vehicle {
	private String type;
	private LicensePlate lp;
	
	public Vehicle(String t, LicensePlate l) {
		type = t;
		lp = l;
	}
}
