package be.ccfun.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubRule extends Rule {

	private List<Integer> subRules1;
	private List<Integer> subRules2;

	public SubRule(int number, String rules) {
		super(number);
		setSubRules(rules);
	}

	public void setSubRules(String rule) {
		String[] split = rule.split("\\|");
		subRules1 = createRules(split[0].trim());
		if (split.length > 1) {
			subRules2 = createRules(split[1].trim());
		}
	}

	private List<Integer> createRules(String numbers) {
		String[] s = numbers.split(" ");
		return Arrays.stream(s).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

	public List<String> getMatches(Map<Integer, Rule> rules, int max, int depth) {
		List<String> result = new ArrayList<>();
		if (getNumber() == 8 || getNumber() == 11) {
			depth++;

		}
		if (!subRules1.isEmpty()) {
			result.addAll(createMatches(subRules1, rules, max, depth));
		}
		if ( (getNumber() == 8 || getNumber() == 11) && depth >= 4) {
			return result;
		}
		if (subRules2 != null && !subRules2.isEmpty()) {
			result.addAll(createMatches(subRules2, rules, max, depth));
		}
		return result;
	}

	private List<String> createMatches(List<Integer> subRules, Map<Integer, Rule> rules, int max, int depth) {
		List<String> result = new ArrayList<>();
		for (Integer ruleNumber : subRules) {
			Rule rule = rules.get(ruleNumber);
			if (rule instanceof EndRule) {
				if (result.isEmpty()) {
					result.add(((EndRule) rule).getLetter());
				} else {
					List<String> newList = new ArrayList<>();
					for (String word : result) {
						String newWord = word + ((EndRule) rule).getLetter();
						if (newWord.length() <= max) {
							newList.add(newWord);
						}
					}
					result = newList;
				}
			} else if (rule instanceof SubRule){
				if (result.isEmpty()) {
					result.addAll(((SubRule)rule).getMatches(rules, max, depth));
				} else {
					List<String> newList = new ArrayList<>();
					for (String word : result) {
						for (String next : ((SubRule)rule).getMatches(rules, max, depth)) {
							String newWord = word + next;
							if (newWord.length() <= max) {
								newList.add(newWord);
							}
						}
					}
					result = newList;
				}
			}
		}
		return result;
	}


	@Override
	public String toANTLR() {
		StringBuilder result = new StringBuilder(super.toANTLR());
		for (int number : subRules1) {
			result.append(toAlphabetic(number)).append(" ");
		}
		if (subRules2 != null && !subRules2.isEmpty()) {
			result.append("| ");
			for (int number : subRules2) {
				result.append(toAlphabetic(number)).append(" ");
			}
		}
		result.append(";");
		return result.toString();
	}
}
