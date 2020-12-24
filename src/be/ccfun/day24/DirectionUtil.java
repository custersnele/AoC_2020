package be.ccfun.day24;

public class DirectionUtil {

	public static Direction getOpposite(Direction direction) {
		switch (direction) {
			case e: return Direction.w;
			case w: return Direction.e;
			case ne: return Direction.sw;
			case nw: return Direction.se;
			case sw: return Direction.ne;
			case se: return Direction.nw;
		}
		return null;
	}

	public static Direction getLeft(Direction direction) {
		switch (direction) {
			case e: return Direction.ne;
			case w: return Direction.sw;
			case ne: return Direction.nw;
			case nw: return Direction.w;
			case sw: return Direction.se;
			case se: return Direction.e;
		}
		return null;
	}

	public static Direction getRight(Direction direction) {
		switch (direction) {
			case e: return Direction.se;
			case w: return Direction.nw;
			case ne: return Direction.e;
			case nw: return Direction.ne;
			case sw: return Direction.w;
			case se: return Direction.sw;
		}
		return null;
	}

}
