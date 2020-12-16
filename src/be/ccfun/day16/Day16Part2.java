package be.ccfun.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16Part2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day16.txt"));
		List<Field> fields = new ArrayList<>();
		String myTicket = "";
		List<Integer> validNumbers = new ArrayList<>();
		//List<Integer> invalidNumbers = new ArrayList<>();
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
				for (int i = 0; i < fields.size(); i++) {
					possibleFields.put(i, new ArrayList<>(fields));
				}
			} else if (part == 0) {
				Field newField = new Field(line);
				fields.add(newField);
				validNumbers.addAll(newField.getValidNumbers());
			} else if (part == 1) {
				myTicket = line;
			} else {
				//isValidTicket(line, fields);
				List<Integer> numbers = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
				if (isValidTicket(validNumbers, numbers)) {
					for (int i = 0; i < numbers.size(); i++) {
						List<Field> toRemove = new ArrayList<>();
						for (Field possibleField: possibleFields.get(i)) {
							if (!possibleField.isValid(numbers.get(i))) {
								toRemove.add(possibleField);
							}
						}
						possibleFields.get(i).removeAll(toRemove);
					}
				}
			}
		}
		for (int i = 0; i < fields.size(); i++) {
			System.out.println(i);
			System.out.println(possibleFields.get(i));
		}
		List<Map.Entry<Integer, List<Field>>> sortedEntrySet = possibleFields.entrySet().stream()
				.sorted((x, y) -> x.getValue().size() - y.getValue().size())
				.collect(Collectors.toList());
		List<Field> found = new ArrayList<>();
		for (int i = 0; i < sortedEntrySet.size(); i++) {
			Map.Entry<Integer, List<Field>> entry = sortedEntrySet.get(i);
			entry.getValue().removeAll(found);
			System.out.println(entry.getKey() + " " + entry.getValue());
			found.addAll(entry.getValue());
		}
	}

	private static boolean isValidTicket(List<Integer> validNumbers, List<Integer> numbers) {
		for (int number : numbers) {
			if (!validNumbers.contains(number)) {
				return false;
			}
		}
		return true;
	}



}
