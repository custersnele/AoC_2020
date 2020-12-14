package be.ccfun.day14;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14Part2 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day14_real.txt"));
		String mask = "";
		Map<Long, Long> memory = new HashMap<>();
		for (String line : lines) {
			if (line.startsWith("mask")) {
				mask = line.substring(7);
				System.out.println("SetMask: " + mask);
			} else {
				String[] parts = line.split("=");
				int startIdx = parts[0].indexOf("[");
				int endIdx = parts[0].indexOf("]");
				int idx = Integer.parseInt(parts[0].substring(startIdx + 1, endIdx));
				long value = Long.parseLong(parts[1].trim());
				for (long addr : getAddresses(Integer.toBinaryString(idx), mask)) {
					memory.put(addr, value);
					System.out.println(value + " at pos  " + addr);
				}
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

	public static List<Long> getAddresses(String idx, String mask) {
		idx = StringUtils.leftPad(idx, 36, '0');
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == '0') {
				result.append(idx.charAt(i));
			} else {
				result.append(mask.charAt(i));
			}
		}
		String pattern = result.toString();
		int xs = (int) pattern.chars().filter(x -> x == 'X').count();
		int possibilities = (int) Math.pow(2, xs);
		List<Long> numbers = new ArrayList<>();
		for (int i = 0; i < possibilities; i++) {
			String fill = StringUtils.leftPad(Integer.toBinaryString(i), xs, '0');
			long number = fill(pattern, fill);
			numbers.add(number);
		}
		return numbers;
	}

	public static long fill(String mask, String fill) {
		int fi = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == 'X') {
				result.append(fill.charAt(fi++));
			} else {
				result.append(mask.charAt(i));
			}
		}
		return Long.parseLong(result.toString(), 2);
	}

}
