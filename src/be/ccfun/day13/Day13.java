package be.ccfun.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day13.txt"));
		int start = Integer.parseInt(lines.get(0));
		int end = start;
		List<Integer> busses = Arrays.stream(lines.get(1).split(",")).filter(x -> !x.equals("x")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		boolean searching = true;
		while (searching) {
			for (int bus : busses) {
				if (end % bus == 0) {
					System.out.println(end);
					int wait = end - start;
					System.out.println(wait * bus);
					searching = false;
				}
			}
			end++;
		}
	}

}
