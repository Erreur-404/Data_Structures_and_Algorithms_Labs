package Maze;

import java.util.*;
import java.util.stream.Collectors;


public class DFSMaze {
    private static boolean[][] dfsVisited;
    private static Integer distance;
    private static boolean isCompleted;

    private static Counter counter;

    /**
     * Returns the distance of the path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the path within the maze, null if not solvable
     */
    public static Integer findPath(ArrayList<ArrayList<Tile>> maze) {
        counter = new Counter();

        if (maze.isEmpty()) {
            return null;
        }

        dfsVisited = new boolean[maze.size()][maze.get(0).size()];
        distance = 1;
        isCompleted = false;

        Node source = null;
        for (int i = 0; i < maze.size(); i++) {
            Tile possibleEntry = maze.get(i).get(0);

            if (possibleEntry.toString().equals("*")) {
                source = new Node(null, i, 0, distance);
                dfsVisited[i][0] = true;

                // Part 2 ---->
                counter.incrementStackedNodes();
                // <---- Part 2
                break;
            }
        }
        if (source == null) {
            return null;
        }
        return dfs(maze, source);
    }

    public static Integer dfs(ArrayList<ArrayList<Tile>> maze, Node currentNode) {
        dfsVisited[currentNode.getX()][currentNode.getY()] = true;
        distance = currentNode.getDistance();

        // Part 2 ---->

        counter.incrementTotalNodesTraversed();
        // <---- Part 2

        for (Node neighbor : currentNode.getNeighbors(maze)) {
            if (!dfsVisited[neighbor.getX()][neighbor.getY()]) {

                // Part 2 ---->
                counter.incrementStackedNodes();
                // <---- Part 2

                if (maze.get(neighbor.getX()).get(neighbor.getY()).toString().equals("*")) {
                    isCompleted = true;
                    // Part 2 ---->
                    System.out.println(
                            "DFS visited a total of " + counter.getTotalNodesTraversed() + " nodes"
                                    + " and stacked a maximum of " + counter.getMaxStackSize() + " nodes."
                    );
                    // <---- Part 2
                }

                if (!isCompleted) {
                    // Part 2 ---->

                    System.out.println("Number of stacked nodes : " + counter.getStackedNodes());
                    // <---- Part 2
                    distance = dfs(maze, neighbor);
                    // Part 2 ---->
                    counter.decrementStackedNodes();
                    // <---- Part 2
                }
            }
        }
        return distance == 0 ? null : distance;
    }


    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

