package be.ccfun.day17_part2;

import be.ccfun.day17.State;

import java.util.List;
import java.util.stream.Collectors;

public class Cube {

	private int x;
	private int y;
	private int z;
	private int w;
	private State state;

	public Cube(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.state = State.INACTIVE;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}


	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public Cube createNext(List<Cube> neighbours) {
		Cube cube = new Cube(x, y, z, w);
		int activeCount = (int) neighbours.stream().filter(n -> n.getState() == State.ACTIVE).count();
		switch (state) {
			case ACTIVE:
				if (activeCount == 2 || activeCount == 3) {
					cube.setState(State.ACTIVE);
				} else {
					cube.setState(State.INACTIVE);
				}
				break;
			case INACTIVE:
				if (activeCount == 3) {
					cube.setState(State.ACTIVE);
				} else {
					cube.setState(State.INACTIVE);
				}
				break;
		}
		return cube;
	}

	public List<Cube> getNeigbours(List<Cube> cubes) {
		return cubes.stream().filter(c -> c.isNeighbour(this)).collect(Collectors.toList());
	}

	public boolean isNeighbour(Cube cube) {
		if (cube.x == x && cube.y == y && cube.z == z && cube.w == w) {
			return false;
		}
		return cube.x >= x - 1 && cube.x <= x + 1 && cube.y >= y - 1 && cube.y <= y + 1 && cube.z >= z - 1 && cube.z <= z + 1 && cube.w >= w - 1 && cube.w <= w + 1;
		/*
		for (int nz = z - 1; nz <= z + 1; z++) {
			for (int ny = y - 1; ny <= y + 1; y++) {
				for (int nx = x - 1; nx <= x + 1; x++) {
					if (cube.x == nx && cube.y == ny && cube)
				}
			}
		} */
	}
}
