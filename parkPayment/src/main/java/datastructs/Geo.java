package datastructs;

public class Geo {
	public double lat;
	public double lng;
	public Geo(double lt, double lg) {
		lat = lt;
		lng = lg;
	}
	public String toString() {
		return "lat: "+lat+" lng: "+lng;
	}

}
