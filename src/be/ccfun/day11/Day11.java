package be.ccfun.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day11_real.txt"));
		boolean stateChange = true;
		while (stateChange) {
			stateChange = false;
			List<String> newState = new ArrayList<>();
			for (int i = 0; i < lines.size(); i++) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < lines.get(0).length(); j++) {
					int adj = adjacentOccupiedSeatsv2(lines, i, j);
					char current = lines.get(i).charAt(j);
					if (current == 'L' && adj == 0) {
						builder.append('#');
						stateChange = true;
					} else if (current == '#' && adj >= 5) {
						builder.append('L');
						stateChange = true;
					} else {
						builder.append(current);
					}
				}
				newState.add(builder.toString());
			}
			lines = newState;
			System.out.println(lines);

		}
		int seats = 0;
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				if (lines.get(i).charAt(j) == '#') {
					seats++;
				}
			}
		}
		System.out.println(seats);

	}

	public static int adjacentOccupiedSeats(List<String> waitingRoom, int row, int col) {
		int count = 0;
		boolean up = false;
		boolean down = false;
		boolean right = false;
		boolean left = false;
		if (row - 1 >= 0) {
			up = true;
		}
		if (row + 1 < waitingRoom.size()) {
			down = true;
		}
		if (col - 1 >= 0) {
			left = true;
		}
		if (col + 1 < waitingRoom.get(0).length()) {
			right = true;
		}
		if (up && waitingRoom.get(row - 1).charAt(col) == '#') { // UP
			count++;
		}
		if (down && waitingRoom.get(row + 1).charAt(col) == '#') { // DOWN
			count++;

		}
		if (left && waitingRoom.get(row).charAt(col - 1) == '#') { // LEFT
			count++;
		}
		if (right && waitingRoom.get(row).charAt(col + 1) == '#') { // RIGHT
			count++;
		}
		if (up && left && waitingRoom.get(row - 1).charAt(col - 1) == '#') {
			count++;
		}
		if (up && right && waitingRoom.get(row - 1).charAt(col + 1) == '#') {
			count++;
		}
		if (down && left && waitingRoom.get(row + 1).charAt(col - 1) == '#') {
			count++;
		}
		if (down && right && waitingRoom.get(row + 1).charAt(col + 1) == '#') {
			count++;
		}
		return count;
	}


	public static int adjacentOccupiedSeatsv2(List<String> waitingRoom, int row, int col) {
		int count = 0;
		int up = row - 1;
		while (getSeatState(waitingRoom, up, col) == 0) { // UP
			up--;
		}
		if (getSeatState(waitingRoom, up, col) == 1) {
			count++;
		}
		int down = row + 1;
		while (getSeatState(waitingRoom, down, col) == 0) { // DOWN
			down++;
		}
		if (getSeatState(waitingRoom, down, col) == 1) {
			count++;
		}
		int left = col - 1;
		while (getSeatState(waitingRoom, row, left) == 0) { // LEFT
			left--;
		}
		if (getSeatState(waitingRoom, row, left) == 1) {
			count++;
		}
		int right = col + 1;
		while (getSeatState(waitingRoom, row, right) == 0) { // RIGHT
			right++;
		}
		if (getSeatState(waitingRoom, row, right) == 1) {
			count++;
		}
		left = col - 1;
		up = row - 1;
		while (getSeatState(waitingRoom, up, left) == 0) { // LEFT UP
			left--;
			up--;
		}
		if (getSeatState(waitingRoom, up, left) == 1) {
			count++;
		}
		left = col - 1;
		down = row + 1;
		while (getSeatState(waitingRoom, down, left) == 0) { // LEFT DOWN
			left--;
			down++;
		}
		if (getSeatState(waitingRoom, down, left) == 1) {
			count++;
		}
		up = row - 1;
		right = col + 1;
		while (getSeatState(waitingRoom, up, right) == 0) { // RIGHT UP
			up--;
			right++;
		}
		if (getSeatState(waitingRoom, up, right) == 1) {
			count++;
		}
		down = row + 1;
		right = col + 1;
		while (getSeatState(waitingRoom, down, right) == 0) { // RIGHT DOWN
			down++;
			right++;
		}
		if (getSeatState(waitingRoom, down, right) == 1) {
			count++;
		}
		return count;
	}

	/*
	-1 outside
	1 occupied
	2 empty
	0 no seat
	 */
	public static int getSeatState(List<String> waitingRoom, int row, int col) {
		if (row < 0 || col < 0 || row >= waitingRoom.size() || col >= waitingRoom.get(0).length()) {
			return -1;
		}
		char c = waitingRoom.get(row).charAt(col);
		if (c == 'L') {
			return 2;
		}
		if (c == '#') {
			return 1;
		}
		if (c == '.') {
			return 0;
		}
		return Integer.MAX_VALUE;
	}
}
