package cs445.parkPayment;

public class Park {
	private float fee;
	
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
