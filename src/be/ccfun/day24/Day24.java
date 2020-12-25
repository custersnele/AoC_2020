package be.ccfun.day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day24.txt"));
		Set<Tile> allTiles = new HashSet<>();

		Tile center = new Tile();
		center.createNeighbours();
		for (String line : lines) {
			List<Direction> simplify = simplify(getDirections(line));
			List<Tile> tiles = flipTiles(center, simplify);
			allTiles.addAll(tiles);

		}
		System.out.println(allTiles.stream().filter(t -> t.isBlack()).count());
		System.out.println(allTiles.size());
		for (int day = 1; day <= 100; day++) {
			// for all black tiles, make sure the neighbours are in allTiles
			/*List<Tile> newNeighbours = new ArrayList<>();
			for (Tile tile: allTiles) {
				if (tile.isBlack() && tile.getNumberOfNeighbours() < 6) {
					tile.createNeighbours();
					newNeighbours.addAll(tile.getNeighbours());
				}
			}
			allTiles.addAll(newNeighbours);*/
			List<Tile> blackNeighbours = new ArrayList<>();
			allTiles.stream().peek(t -> t.createNeighbours()).map(t -> t.getNeighbours()).forEach(l -> blackNeighbours.addAll(l));
			allTiles.addAll(blackNeighbours);
			List<Integer> blackTiles = new ArrayList<>();
			for (Tile tile : allTiles) {
				if (tile.nextColor() == Color.BLACK) {
					blackTiles.add(tile.getId());
				}
			}
			allTiles.forEach(t -> {
				if (blackTiles.contains(t.getId())) {
					t.setColor(Color.BLACK);
				} else {
					t.setColor(Color.WHITE);
				}
			});
			System.out.println(day + " " + allTiles.stream().filter(t -> t.isBlack()).count());
		}
	}

	public static List<Direction> getDirections(String directions) {
		int i = 0;
		List<Direction> instructions = new ArrayList<>();
		while (i < directions.length()) {
			int step = 1;
			if (directions.charAt(i) == 's' || directions.charAt(i) == 'n') {
				step = 2;

			}
			String nextStep = directions.substring(i, i + step);
			Direction direction = Direction.valueOf(nextStep);
			instructions.add(direction);
			i += step;
		}
		return instructions;
	}

	public static List<Tile> flipTiles(Tile tile, List<Direction> directions) {
		int i = 0;
		List<Tile> visited = new ArrayList<>();
		for (Direction direction : directions) {
			tile = tile.go(direction);
			visited.add(tile);
			visited.addAll(tile.getNeighbours());
		}
		tile.flip();
		return visited;
	}

	public static List<Direction> simplify(List<Direction> directions) {
		directions = simplifyOppostie(directions, Direction.e,Direction.w);
		directions = simplifyOppostie(directions, Direction.ne,Direction.sw);
		directions = simplifyOppostie(directions, Direction.se,Direction.nw);
		return directions;
	}


	public static List<Direction> simplifyOppostie(List<Direction> directions, Direction direction, Direction oppositeDirection) {
		int countDirection = (int) directions.stream().filter(d -> d == direction).count();
		int countOppositeDirection = (int) directions.stream().filter(d -> d == oppositeDirection).count();
		int min = Math.min(countDirection, countOppositeDirection);
		int removeDirection = min;
		int removeOppositeDirection = min;
		List<Direction> simple = new ArrayList<>();
		for (Direction aDirection : directions) {
			if (aDirection == direction && removeDirection > 0) {
				removeDirection--;
			} else if (aDirection == oppositeDirection && removeOppositeDirection > 0) {
				removeOppositeDirection--;
			} else {
				simple.add(aDirection);
			}
		}
		return simple;
	}



}
