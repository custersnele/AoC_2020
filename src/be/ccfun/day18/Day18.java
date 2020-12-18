package be.ccfun.day18;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day18 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day18.txt"));
		BigInteger result = BigInteger.ZERO;
		for (String line : lines) {
			long value = AdvancedMath.evaluateExpression(line);
			System.out.println(value);
			result = result.add(BigInteger.valueOf(value));

		}
		System.out.println(result);
	}

}
