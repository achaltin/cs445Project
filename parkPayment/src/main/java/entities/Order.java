package entities;

import cs445.parkPayment.UniqueIdGenerator;
import datastructs.LicensePlate;

public class Order {
	int oid;
	int pid;
	Vehicle vehicle;
	Visitor visitor;
	CreditCard cc;
	public Order(int p, Vehicle ve, Visitor vi, CreditCard c) {
		oid = UniqueIdGenerator.getUniqueID();
		pid = p;
		vehicle = ve;
		visitor = vi;
		cc = c;
	}
	public boolean isValidOrder() {
		return CreditCard.isValidCCNum(cc.getFullNum())
				&& CreditCard.isValidExp(cc.getExpDate())
				&& Vehicle.isValidType(vehicle.getType())
				&& LicensePlate.isValidNum(vehicle.lp.getNum())
				&& LicensePlate.isValidState(vehicle.lp.getState());
	}
	public int getOid() {
		return oid;
	}
	public String toString() {
		return "OID: "+oid+" PID: " +pid+ "Vehicle: ["
				+vehicle.toString()+"] Visitor: ["+visitor.toString()
				+"] Credit Card: "+cc.toString();
	}
}
