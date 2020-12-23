package be.ccfun.day23;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day23Part2 {

	public static void main(String[] args) {
		//LinkedList<Integer> cups = new LinkedList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));
		LinkedList<Integer> cups = new LinkedList<>(Arrays.asList(9,2,5,1,7,6,8,3,4));
		//CircElement current = new CircElement(9);
		CircElement current = new CircElement(9);
		CircElement prev = current;
		Map<Integer, CircElement> allElements = new HashMap<>();
		allElements.put(9, current);
		for (int i : Arrays.asList(2,5,1,7,6,8,3,4)) {
			CircElement next = new CircElement(i);
			allElements.put(i, next);
			prev.setNext(next);
			prev = next;
		}
		for (int i = 10; i <= 1000000; i++) {
			CircElement next = new CircElement(i);
			allElements.put(i, next);
			prev.setNext(next);
			prev = next;
		}
		int max = 1000000;
		prev.setNext(current);
		for (int i = 0; i < 10000000; i++) {
			if (i % 10000 == 0) {
				System.out.println("-- move " + (i + 1) + " --");
			}
			CircElement pickUp = current.getNext();
			current.setNext(pickUp.getNext().getNext().getNext());
			List<Integer> pickUpValues = new ArrayList<>();
			pickUpValues.add(pickUp.getValue());
			pickUpValues.add(pickUp.getNext().getValue());
			pickUpValues.add(pickUp.getNext().getNext().getValue());
			//System.out.println(pickUpValues);
			int currentValue = current.getValue();
			int searchValue = currentValue - 1;
			if (searchValue == 0) {
				searchValue = max;
			}
			while (pickUpValues.contains(searchValue)) {
				searchValue = searchValue - 1;
				if (searchValue == 0) {
					searchValue = max;
				}
			}
			CircElement search = allElements.get(searchValue);
			CircElement afterSearch = search.getNext();
			search.setNext(pickUp);
			pickUp.getNext().getNext().setNext(afterSearch);
			current = current.getNext();
		}
		CircElement one = allElements.get(1);
		int value1 = one.getNext().getValue();
		System.out.println(value1);
		int value2 = one.getNext().getNext().getValue();
		System.out.println(value2);
		System.out.println(BigInteger.valueOf(value1).multiply(BigInteger.valueOf(value2)));
	}


	public static CircElement find(CircElement current, int searchValue) {
		CircElement search = current;
		while (search.getValue() != searchValue) {
			search = search.getNext();
		}
		return search;
	}




}
