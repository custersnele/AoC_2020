package be.ccfun.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WaypointTest {

	@Test
	public void testWaypoint1() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(true, 90);
		Assertions.assertEquals(-10, waypoint.getNorth());
		Assertions.assertEquals(4, waypoint.getEast());
	}

	@Test
	public void testWaypoint2() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(false, 90);
		Assertions.assertEquals(10, waypoint.getNorth());
		Assertions.assertEquals(-4, waypoint.getEast());
	}

	@Test
	public void testWaypoint3() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(true, 180);
		Assertions.assertEquals(-4, waypoint.getNorth());
		Assertions.assertEquals(-10, waypoint.getEast());
	}
	@Test
	public void testWaypoint4() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(false, 180);
		Assertions.assertEquals(-4, waypoint.getNorth());
		Assertions.assertEquals(-10, waypoint.getEast());
	}

	@Test
	public void testWaypoint5() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(true, 270);
		Assertions.assertEquals(10, waypoint.getNorth());
		Assertions.assertEquals(-4, waypoint.getEast());
	}

	@Test
	public void testWaypoint6() {
		Waypoint waypoint = new Waypoint(4, 10);
		waypoint.rotate(false, 270);
		Assertions.assertEquals(-10, waypoint.getNorth());
		Assertions.assertEquals(4, waypoint.getEast());
	}



}
