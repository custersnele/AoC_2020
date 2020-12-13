package be.ccfun.day13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day13Part2 {

	public static void main(String[] args) {

		List<String> numbers = Arrays.asList("19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,643,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,13,x,x,x,x,23,x,x,x,x,x,x,x,509,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29".split(","));
		// https://www.dave4math.com/mathematics/chinese-remainder-theorem/
		// lineair system for example 17,x,13,19
		// t = 17 mod 17
		// t = 11 mod 13
		// t = 16 mod 19
		List<Long> values = new ArrayList<>();
		List<Long> mods = new ArrayList<>();
		for (int i = 0; i < numbers.size(); i++) {
			String elt = numbers.get(i);
			if (!elt.equals("x")) {
				values.add(Long.parseLong(elt) - i);
				mods.add(Long.parseLong(elt));
			}
		}
		long result = calculate(values.stream().mapToLong(x -> x).toArray(), mods.stream().mapToLong(x -> x).toArray());
		System.out.println(result);


	}

	public static long calculate(long[]values, long[]mods) {
		long multiple = LongStream.of(mods).reduce(1, (a, b) -> a * b);
		long sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += chineseStep(values[i], mods[i], multiple);
		}
		return sum % multiple;
	}

	public static long chineseStep(long remainder, long mod, long multiple) {
		long div = multiple / mod;
		BigInteger modInv = BigInteger.valueOf(div).modInverse(BigInteger.valueOf(mod));
		return modInv.longValue() * div * remainder;
	}




}
