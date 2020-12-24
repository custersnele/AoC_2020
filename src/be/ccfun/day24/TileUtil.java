package be.ccfun.day24;

public class TileUtil {

	public static void merge(Tile original, Tile duplicate) {
		if (original.getId() == duplicate.getId() || original.getAka().contains(duplicate.getId())) {
			return;
		}
		original.setWhite(original.isWhite() == duplicate.isWhite());
		original.addAka(duplicate.getId());
		for (Direction direction : Direction.values()) {
			Tile neighbour = duplicate.getNeighbour(direction);
			if (neighbour != null) {
				if (original.getNeighbour(direction) == null) {
					original.setNeighbourNotRecur(direction, neighbour);
				} else {
					TileUtil.merge(neighbour, original.getNeighbour(direction));
					original.setNeighbourNotRecur(direction, neighbour);
				}
			}
		}
	}

}
