package be.ccfun.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Day22 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/combat.txt"));
		ArrayDeque<Integer> player1 = new ArrayDeque<>();
		ArrayDeque<Integer> player2 = new ArrayDeque<>();
		boolean turn1 = true;
		for (String line : lines) {
			if (line.equals("Player 1:")) {
				turn1 = true;
			} else if (line.equals("Player 2:")) {
				turn1 = false;
			} else if (line.length() > 0) {
				if (turn1) {
					player1.add(Integer.parseInt(line));
				} else {
					player2.add(Integer.parseInt(line));
				}
			}
		}
		while (!player1.isEmpty() && !player2.isEmpty()) {
			int card1 = player1.removeFirst();
			int card2 = player2.removeFirst();
			if (card1 > card2) {
				player1.addLast(card1);
				player1.addLast(card2);
			} else {
				player2.addLast(card2);
				player2.addLast(card1);
			}
		}
		long result = 0;
		ArrayDeque<Integer> winner = player1.isEmpty()? player2 : player1;
		int i = 1;
		while (!winner.isEmpty()) {
			result += winner.removeLast() * i;
			i++;
		}
		System.out.println(result);

	}

}
