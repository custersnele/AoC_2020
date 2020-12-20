package be.ccfun.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day19_part2_test.txt"));
		Map<Integer, Rule> rules = new HashMap<>();
		List<String> messages = new ArrayList<>();
		for (String line : lines) {
			if (line.contains(":")) {
				String[] split = line.split(":");
				int ruleNumber = Integer.parseInt(split[0]);
				if (line.contains("\"")) {
					int idx = line.indexOf('\"');
					rules.put(ruleNumber, new EndRule(ruleNumber, line.substring(idx + 1, idx + 2)));
				} else {
					rules.put(ruleNumber, new SubRule(ruleNumber, split[1]));
				}
			} else {
				if (!line.isBlank()) {
					messages.add(line);
				}
			}
		}
		int maxLength = messages.stream().mapToInt(s -> s.length()).max().getAsInt();
		System.out.println("Test");
		List<String> matches = ((SubRule) rules.get(0)).getMatches(rules, maxLength, 1);
		int count = 0;
		for (String message : messages) {
			if (matches.contains(message)) {
				count++;
			}
		}
		System.out.println(count);
	}
}
