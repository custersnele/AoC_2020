package be.ccfun.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day22Part2 {

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
		playGame(1, new ArrayList<>(player1), new ArrayList<>(player2));

	}

	public static int playGame(int game, List<Integer> cards1, List<Integer> cards2) {
		LinkedList<Integer> player1 = new LinkedList<>(cards1);
		LinkedList<Integer> player2 = new LinkedList<>(cards2);
		List<String> played1 = new ArrayList<>();
		List<String> played2 = new ArrayList<>();

		System.out.println("Game " + game);
		int round = 1;
		while (!player1.isEmpty() && !player2.isEmpty()) {
			System.out.println("Round "  + round++);
			if (played1.contains(player1.toString()) || played2.contains(player2.toString())) {
				return 1;
			}
			played1.add(player1.toString());
			played2.add(player2.toString());
			int card1 = player1.removeFirst();
			int card2 = player2.removeFirst();
			if (card1 <= player1.size() && card2 <= player2.size()) {
				System.out.println("Play subgame");
				int winner = playGame(game++, new ArrayList<>(player1).subList(0, card1), new ArrayList<>(player2).subList(0, card2));
				if (winner == 1) {
					player1.addLast(card1);
					player1.addLast(card2);
				} else {
					player2.addLast(card2);
					player2.addLast(card1);
				}
			} else if (card1 > card2) {
				player1.addLast(card1);
				player1.addLast(card2);
				System.out.println();
			} else {
				player2.addLast(card2);
				player2.addLast(card1);
			}
		}
		if (player1.isEmpty()) {
			System.out.println(calculateResult(player2));
			return 2;
		} else {
			System.out.println(calculateResult(player1));
			return 1;
		}
	}

	public static long calculateResult(LinkedList<Integer> cards) {
		int i = cards.size();
		long result = 0;
		for (int c = 0; c < cards.size(); c++) {
			result += cards.get(c) * i;
			i--;
		}
		return result;
	}

}
