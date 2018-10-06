package entities;
import java.text.NumberFormat;

import datastructs.Address;

public class Park {
	private float fee;
	private Address address;
	static int idCount = 0;
	private int id;
	
	public Park(Address addr) {
		fee = 0.0f;
		address = addr;
		idCount++;
		id = idCount;
	}
	
	public Park(float f, Address addr) {
		fee = f;
		address = addr;
		idCount++;
		id = idCount;
	}
	
	public float viewFee() {
		return fee;
	}
	
	public String getState() {
		return address.state;
	}
	
	public void updateFee(float f) {
		fee = f;
	}
	
	public int id()
	{
		return id;
	}
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(fee)+"\t" + address.toString();
	}
}
