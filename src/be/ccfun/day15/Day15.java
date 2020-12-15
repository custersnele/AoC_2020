package be.ccfun.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 {

	public static void main(String[] args) {
		/*
		For example, suppose the starting numbers are 0,3,6:

Turn 1: The 1st number spoken is a starting number, 0.
Turn 2: The 2nd number spoken is a starting number, 3.
Turn 3: The 3rd number spoken is a starting number, 6.
Turn 4: Now, consider the last number spoken, 6. Since that was the first time the number had been spoken, the 4th number spoken is 0.
Turn 5: Next, again consider the last number spoken, 0. Since it had been spoken before, the next number to speak is the difference between the turn number when it was last spoken (the previous turn, 4) and the turn number of the time it was most recently spoken before then (turn 1). Thus, the 5th number spoken is 4 - 1, 3.
Turn 6: The last number spoken, 3 had also been spoken before, most recently on turns 5 and 2. So, the 6th number spoken is 5 - 2, 3.
Turn 7: Since 3 was just spoken twice in a row, and the last two turns are 1 turn apart, the 7th number spoken is 1.
Turn 8: Since 1 is new, the 8th number spoken is 0.
Turn 9: 0 was last spoken on turns 8 and 4, so the 9th number spoken is the difference between them, 4.
Turn 10: 4 is new, so the 10th number spoken is 0.
		 */
		List<String> numbersAsString = Arrays.asList("12,1,16,3,11,0".split(","));
		List<Integer> numbers = numbersAsString.stream().map(Integer::parseInt).collect(Collectors.toList());
		int curIdx = numbers.size();
		int prevNumber = numbers.get(curIdx - 1);
		while (curIdx <= 2020) {
			if (!numbers.subList(0, curIdx - 1).contains(prevNumber)) {
				prevNumber = 0;

			} else {
				int lastIndex = numbers.subList(0, curIdx - 1).lastIndexOf(prevNumber);
				prevNumber = curIdx - (lastIndex + 1);
			}
			numbers.add(prevNumber);
			//System.out.println(numbers);
			curIdx++;
		}
		System.out.println(numbers.subList(2010, 2020));
	}

}
