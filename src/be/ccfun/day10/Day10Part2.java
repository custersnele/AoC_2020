package be.ccfun.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10Part2 {
	public static void main(String[] args) throws IOException {
		List<Integer> adapters = Files.lines(Path.of("resources/day10.txt")).map(Integer::parseInt).collect(Collectors.toList());
		int max = adapters.stream().mapToInt(x -> x).max().getAsInt();
		adapters.add(max + 3);
		adapters.add(0);
		Collections.sort(adapters);

		long[] diffs = new long[adapters.size()];
		diffs[0] = 1;
		for (int i = 0; i < adapters.size(); i++) {
			for (int j = 1; j <= 3 && i + j < adapters.size(); j++) {
				int diff = adapters.get(i + j) - adapters.get(i);
				if (diff <= 3) {
					diffs[i+j] += diffs[i];
				}
			}
		}
		for (long diff : diffs) {
			System.out.println(diff);
		}
	}
}
