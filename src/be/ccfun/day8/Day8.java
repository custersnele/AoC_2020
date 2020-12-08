package be.ccfun.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

	public static void main(String[] args) throws IOException {
		List<String> strings = Files.readAllLines(Path.of("resources/day8.txt"));
		List<Instruction> program = strings.stream().map(Instruction::new).collect(Collectors.toList());
		for (int i = 0; i < program.size(); i++) {
			List<Instruction> newProgram = new ArrayList<>(program);
			Instruction originalInstruction = newProgram.get(i);
			Instruction newInstruction = Instruction.swapInstruction(originalInstruction);
			if (newInstruction.equals(originalInstruction)) {
				continue;
			}
			newProgram.set(i, newInstruction);
			Executor executor = new Executor(newProgram);
			executor.run();
		}

	}



}
