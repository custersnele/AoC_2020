package be.ccfun.day24;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tile implements Comparable<Tile> {
	private static int count;
	private Tile[] neighbours = new Tile[6];
	private int id;
	private Color color;


	public Tile() {
		this.id = count++;
		if (id == 417) {
			System.out.println("debug");
		}
		this.color = Color.WHITE;
	}

	public void createNeighbours() {
		boolean tile232Created = false;
		for (Direction direction : Direction.values()) {
			if (neighbours[direction.ordinal()] == null) {
				Tile neighbour = new Tile();
				if (neighbour.getId() == 417) {
					tile232Created = true;
				}
				setNeighbour(direction, neighbour);
			}
		}

		// controle w - sw buur
		Tile westSoutwestNeighbour = neighbours[Direction.w.ordinal()].neighbours[Direction.sw.ordinal()];
		if (westSoutwestNeighbour != null) {
			Tile swNeighbour = neighbours[Direction.sw.ordinal()];
			swNeighbour.setNeighbour(Direction.w, westSoutwestNeighbour);
		}
		// controle sw - w buur
		Tile southwestWestNeighbour = neighbours[Direction.sw.ordinal()].neighbours[Direction.w.ordinal()];
		if (southwestWestNeighbour != null) {
			Tile wNeighbour = neighbours[Direction.w.ordinal()];
			wNeighbour.setNeighbour(Direction.sw, southwestWestNeighbour);
		}
		// controle w - nw buur
		Tile westNorthwestNeighbour = neighbours[Direction.w.ordinal()].neighbours[Direction.nw.ordinal()];
		if (westNorthwestNeighbour != null) {
			Tile nwNeighbour = neighbours[Direction.nw.ordinal()];
			nwNeighbour.setNeighbour(Direction.w, westNorthwestNeighbour);
		}
		// controle nw - w buur
		Tile northwestWestNeighbour = neighbours[Direction.nw.ordinal()].neighbours[Direction.w.ordinal()];
		if (northwestWestNeighbour != null) {
			Tile wNeighbour = neighbours[Direction.w.ordinal()];
			wNeighbour.setNeighbour(Direction.nw, northwestWestNeighbour);
		}
		// controle nw - e buur ??
		/*Tile northwestEastNeighbour = neighbours[Direction.nw.ordinal()].neighbours[Direction.e.ordinal()];
		if (northwestEastNeighbour != null) {
			Tile wNeighbour = neighbours[Direction.w.ordinal()];
			wNeighbour.setNeighbour(Direction.nw, northwestEastNeighbour);
		}*/
		// controle e - ne buur
		Tile eastNortheastNeighbour = neighbours[Direction.e.ordinal()].neighbours[Direction.ne.ordinal()];
		if (eastNortheastNeighbour != null) {
			Tile neNeighbour = neighbours[Direction.ne.ordinal()];
			neNeighbour.setNeighbour(Direction.e, eastNortheastNeighbour);
		}
		// controle ne - e buur
		Tile northeastEastNeighbour = neighbours[Direction.ne.ordinal()].neighbours[Direction.e.ordinal()];
		if (northeastEastNeighbour != null) {
			Tile eNeighbour = neighbours[Direction.e.ordinal()];
			eNeighbour.setNeighbour(Direction.ne, northeastEastNeighbour);
		}
		// controle e - se buur
		Tile eastSoutheastNeighbour = neighbours[Direction.e.ordinal()].neighbours[Direction.se.ordinal()];
		if (eastSoutheastNeighbour != null) {
			Tile seNeighbour = neighbours[Direction.se.ordinal()];
			seNeighbour.setNeighbour(Direction.e, eastSoutheastNeighbour);
		}
		// controle se - e buur
		Tile southeastEastNeighbour = neighbours[Direction.se.ordinal()].neighbours[Direction.e.ordinal()];
		if (southeastEastNeighbour != null) {
			Tile eNeighbour = neighbours[Direction.e.ordinal()];
			eNeighbour.setNeighbour(Direction.se, southeastEastNeighbour);
		}
		// controle nw - ne buur
		Tile nwneneighbour = neighbours[Direction.nw.ordinal()].neighbours[Direction.ne.ordinal()];
		if (nwneneighbour != null) {
			Tile neNeighbour = neighbours[Direction.ne.ordinal()];
			neNeighbour.setNeighbour(Direction.nw, nwneneighbour);
		}
		// controle ne - nw buur
		Tile nenwneighbour = neighbours[Direction.ne.ordinal()].neighbours[Direction.nw.ordinal()];
		if (nenwneighbour != null) {
			Tile nwNeighbour = neighbours[Direction.nw.ordinal()];
			nenwneighbour.setNeighbour(Direction.sw, nwNeighbour);
			nwNeighbour.setNeighbour(Direction.ne, nenwneighbour);

		}
		// controle se - sw buur
		Tile seswneighbour = neighbours[Direction.se.ordinal()].neighbours[Direction.sw.ordinal()];
		if (seswneighbour != null) {
			Tile swNeighbour = neighbours[Direction.sw.ordinal()];
			swNeighbour.setNeighbour(Direction.se, seswneighbour);
		}
		// controle sw - se buur
		Tile swseneighbour = neighbours[Direction.sw.ordinal()].neighbours[Direction.se.ordinal()];
		if (swseneighbour != null) {
			Tile seNeighbour = neighbours[Direction.se.ordinal()];
			seNeighbour.setNeighbour(Direction.sw, swseneighbour);
		}
		if (tile232Created) {
			System.out.println("***** 417 ******");
			System.out.println(this);
			for (Direction direction : Direction.values()) {
				System.out.println(neighbours[direction.ordinal()]);
			}
		}
	}

	/*
	Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
	 */
	public Color nextColor() {
		long countBlack = Arrays.stream(neighbours).filter(t -> t != null).filter(t -> t.isBlack()).count();
		if (isWhite()) {
			if (countBlack == 2) {
				return Color.BLACK;
			} else {
				return Color.WHITE;
			}
		} else {
			if (countBlack == 0 || countBlack > 2) {
				return Color.WHITE;
			} else {
				return Color.BLACK;
			}
		}
	}

	public List<Tile> getNeighbours() {
		return Arrays.stream(neighbours).collect(Collectors.toList());
	}

	public int getNumberOfNeighbours() {
		return (int) Arrays.stream(neighbours).filter(n -> n != null).count();
	}

	public boolean isBlack() {
		return color == Color.BLACK;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isWhite() {
		return color == Color.WHITE;
	}

	public void flip() {
		color = Color.values()[(color.ordinal() + 1) % Color.values().length];
		System.out.println("Flip " + id + " to " + (isWhite()? "white" : "black"));
	}

	public Tile go(Direction direction) {
		Tile neighbour = neighbours[direction.ordinal()];
		neighbour.createNeighbours();
		return neighbour;
	}

	public void setColor(Color color) {
		this.color = color;
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
		if (existingNeighbour != null) {
			System.out.println("PROBLEM");
			System.out.println(this);
			System.out.println(existingNeighbour);
			System.out.println(neighbour);
		}
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

	public Color getColor() {
		return color;
	}

	@Override
	public int compareTo(Tile o) {
		return id - o.id;
	}
}
