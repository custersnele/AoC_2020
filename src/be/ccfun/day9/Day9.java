package be.ccfun.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day9.txt"));
		int preambleSize = 25;
		List<Long> summers = strings.subList(0, preambleSize).stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());
		List<Long> checkers = strings.subList(preambleSize, strings.size()).stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());
		long lookingFor = 0l;
		for (Long number : checkers) {
			if (!isValid(number, summers)) {
				lookingFor = number;
				break;
			}
			summers.remove(0);
			summers.add(number);
		}
		List<Long> allNumbers = strings.stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());
		for (int i = 0; i < allNumbers.size(); i++) {
			int index = i;
			List<Long> adders = new ArrayList<>();
			Long nextNumber = allNumbers.get(index++);
			adders.add(nextNumber);
			long sum = nextNumber;
			while (sum < lookingFor) {
				nextNumber = allNumbers.get(index++);
				sum += nextNumber;
				adders.add(nextNumber);
			}
			if (sum == lookingFor && adders.size() > 1) {
				System.out.println("FOUND" + adders);
				long min = adders.stream().mapToLong(x -> x).min().getAsLong();
				long max = adders.stream().mapToLong(x -> x).max().getAsLong();
				System.out.println("SOLUTION: " + (min + max));
			}
		}
	}

	public static boolean isValid(long number, List<Long> summers) {
		for (int i = 0; i < summers.size() - 1; i++) {
			for (int j = i + 1; j < summers.size(); j++) {
				if (number == summers.get(i) + summers.get(j)) {
					return true;
				}
			}
		}
		return false;
	}

}
