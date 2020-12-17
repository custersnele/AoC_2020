package be.ccfun.day17;

public enum State {
	ACTIVE("#"),
	INACTIVE(".");

	private String symbol;

	State(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
