package entities;

import datastructs.Address;
import java.util.Scanner;

public class CreditCard {
	private String fullNum;
	private String nameOnCard;
	private String expDate;
	private String lastFour;
	private Address billing;
	
	public CreditCard(String num, String name, String exp, Address addr) {
		fullNum = "    "; //at least four 
		if(isValidCCNum(num))
			fullNum = num;
		expDate = "";
		nameOnCard = name;
		if(isValidExp(exp))
			expDate = exp;
		billing = addr;
		lastFour = fullNum.substring(fullNum.length()-4, fullNum.length());

	}
	
	public void setfullNum(String num) {
		if(!isValidCCNum(fullNum) && isValidCCNum(num))
			fullNum = num;
	}
	public void updateExpDate(String date) {
		if( isValidExp(date) && //new date must be valid
			(!isValidExp(expDate) ) || //either old date was bad or the new year is later
			(Integer.parseInt(expDate.substring(3,4)) < Integer.parseInt(date.substring(3,4)) )
		) {
			expDate = date;
		}
	}
	public static boolean isValidCCNum(String num) {
		return num.length() == 16 || num.length() == 15 && num.matches("[0-9]+");
	}
	
	public static boolean isValidExp(String exp) {
		String[] split = exp.split("/");
		try {
			int m = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			return (m<=12 && m>0 && y>=18 && y<100);
		}catch(Exception e) {
			return false;
		}
	}
	public String toString() {
		return nameOnCard+" ,"+lastFour+" "+expDate+" "+billing.toString();
	}
	public String getFullNum() {
		return fullNum;
	}
	public String getLastFour() {
		return lastFour;
	}
	public Address getAddr() {
		return billing;
	}
	public String getName() {
		return nameOnCard;
	}
	public String getExpDate() {
		return expDate;
	}
}
