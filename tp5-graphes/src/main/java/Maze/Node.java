package Maze;

import java.util.ArrayList;

public class Node {
    private Integer x;
    private Integer y;
    private Node parent;
    private Integer distance;
    private boolean visited;

    public Node(Node parent, Integer x, Integer y, Integer distance) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public ArrayList<Node> getNeighbors(ArrayList<ArrayList<Tile>> maze) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            addToNeighbors(x + i, y, maze, neighbors);
            addToNeighbors(x, y + i, maze, neighbors);
        }
        return neighbors;
    }

    private void addToNeighbors(int x, int y, ArrayList<ArrayList<Tile>> maze,
     ArrayList<Node> neighbors) {
        boolean isParent = ((this.parent != null) && ((parent.getX() == x) && (parent.getY() == y)));
        if (isNeighbor(x, y, maze) && !isParent) {
            neighbors.add(new Node(this, x, y, distance + 1));
        }
    }

    private boolean isNeighbor(int x, int y, ArrayList<ArrayList<Tile>> maze) {
        if (((0 <= x) && (x < maze.size())) && ((0 <= y) && (y < maze.get(0).size()))) {
            String possibleNeighbor = maze.get(x).get(y).toString();
            boolean isFloor = possibleNeighbor.equals("_");
            boolean isExit = possibleNeighbor.equals("*");
            if (isFloor || isExit) {
                return true;
            }
        }
        return false;
    }
}
