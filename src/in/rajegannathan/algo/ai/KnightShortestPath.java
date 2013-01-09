package in.rajegannathan.algo.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KnightShortestPath {
	private Map<Integer, Map<Integer, Integer>> distanceMatrix = new HashMap<Integer, Map<Integer, Integer>>(5);
	private Coordinate target;
	private Coordinate source;

	public KnightShortestPath(Coordinate source, Coordinate target) {
		this.source = source;
		this.target = target;
	}

	public Coordinate getTarget() {
		return target;
	}

	public void setTarget(Coordinate target) {
		this.target = target;
	}

	public Coordinate getSource() {
		return source;
	}

	public void setSource(Coordinate source) {
		this.source = source;
	}

	private void populateDistanceMatrix() {
		for (int i = target.getxPos() - 2; i < target.getxPos() + 3; i++) {
			if (distanceMatrix.get(i) == null) {
				distanceMatrix.put(i, new HashMap<Integer, Integer>(5));
			}
			populateRow(distanceMatrix.get(i), target.getxPos() - i, target.getyPos());
		}
	}

	private void populateRow(Map<Integer, Integer> map, int xDistanceFromTarget, int yDistance) {
		switch (xDistanceFromTarget) {
		case 2:
			map.put(yDistance + 2, 4);
			map.put(yDistance + 1, 1);
			map.put(yDistance + 0, 2);
			map.put(yDistance - 1, 1);
			map.put(yDistance - 2, 4);
			break;
		case 1:
			map.put(yDistance + 2, 1);
			map.put(yDistance + 1, 2);
			map.put(yDistance + 0, 3);
			map.put(yDistance - 1, 2);
			map.put(yDistance - 2, 1);
			break;
		case 0:
			map.put(yDistance + 2, 2);
			map.put(yDistance + 1, 3);
			map.put(yDistance + 0, 0);
			map.put(yDistance - 1, 3);
			map.put(yDistance - 2, 2);
			break;
		case -1:
			map.put(yDistance + 2, 1);
			map.put(yDistance + 1, 2);
			map.put(yDistance + 0, 3);
			map.put(yDistance - 1, 2);
			map.put(yDistance - 2, 1);
			break;
		case -2:
			map.put(yDistance + 2, 4);
			map.put(yDistance + 1, 1);
			map.put(yDistance + 0, 2);
			map.put(yDistance - 1, 1);
			map.put(yDistance - 2, 4);
			break;
		}
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Not a valid invocation. Give source and target");
		}

		Coordinate source = new Coordinate(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		Coordinate target = new Coordinate(Integer.parseInt(args[2]), Integer.parseInt(args[3]));

		System.out.println(source + " " + target);

		KnightShortestPath ksp = new KnightShortestPath(source, target);

		List<Coordinate> shortestPath = ksp.getShortestPath();
		for (Coordinate pos : shortestPath) {
			System.out.println(pos);
		}

	}

	private List<Coordinate> getShortestPath() {
		ArrayList<Coordinate> shortestPath = new ArrayList<Coordinate>();
		this.populateDistanceMatrix();

		Coordinate tmpSource = source;
		
		while (!tmpSource.equals(target)) {
			TreeMap<Integer, Coordinate> tmpCoordinates = new TreeMap<Integer, Coordinate>();
			for(Coordinate pos : getAllKnightMoves(tmpSource)){
				tmpCoordinates.put(getKnightDistanceFromTarget(pos), pos);
			}
			
			Coordinate value = tmpCoordinates.firstEntry().getValue();
			shortestPath.add(value);
			tmpSource = value;
		}

		return shortestPath;
	}

	private List<Coordinate> getAllKnightMoves(Coordinate tmpSource) {
		List<Coordinate> possibleMoves = new ArrayList<Coordinate>();
		int xPos = tmpSource.getxPos();
		int yPos = tmpSource.getyPos();
		possibleMoves.add(new Coordinate(xPos+2, yPos+1));
		possibleMoves.add(new Coordinate(xPos+2, yPos-1));
		possibleMoves.add(new Coordinate(xPos-2, yPos+1));
		possibleMoves.add(new Coordinate(xPos-2, yPos-1));
		possibleMoves.add(new Coordinate(xPos+1, yPos+2));
		possibleMoves.add(new Coordinate(xPos+1, yPos-2));
		possibleMoves.add(new Coordinate(xPos-1, yPos+2));
		possibleMoves.add(new Coordinate(xPos-1, yPos-2));
		return possibleMoves;
	}

	private int getKnightDistanceFromTarget(Coordinate tmpSource) {
		if (tmpSource.equals(target)) {
			return 0;
		}

		int absXDistance = Math.abs(tmpSource.getxPos() - target.getxPos());
		int absYDistance = Math.abs(tmpSource.getyPos() - target.getyPos());
		if (absXDistance >= 3 || absYDistance >= 3) {
			return (absXDistance * absYDistance) + 2;
		}

		if (distanceMatrix.get(tmpSource.getxPos()).get(tmpSource.getyPos()) == 4) {
			return 5;
		}

		return distanceMatrix.get(tmpSource.getxPos()).get(tmpSource.getyPos());
	}

}

class Coordinate {
	int xPos;
	int yPos;

	public Coordinate(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPos;
		result = prime * result + yPos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (xPos != other.xPos)
			return false;
		if (yPos != other.yPos)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + xPos + "," + yPos + ")";
	}
}