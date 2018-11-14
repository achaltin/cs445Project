package datastructs;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoTest {

	@Test
	public void testGeo() {
		Geo g = new Geo(1.1, -2.2);
		assertEquals(g.lat, 1.1, .0001);
		assertEquals(g.lng, -2.2, .0001);
		assertEquals(g.toString(),"lat: 1.1 lng: -2.2");
	}

}
