package be.ccfun.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Day1 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day1.txt"));
		List<Integer> numbers = lines.stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());
		for (int number : numbers) {
			for (int otherNumber : numbers) {
				for (int yaon : numbers) {
					if (number + otherNumber + yaon == 2020L) {
						System.out.println((long)number * otherNumber * yaon);
					}
				}
			}
		}
	}

}
