package be.ccfun.day20.domino;

public class PixelPosition {
	private int row;
	private int col;

	public PixelPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PixelPosition pixel = (PixelPosition) o;

		if (row != pixel.row) {
			return false;
		}
		return col == pixel.col;
	}

	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}
}
