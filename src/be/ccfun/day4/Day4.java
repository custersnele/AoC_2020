package be.ccfun.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {

	private static final String HEX_PATTERN = "^#([a-f0-9]{6})$";
	private static final String PID_PATTERN = "^([0-9]{9})$";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day4_johan.txt"));
		StringBuilder id = new StringBuilder();
		int count = 0;
		for (String line : lines) {
			if (line.isBlank()) {
				System.out.println(id);
				if (isValid2(id.toString())) {
					count++;
					System.out.println("VALID");
				}
				id = new StringBuilder();
			} else {
				id.append(line).append(" ");
			}
		}
		System.out.println(id);
		if (isValid2(id.toString())) {
			count++;
			System.out.println("VALID");
		}
		System.out.println(count);
	}


	public static boolean isValid(String data) {
		List<String> requiredFields = Arrays.asList("byr",
				"iyr",
				"eyr",
				"hgt",
				"hcl",
				"ecl",
				"pid"//,
				//"cid"
		);

		for (String field : requiredFields) {
			if (!data.contains(field)) {
				return false;
			}
		}
		return true;
	}


	public static boolean isValid2(String data) {
		if (!isValid(data)) {
			return false;
		}
		String[] parts = data.split(" ");
		for (String part : parts) {
			String[] keyValue = part.split(":");
			switch (keyValue[0]) {
				case "byr":
					if (!isDigits(keyValue[1], 4, 1920, 2002)) {
						return false;
					}
					break;
				case "iyr":
					if (!isDigits(keyValue[1], 4, 2010, 2020)) {
						return false;
					}
					break;
				case "eyr":
					if (!isDigits(keyValue[1], 4, 2020, 2030)) {
						return false;
					}
					break;
				case "hgt":
					if (!isHeight(keyValue[1])) {
						return false;
					}
					break;
				case "hcl":
					if (!isColor(keyValue[1])) {
						return false;
					}
					break;
				case "ecl":
					if (!Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(keyValue[1])) {
						return false;
					}
					break;
				case "pid":
					if (!isPid(keyValue[1])) {
						return false;
					}
					break;
			}
		}
		return true;
	}

	public static boolean isDigits(String text, int number, int min, int max) {
		if (text.length() != number) {
			return false;
		}
		try {
			int value = Integer.parseInt(text);
			return value >= min && value <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isHeight(String text) {
		//hgt (Height) - a number followed by either cm or in:
		//If cm, the number must be at least 150 and at most 193.
		//If in, the number must be at least 59 and at most 76.
		if (text.endsWith("cm") || text.endsWith("in")) {
			try {
				int value = Integer.parseInt(text.substring(0, text.length() - 2));
				if (text.endsWith("cm")) {
					return value >= 150 && value <= 193;
				} else if (text.endsWith("in")) {
					return value >= 59 && value <= 76;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	public static boolean isColor(String text) {
		return text.matches(HEX_PATTERN);
	}

	public static boolean isPid(String text) {
		return text.matches(PID_PATTERN);
	}

}
