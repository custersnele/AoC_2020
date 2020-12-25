package be.ccfun.day25;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day25 {

	public static void main(String[] args) {

		/**
		 * Then, suppose you know that the door's public key is 17807724.
		 * By the same process, you can determine that the door's loop size is 11,
		 * because transforming the initial subject number of 7 with a loop size of 11 produces 17807724.
		 */
		System.out.println(transform(7, 11));
		/*
		19774466
		7290641
		 */
		List<Long> publicKeys = new ArrayList<>(Arrays.asList(19774466l, 7290641l));

		System.out.println(determineCounter(7, 17807724));
		int loopSize = determineCounter(7, 19774466);
		System.out.println(loopSize);
		int otherLoopSize = determineCounter(7, 7290641);
		System.out.println(otherLoopSize);
		System.out.println(transform(19774466, otherLoopSize));
		System.out.println(transform(7290641, loopSize));
	}


	/**
	 * transforms a subject number. To transform a subject number, start with the value 1. Then, a number of times called the loop size, perform the following steps:
	 * <p>
	 * Set the value to itself multiplied by the subject number.
	 * Set the value to the remainder after dividing the value by 20201227
	 */
	public static int determineCounter(long subjectNumber, int publicKey) {
		long start = 1;
		int count = 0;
		while (start != publicKey) {
			count++;
			start = start * subjectNumber;
			start = start % 20201227;
		}
		return count;
	}

	public static long transform(long subjectNumber, int loopSize) {
		long start = 1;
		for (int i = 0; i < loopSize; i++) {
			start = start * subjectNumber;
			start = start % 20201227;
		}
		return start;
	}


}
