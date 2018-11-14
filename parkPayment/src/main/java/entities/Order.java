package entities;

import cs445.parkPayment.UniqueIdGenerator;

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

}
