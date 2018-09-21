package entities;
import datastructs.Address;

public class Park {
	private float fee;
	private Address address;
	
	public Park() {
		fee = 0.0f;
	}
	public Park(float f) {
		fee = f;
	} 
	
	public float viewFee() {
		return fee;
	}
	
	public String getState() {
		return "Illinois";
	}
}
