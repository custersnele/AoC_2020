package be.ccfun.day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tile {

	private int number;

	private List<String> image = new ArrayList<>();

	private List<Tile> neighbours;

	public Tile(int number) {
		this.number = number;
	}

	public void addLine(String imageLine) {
		if (imageLine.isBlank()) {
			return;
		}
		image.add(imageLine);
	}

	public void setNeighbours(List<Tile> tiles) {
		neighbours = tiles.stream().filter(t -> t.number != this.number).filter(t -> t.match(this)).collect(Collectors.toList());
	}

	public int getSize() {
		return image.get(0).length();
	}

	public List<Tile> getNeighbours() {
		return neighbours;
	}

	public int getNumber() {
		return number;
	}

	public boolean isCorner() {
		return neighbours.size() == 2;
	}

	public boolean match(Tile tile) {
		return tile.getBorders().stream().anyMatch(b -> getBorders().contains(b));
	}

	public Direction getMatch(Tile tile) {
		if (tile.getBorders().contains(getLeft())) {
			return Direction.LEFT;
		}
		if (tile.getBorders().contains(getRight())) {
			return Direction.RIGHT;
		}
		if (tile.getBorders().contains(getTop())) {
			return Direction.TOP;
		}
		if (tile.getBorders().contains(getBottom())) {
			return Direction.BOTTOM;
		}
		return null;
	}

	public void align(Direction... directions) {
		while (!hasAlignment(directions)) {
			rotate();
		}
	}

	public void align(String border, Direction direction) {
		if (getBorder(direction).equals(border)) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			rotate();
			if (getBorder(direction).equals(border)) {
				return;
			}
		}
		flipHorizontal();
		for (int i = 0; i < 4; i++) {
			rotate();
			if (getBorder(direction).equals(border)) {
				return;
			}
		}
		flipHorizontal();
		flipVertical();
		for (int i = 0; i < 4; i++) {
			rotate();
			if (getBorder(direction).equals(border)) {
				return;
			}
		}
	}

	public boolean hasAlignment(Direction... directions) {
		List<Direction> currentDirections = neighbours.stream().map(t -> getMatch(t)).filter(t -> t != null).collect(Collectors.toList());
		for (Direction expectedDirection : directions) {
			currentDirections.remove(expectedDirection);
		}
		return currentDirections.isEmpty();
	}



	public void rotate() {
		List<String> newImage = new ArrayList<>();
		for (int i = 0; i < getSize(); i++) {
			newImage.add(getReverse(getColumn(i)));
		}
		image = newImage;
	}

	public void flipVertical() {
		List<String> newImage = new ArrayList<>();
		for (int i = getSize() - 1; i >= 0; i--) {
			newImage.add(getRow(i));
		}
		image = newImage;
	}

	public void flipHorizontal() {
		List<String> newImage = new ArrayList<>();
		for (int i = getSize() - 1; i >= 0; i--) {
			newImage.add(getColumn(i));
		}
		image = newImage;
	}


	private String getColumn(int index) {
		StringBuilder builder = new StringBuilder();
		for (String line : image) {
			builder.append(line.charAt(index));
		}
		return builder.toString();
	}

	public String getRow(int index) {
		return image.get(index);
	}

	public List<String> getBorders() {

		List<String> borders = new ArrayList<>();
		borders.add(getLeft());
		borders.add(getBottom());
		borders.add(getTop());
		borders.add(getRight());
		borders.add(getReverse(getLeft()));
		borders.add(getReverse(getBottom()));
		borders.add(getReverse(getTop()));
		borders.add(getReverse(getRight()));
		return borders;
	}

	private String getReverse(String border) {
		StringBuilder builder = new StringBuilder(border);
		return builder.reverse().toString();
	}

	public String getLeft() {
		return getColumn(0);
	}

	public String getTop() {
		return image.get(0);
	}

	public String getBottom() {
		return image.get(image.size() - 1);
	}

	public String getRight() {
		return getColumn(getSize()-1);
	}

	public String getBorder(Direction direction) {
		switch (direction) {
			case LEFT: return getLeft();
			case RIGHT: return getRight();
			case BOTTOM: return getBottom();
			case TOP: return getTop();
		}
		return null;
	}


	public Tile getRightNeighbour() {
		Tile tile = neighbours.stream().filter(n -> n.getBorders().contains(getRight())).findAny().get();
		tile.align(getRight(), Direction.LEFT);
		return tile;
	}

	public Tile getLeftNeighbour() {
		Tile tile = neighbours.stream().filter(n -> n.getBorders().contains(getLeft())).findAny().get();
		tile.align(getLeft(), Direction.RIGHT);
		return tile;
	}

	public Tile getBottomNeighbour() {
		Tile tile = neighbours.stream().filter(n -> n.getBorders().contains(getBottom())).findAny().get();
		tile.align(getBottom(), Direction.TOP);
		return tile;
	}

	public Tile getTopNeighbour() {
		Tile tile = neighbours.stream().filter(n -> n.getBorders().contains(getTop())).findAny().get();
		tile.align(getTop(), Direction.BOTTOM);
		return tile;
	}

	public void print() {
		for (String line : image) {
			System.out.println(line);
		}
	}

	public void countPatternAllDirections(List<String> seamonster) {
		System.out.println("SOL: " + countPattern(seamonster));
		for (int i = 0; i < 4; i++) {
			rotate();
			printSolution(seamonster);
		}
		flipHorizontal();
		for (int i = 0; i < 4; i++) {
			rotate();
			printSolution(seamonster);
		}
		flipHorizontal();
		flipVertical();
		for (int i = 0; i < 4; i++) {
			rotate();
			printSolution(seamonster);
		}
	}


	private void printSolution(List<String> seamonster) {
		int i = countPattern(seamonster);
		if (i > 0) {
			System.out.println("SOLUTION: " + i + " " + (count('#') - (15 * i)));
		}
	}



	private int countPattern(List<String> seamonster) {
		int seamonsterLength = seamonster.get(0).length();
		int count = 0;
		for (int si = 0; si < image.get(0).length() - seamonsterLength; si++) {
			for (int sj = 0; sj < image.size() - 2; sj++) {
				List<String> seaPart = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					seaPart.add(image.get(sj+i).substring(si));
				}
				if (matchPattern(seaPart, seamonster)) {
					count++;
				}
			}
		}
		return count;
	}

	public boolean matchPattern(List<String> sea, List<String> seamonster) {
		int seamonsterLength = seamonster.get(0).length();
		for (int i = 0; i < seamonsterLength; i++) {
			for (int j = 0; j < seamonster.size(); j++) {
				if (seamonster.get(j).charAt(i) == '#') {
					if (sea.get(j).charAt(i) != '#') {
						return false;
					}
				}
			}
		}
		return true;
	}


	public int count(char character) {
		int count = 0;
		for (String line : image) {
			count += (int) line.chars().filter(c -> c == character).count();
		}
		return count;
	}


}
