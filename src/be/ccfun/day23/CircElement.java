package be.ccfun.day23;

public class CircElement {
	private int value;
	private CircElement next;

	public CircElement(int value) {
		this.value = value;
	}

	public void setNext(CircElement next) {
		this.next = next;
	}

	public CircElement getNext() {
		return next;
	}

	public int getValue() {
		return value;
	}
}
