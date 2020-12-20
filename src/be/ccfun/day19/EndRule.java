package be.ccfun.day19;

public class EndRule extends Rule {

	private String letter;

	public EndRule(int number, String letter) {
		super(number);
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	@Override
	public String toANTLR() {
		return super.toANTLR() + "'" + letter + "';";
	}
}
