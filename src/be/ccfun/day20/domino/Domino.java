package be.ccfun.day20.domino;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

public class Domino<T> {
	private T left;
	private T right;
	private boolean flipped;
	private ImageIO image;

	public Domino(T left, T right) {
		this.left = left;
		this.right = right;
	}

	public T getLeft() {
		return left;
	}

	public T getRight() {
		return right;
	}

	public void flip() {
		T hulp = left;
		left  = right;
		right = hulp;
	}

	public boolean isDouble() {
		return left.equals(right);
	}

	public boolean matchLeft(Domino other) {
		return left == other.right;
	}

	public boolean matchRight(Domino other) {
		return right == other.left;
	}

	public boolean contains(T value) {
		return left.equals(value) || right.equals(value);
	}

	@Override
	public String toString() {

		return "|" + left + "|" + right + "|";
	}

	public BufferedImage getImage() throws IOException {

		String filename = "resources/domino/domino_" + left + "_" + right + ".png";
		return ImageIO.read(Paths.get(filename).toFile());
	}



}
