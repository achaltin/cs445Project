package interfaces;
import datastructs.*;

public interface ParkManager {
	public int addPark(Address addr);
	public int addPark(float f, Address addr);
	public void listParks();
	public void updatePark(int id, float f);
}
