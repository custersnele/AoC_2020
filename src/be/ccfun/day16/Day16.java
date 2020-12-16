package be.ccfun.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day16.txt"));
		List<Field> fields = new ArrayList<>();
		String myTicket = "";
		List<Integer> validNumbers = new ArrayList<>();
		List<Integer> invalidNumbers = new ArrayList<>();
		Map<Integer, List<Field>> possibleFields = new HashMap<>();
		int part = 0;
		for (String line : lines) {
			if (line.isBlank()) {
				continue;
			}
			if (line.equals("your ticket:")) {
				part++;

			} else if (line.equals("nearby tickets:")) {
				part++;
			} else if (part == 0) {
				Field newField = new Field(line);
				fields.add(newField);
				validNumbers.addAll(newField.getValidNumbers());
			} else if (part == 1) {
				myTicket = line;
			} else {
				//isValidTicket(line, fields);
				String[] numbers = line.split(",");
				for (String number : numbers) {
					int asNumber = Integer.parseInt(number);
					if (!validNumbers.contains(asNumber)) {
						invalidNumbers.add(asNumber);
					}
				}
			}
		}
		System.out.println(invalidNumbers);
		System.out.println(invalidNumbers.stream().mapToInt(x->x).sum());
	}

	public static boolean isValidTicket(String ticket, List<Field> fields) {
		String[] numbers = ticket.split(",");
		for (int i = 0; i< numbers.length; i++) {
			if (!fields.get(i).isValid(numbers[i])) {
				return false;
			}
		}
		return true;
	}



}
