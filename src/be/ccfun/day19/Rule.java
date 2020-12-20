package be.ccfun.day19;

public class Rule {
	private int number;


	public Rule(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public String toANTLR() {
		return toAlphabetic(number) + " : ";
	}


	public static String toAlphabetic(int i) {
		if (i == 0) {
			return "r";
		}
		return "var" + i;
	}

}
