package be.ccfun.day20;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day20 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day20.txt"));
		Tile currentTile = null;
		List<Tile> tiles = new ArrayList<>();
		for (String line : lines) {
			if (line.startsWith("Tile")) {
				if (currentTile != null) {
					tiles.add(currentTile);
				}
				line = line.replace(":","");
				currentTile = new Tile(Integer.parseInt(line.substring(5)));
			} else {
				currentTile.addLine(line);
			}
		}
		tiles.add(currentTile);
		System.out.println(tiles.size());
		BigInteger result = BigInteger.ONE;
		int imageSize = (int) Math.sqrt(tiles.size());
		for (Tile tile : tiles) {
			tile.setNeighbours(tiles);
			List<Tile> matching = tile.getNeighbours();
			int size = matching.size();
			if (size == 2) {
				result = result.multiply(BigInteger.valueOf(tile.getNumber()));
				for (Tile neighbour : tile.getNeighbours()) {
					System.out.println(neighbour.getNumber() + " " + tile.getMatch(neighbour));
				}
			}
			System.out.println(tile.getNumber() + " " + size);
		}
		System.out.println(result);

		Image image = new Image(imageSize);
		Tile tile1 = tiles.stream().filter(t -> t.getNeighbours().size() == 2).findFirst().get();

		tile1.print();
		tile1.rotate();
		System.out.println();
		tile1.print();

		image.setLeftCorner(tile1);
		Tile tile = image.createTile();
		tile.print();


		List<String> seamonster = new ArrayList<>();
		seamonster.add("                  # ");
		seamonster.add("#    ##    ##    ###");
		seamonster.add(" #  #  #  #  #  #   ");

		System.out.println("ALIGNED");
		tile.align(".#.#..#.##...#.##..#####", Direction.TOP);

		System.out.println();
		tile.print();

		tile.countPatternAllDirections(seamonster);





	}



	public static boolean matchPattern(List<String> sea, List<String> seamonster) {
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
}
