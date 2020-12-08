package be.ccfun.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day3_test.txt"));
		List<Long> trees = new ArrayList<>();
		trees.add(countTrees(lines, 1, 1));
		trees.add(countTrees(lines, 3, 1));
		trees.add(countTrees(lines, 5, 1));
		trees.add(countTrees(lines, 7, 1));
		trees.add(countTrees(lines, 1, 2));
		System.out.println(trees);
		long reduce = trees.stream().mapToLong(x -> x).peek(x -> System.out.println(x)).reduce(1l, (x, acc) -> x * acc);
		System.out.println(reduce);
	}

	private static long countTrees(List<String> lines, int stepRight, int stepDown) {
		int x = 0;
		int y = 0;
		boolean searching = true;
		long trees = 0;
		int maxLength = lines.get(0).length();
		while (searching) {
			x = (x + stepRight) % maxLength;
			y += stepDown;
			try {
			if (lines.get(y).charAt(x) == '#') {
				trees++;
			}} catch (IndexOutOfBoundsException e) {
				searching = false;
			}
		}
		return trees;
	}

}
