package be.ccfun.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day7Part2 {

	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day7.txt"));
		Map<String, List<BagCount>> bagRules = new HashMap<>();
		for (String line : strings) {
			String[] split = line.split(" bags contain ");
			bagRules.put(split[0], createBagCounts(split[1]));
		}
		Set<String> bags = new HashSet<>();
		int shinyGold = countBags(bagRules, "shiny gold");
		System.out.println(shinyGold);
	}

	public static List<BagCount> createBagCounts(String line) {
		String[] split = line.split(",");
		List<BagCount> bagCounts = new ArrayList<>();
		for (String part : split) {
			String[] numberAndColor = part.trim().split(" ");
			if (!numberAndColor[0].equals("no")) {
				bagCounts.add(new BagCount(Integer.parseInt(numberAndColor[0]), numberAndColor[1] + " " + numberAndColor[2]));
			}
		}
		return bagCounts;
	}


	public static int countBags(Map<String, List<BagCount>> bagRules, String bagColor) {
		List<BagCount> bagCounts = bagRules.get(bagColor);
		int count = 0;
		for (BagCount bagCount : bagCounts) {
			int containing = countBags(bagRules, bagCount.getColor());
			count += bagCount.getNumber() + bagCount.getNumber() * containing;
		}
		return count;
	}

}
