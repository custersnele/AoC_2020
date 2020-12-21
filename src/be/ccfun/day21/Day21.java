package be.ccfun.day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day21.txt"));
		Map<String, List<String>> allergenes = new HashMap<>();
		int recipe = 0;
		Set<String> ingredients = new HashSet<>();
		Map<String, Integer> recipeCount = new HashMap<>();
		for (String line : lines) {
			recipe++;
			int idx = line.indexOf("(");
			List<String> ingredientList = Arrays.stream(line.substring(0, idx).split(" ")).map(s -> s.trim()).collect(Collectors.toList());
			for (String ingredient : ingredientList) {
				if (recipeCount.get(ingredient) != null ) {
					recipeCount.put(ingredient, recipeCount.get(ingredient)+1);
				} else {
					recipeCount.put(ingredient, 1);
				}
			}
			ingredients.addAll(ingredientList);
			List<String> allergeneList = Arrays.stream(line.substring(idx + 10, line.length() - 1).split(",")).map(s -> s.trim()).collect(Collectors.toList());
			for (String allergene : allergeneList) {
				if (allergenes.get(allergene) == null) {
					allergenes.put(allergene, new ArrayList<>(ingredientList));
				} else {
					allergenes.get(allergene).retainAll(ingredientList);
				}
			}
		}

		Set<String> ingredientsWithAllergenes = new HashSet<>();
		for (String allergene : allergenes.keySet()) {
			ingredientsWithAllergenes.addAll(allergenes.get(allergene));
		}
		List<String> removed = new ArrayList<>();
		while (allergenes.values().stream().anyMatch(l -> l.size() > 1)) {
			String toRemove = allergenes.values().stream().filter(l -> l.size() == 1).filter(l -> !removed.contains(l.get(0))).findAny().get().get(0);
			for (List<String> values : allergenes.values()) {
				if (values.size() > 1) {
					values.remove(toRemove);
				}
			}
			removed.add(toRemove);
		}
		long count = 0;
		for (String ingredient : ingredients) {
			if (!ingredientsWithAllergenes.contains(ingredient)) {
				count += recipeCount.get(ingredient);
			}
		}

		List<String> allergenesSorted = new ArrayList<>(allergenes.keySet());
		Collections.sort(allergenesSorted);
		StringBuilder builder = new StringBuilder();
		for (String allergene : allergenesSorted) {
			System.out.println(allergene + " " + allergenes.get(allergene));
			builder.append(allergenes.get(allergene).get(0)).append(",");
		}
		System.out.println(count);

		System.out.println(builder.toString());

	}


}
