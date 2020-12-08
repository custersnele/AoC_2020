package be.ccfun.day8;

import java.util.List;
import java.util.stream.Collectors;

public class Executor {
	private List<Instruction> program;
	private int accumulator = 0;
	private int currentInstruction = 0;
	private boolean[] executed;

	public Executor(List<String> programAsString, boolean test) {
		program = programAsString.stream().map(Instruction::new).collect(Collectors.toList());
		executed = new boolean[program.size()];
	}

	public Executor(List<Instruction> program) {
		this.program = program;
		executed = new boolean[program.size()];
	}

	public void run() {
		boolean inifiteLoop = false;
		while (currentInstruction < program.size()) {
			if (executed[currentInstruction]) {
				inifiteLoop = true;
				break;
			}
			executed[currentInstruction] = true;
			execute(program.get(currentInstruction));
		}
		if (!inifiteLoop) {
			System.out.println(accumulator);
		}
	}


	public void execute(Instruction instruction) {
		//System.out.println(instruction);
		switch (instruction.getOperation()) {
			case "acc": accumulator += instruction.getValue(); currentInstruction++; break;
			case "jmp": currentInstruction += instruction.getValue(); break;
			case "nop": currentInstruction++; break;
		}
	}





}
