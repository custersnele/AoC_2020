package be.ccfun.day13;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day13.txt"));
		int start = Integer.parseInt(lines.get(0));
		int end = start;
		System.out.println(lcm(BigInteger.valueOf(17), lcm(BigInteger.valueOf(15), BigInteger.valueOf(22))));
		List<Integer> busses = Arrays.stream(lines.get(1).split(",")).filter(x -> !x.equals("x")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		/*boolean searching = true;
		while (searching) {
			for (int bus : busses) {
				if (end % bus == 0) {
					System.out.println(end);
					int wait = end - start;
					System.out.println(wait * bus);
					searching = false;
				}
			}
			end++;
		}*/
	}


	public static BigInteger lcm(BigInteger number1, BigInteger number2) {
		BigInteger gcd = number1.gcd(number2);
		BigInteger absProduct = number1.multiply(number2).abs();
		return absProduct.divide(gcd);
	}

	/*public static int gcd(int number1, int number2) {
		if (number1 == 0 || number2 == 0) {
			return number1 + number2;
		} else {
			int absNumber1 = Math.abs(number1);
			int absNumber2 = Math.abs(number2);
			int biggerValue = Math.max(absNumber1, absNumber2);
			int smallerValue = Math.min(absNumber1, absNumber2);
			return gcd(biggerValue % smallerValue, smallerValue);
		}
	} */

}
