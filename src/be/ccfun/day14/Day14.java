package be.ccfun.day14;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day14.txt"));
		String mask = "";
		Map<Integer, Long> memory = new HashMap<>();
		for (String line : lines) {
			if (line.startsWith("mask")) {
				mask = line.substring(7);
				System.out.println("SetMask: " + mask);
			} else {
				String[] parts = line.split("=");
				int startIdx = parts[0].indexOf("[");
				int endIdx = parts[0].indexOf("]");
				int idx = Integer.parseInt(parts[0].substring(startIdx + 1, endIdx));
				long value = getValue(Long.toBinaryString(Long.parseLong(parts[1].trim())), mask);
				memory.put(idx, value);
				System.out.println(value + " at pos  " + idx);
				//printMemory(memory);
			}
		}
		System.out.println(memory.values().stream().mapToLong(x -> x).summaryStatistics());
		System.out.println(memory.values().stream().mapToLong(x -> x).sum());
	}

	public static void printMemory(Map<Integer, Long> memory) {
		System.out.println("++++++++++++++++");
		for (int adr : memory.keySet().stream().sorted().collect(Collectors.toList())) {
			System.out.println(adr + ": " + memory.get(adr));
		}
		System.out.println("++++++++++++++++");
	}

	public static long getValue(String value, String mask) {
		value = StringUtils.leftPad(value, 36, '0');
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == 'X') {
				result.append(value.charAt(i));
			} else {
				result.append(mask.charAt(i));
			}
		}
		return Long.parseLong(result.toString(), 2);
	}

}
