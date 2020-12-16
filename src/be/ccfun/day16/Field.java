package be.ccfun.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Field {

	private String name;
	private int[] validFrom= new int[2];
	private int[] validTo = new int[2];

	public Field(String field) {
		String[] parts = field.split(":");
		name = parts[0].trim();
		String[] ranges = parts[1].split(" or ");
		for (int i = 0; i<ranges.length;i++) {

			String[] numbers = ranges[i].split("-");
			validFrom[i] = Integer.parseInt(numbers[0].trim());
			validTo[i] = Integer.parseInt(numbers[1].trim());
		}
	}

	public String getName() {
		return name;
	}

	public List<Integer> getValidNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < validFrom.length; i++) {
			List<Integer> collect = IntStream.rangeClosed(validFrom[i], validTo[i]).boxed().collect(Collectors.toList());
			numbers.addAll(collect);
		}
		return numbers;
	}

	public boolean isValid(int number) {
		for (int i = 0; i< validFrom.length; i++) {
			if (number >= validFrom[i] && number <= validTo[i]) {
				return true;
			}
		}
		return false;
	}

	public boolean isValid(String number) {
		return isValid(Integer.parseInt(number));
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Field)) {
			return false;
		}

		Field field = (Field) o;

		return getName() != null ? getName().equals(field.getName()) : field.getName() == null;
	}

	@Override
	public int hashCode() {
		return getName() != null ? getName().hashCode() : 0;
	}
}
