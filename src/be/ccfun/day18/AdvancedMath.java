package be.ccfun.day18;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedMath {


	public static long evaluateExpression(String expression) {
		while (expression.contains("(")) {
			int start = expression.indexOf("(");
			int end = indexClosing(expression, start);
			expression = expression.substring(0, start) + evaluateExpression(expression.substring(start + 1, end)) + expression.substring(end + 1);
		}
		return evaluateWithoutParenthesis(expression);
	}

	public static int indexClosing(String expression, int start) {
		int depth = 1;
		for (int i = start + 1; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				depth++;
			} else if (expression.charAt(i) == ')') {
				depth--;
				if (depth == 0) {
					return i;
				}
			}
		}
		return expression.length() + 10;
	}


	public static long evaluateWithoutParenthesis(String expression) {
		List<String> parts = Arrays.stream(expression.split(" ")).collect(Collectors.toList());
		while(parts.contains("+")) {
			int i = parts.indexOf("+");
			long sum = Long.parseLong(parts.get(i-1)) + Long.parseLong(parts.get(i+1));
			parts.add(i-1, String.valueOf(sum));
			parts.remove(i);
			parts.remove(i);
			parts.remove(i);
		}
		long result = 1;
		for (String part: parts) {
			if (!part.equals("*")) {
				result *= Long.parseLong(part);
			}

		}
		return result;
	}

}
