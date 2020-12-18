package be.ccfun.day18;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewMath {


	public static long evaluateExpression(String expression) {
		expression = expression.replaceAll("\\s+","");
		List<Character> characterList = expression.chars().mapToObj(s -> Character.valueOf((char) s)).collect(Collectors.toList());
		long currentValue = 0;
		boolean add = true; // add true -> add, otherwise multiply
		List<List<String>> stacks = new ArrayList<>();
		int currStack = -1;
		for (Character character : characterList) {
			if (currStack == -1) {
				if (Character.isDigit(character)) {
					int nextValue = Integer.parseInt(String.valueOf(character));
					if (add) {
						currentValue += nextValue;
					} else {
						currentValue *= nextValue;
					}
				}
				if (character == '*') {
					add = false;
				}
				if (character == '+') {
					add = true;
				}
				if (character == '(') {
					stacks.add(new ArrayList<>());
					currStack = 0;
				}
			} else {
				if (character == ')') {
					long res = evaluateWithoutParenthesis(stacks.remove(currStack));
					currStack--;
					if (currStack >= 0) {
						stacks.get(currStack).add(String.valueOf(res));
					} else {
						if (add) {
							currentValue += res;
						} else {
							currentValue *= res;
						}
					}

				} else if (character == '(') {
					stacks.add(new ArrayList<>());
					currStack++;
				} else {
					stacks.get(currStack).add(String.valueOf(character));
				}
			}

		}
		return currentValue;
	}


	public static long evaluateWithoutParenthesis(List<String> expression) {
		long currentValue = 0;
		boolean add = true; // add true -> add, otherwise multiply
		for (String character : expression) {
			if (StringUtils.isNumeric(character)) {
				long nextValue = Integer.parseInt(String.valueOf(character));
				if (add) {
					currentValue += nextValue;
				} else {
					currentValue *= nextValue;
				}
			}
			if (character.equals("*")) {
				add = false;
			}
			if (character.equals("+")) {
				add = true;
			}
		}
		return currentValue;
	}

}
