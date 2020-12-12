package be.ccfun.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day12 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day12.txt"));
		int east = 0;
		int north = 0;
		Direction currentDirection = Direction.E;
		for (String line : lines) {
			char direction = line.charAt(0);
			int number = Integer.parseInt(line.substring(1));
			if (direction == 'F') {
				direction = currentDirection.name().charAt(0);
			}
			if (direction == 'L') {
				currentDirection = currentDirection.rotate(-number);
			} else if (direction == 'R') {
				currentDirection = currentDirection.rotate(number);
			} else {
				switch (direction) {
					case 'E':
						east += number;
						break;
					case 'W':
						east -= number;
						break;
					case 'N':
						north += number;
						break;
					case 'S':
						north -= number;
						break;
				}
			}
			System.out.println(east + " " + north);
		}
		System.out.println(east + " " + north + " " + (Math.abs(east) + Math.abs(north)));

	}



}
