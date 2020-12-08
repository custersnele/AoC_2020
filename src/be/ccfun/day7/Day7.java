package be.ccfun.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day7 {

	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day7.txt"));
		Map<String, String> bagRules = new HashMap<>();
		for (String line : strings) {
			String[] split = line.split(" bags contain ");
			bagRules.put(split[0], split[1]);
		}
		Set<String> bags = new HashSet<>();
		countBags(bagRules, "shiny gold", bags);
		System.out.println(bags.size());
	}


	public static void countBags(Map<String, String> bagRules, String bagColor, Set<String> bags) {
		System.out.println("** LOOKING FOR " + bagColor + " ***");
		Set<String> ok = new HashSet<>();
		for (Map.Entry<String, String> entry : bagRules.entrySet()) {
			if (entry.getValue().contains(bagColor)) {
				System.out.println(entry.getKey() + " contain " + entry.getValue());
				bags.add(entry.getKey());
				ok.add(entry.getKey());

			}
		}
		for (String bag : ok) {
			countBags(bagRules, bag, bags);
		}

	}

}
