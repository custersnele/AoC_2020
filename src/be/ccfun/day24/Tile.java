package be.ccfun.day24;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Tile {
	private static int count;
	private Tile[] neighbours = new Tile[6];
	private int id;
	private boolean white;
	private List<Integer> aka = new ArrayList<>();

	public Tile() {
		this.id = count++;
		this.white = true;
	}

	public void createNeighbours() {
		for (Direction direction : Direction.values()) {
			if (neighbours[direction.ordinal()] == null) {
				setNeighbour(direction, new Tile());
			}
		}
	}

	public void addAka(int id) {
		aka.add(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isWhite() {
		return white;
	}

	public void flip() {
		white = !white;
		System.out.println("Flip " + id + " to " + (white? "white" : "black"));
	}

	public Tile go(Direction direction) {
		Tile neighbour = neighbours[direction.ordinal()];
		neighbour.createNeighbours();
		return neighbour;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Tile)) {
			return false;
		}

		Tile tile = (Tile) o;

		return id == tile.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	public List<Integer> getAka() {
		return aka;
	}

	public int getId() {
		return id;
	}

	public Tile getNeighbour(Direction direction) {
		return neighbours[direction.ordinal()];
	}

	public void setNeighbourNotRecur(Direction direction, Tile tile) {
		neighbours[direction.ordinal()] = tile;
	}

	public void setNeighbour(Direction direction, Tile neighbour) {
		Tile existingNeighbour = neighbours[direction.ordinal()];
		if (neighbour.equals(existingNeighbour)) {
			return;
		}
		/*if (existingNeighbour != null && existingNeighbour.getAka().contains(neighbour.getId())) {
			return;
		}
		if (existingNeighbour != null) {
			System.out.println("PROBLEM");
			//existingNeighbour.addAka(neighbour.getId());
			TileUtil.merge(existingNeighbour, neighbour);
			neighbour = existingNeighbour;
		}*/
		neighbours[direction.ordinal()] = neighbour;
		Direction opposite = DirectionUtil.getOpposite(direction);
		neighbour.neighbours[opposite.ordinal()] = this;
		Tile leftTile = neighbours[DirectionUtil.getLeft(direction).ordinal()];
		if (leftTile != null) {
			Direction nextConnection = DirectionUtil.getLeft(DirectionUtil.getOpposite(DirectionUtil.getLeft(direction)));
			leftTile.setNeighbour(nextConnection, neighbour);
		}
		Tile rightTile = neighbours[DirectionUtil.getRight(direction).ordinal()];
		if (rightTile != null) {
			Direction nextConnection = DirectionUtil.getRight(DirectionUtil.getOpposite(DirectionUtil.getRight(direction)));
			rightTile.setNeighbour(nextConnection, neighbour);
		}
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tile ").append(id).append("\n");
		for (Direction direction : Direction.values()) {
			if (neighbours[direction.ordinal()] != null) {
				builder.append(direction).append(" Tile ").append(neighbours[direction.ordinal()].id).append("\n");
			}
		}
		return builder.toString();
	}
}
