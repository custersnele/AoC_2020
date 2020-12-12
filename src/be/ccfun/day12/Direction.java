package be.ccfun.day12;

public enum Direction {
	N(0),
	E(90),
	S(180),
	W(270);

	int degree;

	Direction(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public Direction rotate(int degree) {
		System.out.println("ROTATE " + this.degree + " " + degree);
		int newDegree = (this.degree + degree) % 360;
		if (newDegree < 0) {
			newDegree = 360 + newDegree;
		}
		for (Direction direction : values()) {
			if (direction.getDegree() == newDegree) {
				return direction;
			}
		}
		return null;
	}
}
