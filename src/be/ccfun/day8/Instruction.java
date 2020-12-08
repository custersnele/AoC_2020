package be.ccfun.day8;

public class Instruction {

	private String operation;
	private int value;

	public Instruction(String code) {
		String[] parts = code.split(" ");
		this.operation = parts[0];
		this.value = Integer.parseInt(parts[1]);
	}

	public Instruction(String operation, int value) {
		this.operation = operation;
		this.value = value;
	}

	public static Instruction swapInstruction(Instruction original) {
		String operation = original.operation;
		if (operation.equals("nop")) {
			operation = "jmp";
		} else if (operation.equals("jmp")) {
			operation = "nop";
		}
		return new Instruction(operation, original.getValue());
	}

	public String getOperation() {
		return operation;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return operation + " " + value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Instruction that = (Instruction) o;

		if (getValue() != that.getValue()) {
			return false;
		}
		return getOperation() != null ? getOperation().equals(that.getOperation()) : that.getOperation() == null;
	}

	@Override
	public int hashCode() {
		int result = getOperation() != null ? getOperation().hashCode() : 0;
		result = 31 * result + getValue();
		return result;
	}
}
