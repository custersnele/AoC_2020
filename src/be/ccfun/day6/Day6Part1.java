package be.ccfun.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class Day6Part1 {
	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day6_johan.txt"));
		Iterator<String> iterator = strings.iterator();
		StringBuilder groupString = new StringBuilder();
		long count = 0;
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.isBlank()) {
				count += groupString.chars().distinct().count();
				groupString = new StringBuilder();
			} else {
				groupString.append(line);
			}
		}
		count += groupString.chars().distinct().count();
		System.out.println(count);
	}

}
