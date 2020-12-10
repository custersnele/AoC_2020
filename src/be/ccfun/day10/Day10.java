package be.ccfun.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
	public static void main(String[] args) throws IOException {
		List<Integer> adapters = Files.lines(Path.of("resources/day10.txt")).map(Integer::parseInt).collect(Collectors.toList());
		int needed = adapters.stream().mapToInt(x -> x).max().getAsInt();
		adapters.add(0);
		adapters = adapters.stream().sorted().collect(Collectors.toList());
		int[] diffs = new int[3];
		for (int i = 0; i < adapters.size() - 1; i++) {
			int diff = adapters.get(i + 1) - adapters.get(i);
			diffs[diff - 1]++;
		}
		diffs[2]++;
		System.out.println(diffs[0] * diffs[2]);
	}
}
