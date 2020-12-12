package be.ccfun.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day12Part2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day12.txt"));
		int east = 0;
		int north = 0;
		int wpeast = 10;
		int wpnorth = 1;
		for (String line : lines) {
			char direction = line.charAt(0);
			int number = Integer.parseInt(line.substring(1));
			if (direction == 'F') {
				east += wpeast * number;
				north += wpnorth * number;
			} else if (direction == 'L' || direction == 'R') {
				Waypoint waypoint = new Waypoint(wpnorth, wpeast);
				waypoint.rotate(direction == 'R', number);
				wpnorth = waypoint.getNorth();
				wpeast = waypoint.getEast();
			} else {
				switch (direction) {
					case 'E':
						wpeast += number;
						break;
					case 'W':
						wpeast -= number;
						break;
					case 'N':
						wpnorth += number;
						break;
					case 'S':
						wpnorth -= number;
						break;
				}
			}
			System.out.println(east + " " + north);
		}
		System.out.println(east + " " + north + " " + (Math.abs(east) + Math.abs(north)));
	}



}
