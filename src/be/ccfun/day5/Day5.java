package be.ccfun.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day5 {

	public static void main(String[] args) throws IOException {
		int max = Files.lines(Path.of("resources/day5.txt")).mapToInt(Day5::getSeat).max().getAsInt();
		System.out.println(max);
		List<Integer> seats = Files.lines(Path.of("resources/day5.txt")).map(Day5::getSeat).sorted().collect(Collectors.toList());
		for (int i = 0; i < max; i++) {
			if (!seats.contains(i)) {
				System.out.println(i);
			}
		}
	}

	public static int getSeat(String directions) {
		int row = getRow(directions.substring(0, 7));
		int col = getCol(directions.substring(7));
		return row * 8 +  col;
	}

	public static int getRow(String directions) {
			int from = 0;
			int to = 127;
			for (int i = 0; i < directions.length(); i++) {
				int mid = (int)Math.round((from + to) / 2.0);
				char dir = directions.charAt(i);
				if (dir == 'F') {
					to = mid;
				} else {
					from = mid;
				}
			}
			return from;
	}

	public static int getCol(String directions) {
		int from = 0;
		int to = 7;
		for (int i = 0; i < directions.length(); i++) {
			int mid = (int)Math.round((from + to) / 2.0);
			char dir = directions.charAt(i);
			if (dir == 'L') {
				to = mid;
			} else {
				from = mid;
			}
		}
		return from;
	}

	/*
	Start by considering the whole range, rows 0 through 127.
F means to take the lower half, keeping rows 0 through 63.
B means to take the upper half, keeping rows 32 through 63.
F means to take the lower half, keeping rows 32 through 47.
B means to take the upper half, keeping rows 40 through 47.
B keeps rows 44 through 47.
F keeps rows 44 through 45.
The final F keeps the lower of the two, row 44.
	 */

}
