package be.ccfun.day20.domino;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DominoPicture<T> {

	private static final int DOMINO_WIDTH = 178;
	private static final int DOMINO_HEIGHT = 90;
	private static final String FORMAT = "png";

	private Map<PixelPosition, Domino<T>> dominos = new HashMap<>();

	public void add(PixelPosition pixelPosition, Domino<T> domino) {
		dominos.put(pixelPosition, domino);
	}

	public void savePicture(Path path) throws IOException {
		int imageWidth = getWidth() * DOMINO_WIDTH;
		int imageHeight = getHeight() * DOMINO_HEIGHT;
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

		for (int h = 0; h < getHeight(); h++) {
			for (int w = 0; w < getWidth(); w++) {
				Domino<T> domino = dominos.get(new PixelPosition(h, w));
				if (domino != null) {
					includeDomino(image, h * DOMINO_HEIGHT, w * DOMINO_WIDTH, domino.getImage());
				}

			}
		}
		ImageIO.write(image, FORMAT, path.toFile());
	}

	/**
	 * Return the number of domino's used on the x-axis of the picture. This is the maximum
	 * @return
	 */
	public int getWidth() {
		return dominos.keySet().stream().mapToInt(pp -> pp.getCol()).max().getAsInt() + 1;
	}

	public int getHeight() {
		return dominos.keySet().stream().mapToInt(pp -> pp.getRow()).max().getAsInt() + 1;
	}


	/**
	 * Include the picture of the given domino in the general picture bufferedImage starting at position (startRow, startCol). Where (0,0) is the left upper
	 * corner of the general picture.
	 *
	 * @param bufferedImage the general picture
	 * @param startRow position on y-axis to include the domino
	 * @param startCol position on x-axis to include the domino
	 * @param domino domino picture to include
	 */
	private void includeDomino(BufferedImage bufferedImage, int startRow, int startCol, BufferedImage domino) {
		for (int h = 0; h < domino.getHeight(); h++) {
			for (int w = 0; w < domino.getWidth(); w++) {
				bufferedImage.setRGB(startCol + w, startRow + h, domino.getRGB(w, h));
			}
		}
	}



}
