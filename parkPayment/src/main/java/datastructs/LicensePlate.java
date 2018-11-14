package datastructs;

public class LicensePlate {
	private String state;
	private String num;
	
	public LicensePlate(String s, String n) {
		state = "";
		num="";
		if(isValidState(s))
			state = s;
		if(isValidNum(n))	
			num = n;
	}
	
	public static boolean isValidState(String st) {
		return st.length() == 2;
	}
	public static boolean isValidNum(String num) {
		return num.length() <8 && num.matches("[a-zA-z0-9]+");
	}
	
	public String getState() {
		return state;
	}
	public String getNum() {
		return num;
	}
}
