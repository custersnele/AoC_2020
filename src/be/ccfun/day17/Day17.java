package be.ccfun.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day17 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("resources/day17.txt"));
		List<Cube> cubes = new ArrayList<>();
		int j = 0;
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				Cube cube = new Cube(i, j, 0);
				cube.setState(line.charAt(i) == '#'? State.ACTIVE : State.INACTIVE);
				cubes.add(cube);
			}
			j++;
		}
		int cycle = 0;
		while (cycle < 100) {
			System.out.println(cycle);
			int maxX = cubes.stream().mapToInt(c -> c.getX()).max().getAsInt();
			int minX = cubes.stream().mapToInt(c -> c.getX()).min().getAsInt();
			int maxY = cubes.stream().mapToInt(c -> c.getY()).max().getAsInt();
			int minY = cubes.stream().mapToInt(c -> c.getY()).min().getAsInt();
			int maxZ = cubes.stream().mapToInt(c -> c.getZ()).max().getAsInt();
			int minZ = cubes.stream().mapToInt(c -> c.getZ()).min().getAsInt();
			List<Cube> nextGen = new ArrayList<>();
			for (int z = minZ-1; z <= maxZ+1; z++) {
				for (int x = minX-1; x <= maxX + 1; x++) {
					for (int y = minY - 1; y <= maxY + 1; y++) {
						final int fx = x;
						final int fy = y;
						final int fz = z;
						Optional<Cube> anyCube = cubes.stream().filter(c -> c.getX() == fx && c.getY() == fy && c.getZ() == fz).findAny();
						Cube cube = new Cube(fx,fy,fz);
						if (anyCube.isPresent()) {
							cube = anyCube.get();
						}
						nextGen.add(cube.createNext(cube.getNeigbours(cubes)));
					}
				}
			}
			cubes = nextGen;
			System.out.println(cubes.stream().filter(c -> c.getState() == State.ACTIVE).count());
			cycle++;
		}

	}

}
