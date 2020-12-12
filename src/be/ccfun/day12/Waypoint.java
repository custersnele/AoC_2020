package be.ccfun.day12;

public class Waypoint {
	private int north;
	private int east;

	public Waypoint(int north, int east) {
		this.north = north;
		this.east = east;
	}

	public int getEast() {
		return east;
	}

	public int getNorth() {
		return north;
	}

	public void rotate(boolean clockwise, int degree) {
		int oldEast = east;
		int oldNorth = north;
		if (degree == 270) {
			rotate(!clockwise, 90);
		} else {
			if (degree == 180) {
				north = -oldNorth;
				east = -oldEast;
			}
			if (degree == 90) {
				if (clockwise) {
					east = oldNorth;
					north = -oldEast;
				} else {
					north = oldEast;
					east = -oldNorth;
				}
			}
		}

	}

	@Override
	public String toString() {
		return "Waypoint{" +
				"north=" + north +
				", east=" + east +
				'}';
	}
}
