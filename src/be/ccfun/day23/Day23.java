package be.ccfun.day23;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day23 {

	public static void main(String[] args) {
		//LinkedList<Integer> cups = new LinkedList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));
		LinkedList<Integer> cups = new LinkedList<>(Arrays.asList(9,2,5,1,7,6,8,3,4));
		for (int i = 10; i <= 1000000; i++) {
			cups.add(i);
		}
		int numberOfCups = cups.size();
		int currentCup = 0;
		for (int i = 0; i < 10000001; i++) {
			if (i % 1000 == 0) {
				System.out.println("-- move " + (i + 1) + " --");
			}
			List<Integer> pickUp = new ArrayList<>();
			//System.out.print(cups);
			int currentLabel = cups.get(currentCup);
			//System.out.println(currentLabel);
			int index = (currentCup + 1) % numberOfCups;
			pickUp.add(cups.remove(index));
			if (index < currentCup) {
				currentCup--;
			}
			if (index == cups.size()) {
				index = 0;
			}
			pickUp.add(cups.remove(index));
			if (index < currentCup) {
				currentCup--;
			}
			if (index == cups.size()) {
				index = 0;
			}
			pickUp.add(cups.remove(index));
			if (index < currentCup) {
				currentCup--;
			}
			//System.out.println(pickUp);
			int destination = cups.get(currentCup) - 1;
			if (destination == 0) {
				destination = 1000000;
			}
			while (pickUp.contains(destination)) {
				destination = destination - 1;
				if (destination == 0) {
					destination = 100000;
				}
			}
			//System.out.println(destination);
			int indexOf = cups.indexOf(destination);
			cups.add((indexOf + 1) % numberOfCups, pickUp.get(0));
			cups.add((indexOf + 2) % numberOfCups, pickUp.get(1));
			cups.add((indexOf + 3) % numberOfCups, pickUp.get(2));
			currentCup = (cups.indexOf(currentLabel) + 1) % numberOfCups;
		}
		int indexOf = cups.indexOf(1);
		System.out.println(cups.subList(indexOf - 10, indexOf + 10));
		int index = (indexOf + 1) % numberOfCups;
		Integer getal1 = cups.get(index);
		System.out.println(getal1);
		int index1 = (indexOf + 2) % numberOfCups;
		Integer x = cups.get(index1);
		System.out.println(x);
		System.out.println(BigInteger.valueOf(getal1).multiply(BigInteger.valueOf(x)));
	}




}
