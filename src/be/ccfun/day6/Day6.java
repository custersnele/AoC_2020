package be.ccfun.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class Day6 {

	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day6_johan.txt"));
		Iterator<String> iterator = strings.iterator();
		String groupString = iterator.next();
		long count = 0;
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.isBlank()) {
				count += groupString.length();
				groupString = iterator.next();
			} else {
				groupString = common(groupString, line);
			}
		}
		count += groupString.length();
		System.out.println(count);
	}

	public static String common(String letters, String otherLetters) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i< letters.length(); i++) {
			String substring = letters.substring(i, i + 1);
			if (otherLetters.contains(substring)) {
				result.append(substring);
			}
		}
		return result.toString();
	}

}
