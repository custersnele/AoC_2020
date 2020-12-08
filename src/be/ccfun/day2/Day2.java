package be.ccfun.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {

	public static void main(String[] args) throws IOException {
		System.out.println(isValid2("2-9 c: ccccccccc"));
		List<String> lines = Files.readAllLines(Path.of("resources/day2.txt"));
		long count = lines.stream().filter(Day2::isValid2).count();
		System.out.println(count);
	}


	public static boolean isValid(String line) {
		String[] parts = line.split(":");
		String password = parts[1].trim();
		parts = parts[0].split(" ");
		char character = parts[1].charAt(0);
		parts = parts[0].split("-");
		int min = Integer.parseInt(parts[0]);
		int max = Integer.parseInt(parts[1]);

		int count = 0;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == character) {
				count++;
			}
		}
		return min <= count && count <= max;

	}

	public static boolean isValid2(String line) {
		String[] parts = line.split(":");
		String password = parts[1].trim();
		parts = parts[0].split(" ");
		char character = parts[1].charAt(0);
		parts = parts[0].split("-");
		int pos1 = Integer.parseInt(parts[0]);
		int pos2 = Integer.parseInt(parts[1]);

		return password.charAt(pos1-1) == character ^ password.charAt(pos2-1) == character;

	}

}
