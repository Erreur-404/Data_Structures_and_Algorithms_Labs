package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class JoinabilityAlgorithmMaze {
    /**
     * Returns the distance of the path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the path within the maze, null if not solvable
     */
     public static Integer findPath(ArrayList<ArrayList<Tile>> maze) {
        Counter counter = new Counter();

        if (maze.isEmpty()) {
            return null;
        }

        Integer distance = 1; // Lorsqu'on est à l'entrée, on a traversé une tuile, ce qui doit être compté
        Stack<Node> nodesToVisit = new Stack<>();
        boolean[][] dfsVisited = new boolean[maze.size()][maze.get(0).size()];

        Node source;
        boolean hasEntry = false;
        for (int i = 0; i < maze.size(); i++) {
            Tile possibleEntry = maze.get(i).get(0);

            if (possibleEntry.toString().equals("*")) {
                source = new Node(null, i, 0, distance);
                nodesToVisit.push(source);
                dfsVisited[i][0] = true;
                hasEntry = true;

                // Part 2 ---->
                counter.incrementStackedNodes();
                // <---- Part 2
                break;
            }
        }
        if (!hasEntry) {
            return null;
        }

        while (!nodesToVisit.isEmpty()) {
            // Part 2 ---->
            System.out.println("Number of stacked nodes : " + counter.getStackedNodes());
            // <---- Part 2

            Node currentNode = nodesToVisit.pop();
            distance = currentNode.getDistance();

            // Part 2 ---->
            counter.decrementStackedNodes();
            counter.incrementTotalNodesTraversed();
            // <---- Part 2
            for (Node neighbor : currentNode.getNeighbors(maze)) {
                if (maze.get(neighbor.getX()).get(neighbor.getY()).toString().equals("*")) {
                    nodesToVisit.clear();
                    break;
                }

                if (!dfsVisited[neighbor.getX()][neighbor.getY()]) {
                    dfsVisited[neighbor.getX()][neighbor.getY()] = true;
                    nodesToVisit.push(neighbor);

                    // Part 2 ---->
                    counter.incrementStackedNodes();
                    // <---- Part 2
                }
            }
        }
        // Part 2 ---->
        System.out.println(
                "DFS visited a total of " + counter.getTotalNodesTraversed() + " nodes"
                + " and stacked a maximum of " + counter.getMaxStackSize() + " nodes."
        );
        // <---- Part 2

        return distance == 0 ? null : distance;
    }


    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}
