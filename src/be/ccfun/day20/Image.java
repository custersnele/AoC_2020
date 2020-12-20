package be.ccfun.day20;

public class Image {

	private Tile[][] tiles;
	private int size;

	public Image(int size) {
		tiles = new Tile[size][size];
		this.size = size;
	}

	public void setLeftCorner(Tile tile) {
		tile.align(Direction.RIGHT, Direction.BOTTOM);
		for (int row = 0; row < size; row += 2) {
			tiles[row][0] = tile;
			// first row
			for (int i = 1; i < size; i++) {
				tile = tile.getRightNeighbour();
				tiles[row][i] = tile;
			}
			// second row
			if (row + 1 < size) {
				tile = tile.getBottomNeighbour();
				tiles[row + 1][size - 1] = tile;
				for (int i = size - 2; i >= 0; i--) {
					tile = tile.getLeftNeighbour();
					tiles[row + 1][i] = tile;
				}
				if (row + 2 < size) {
					tile = tile.getBottomNeighbour();
				}
			}
		}
/*
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(tiles[i][j].getNumber() + " ");
			}
			System.out.println();
		}
		*/
	}

	public Tile createTile() {
		Tile tile = new Tile(0);
		int tileSize = tiles[0][0].getSize();
		for (int i = 0; i < this.size; i++) {
			for (int k = 1; k < tileSize - 1; k++) {
				StringBuilder line = new StringBuilder();
				for (int j = 0; j < this.size; j++) {
					line.append(tiles[i][j].getRow(k).substring(1, tileSize - 1));
				}
				tile.addLine(line.toString());
			}
		}
		return tile;
	}

}
