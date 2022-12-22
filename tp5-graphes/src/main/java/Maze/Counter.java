package Maze;

public class Counter {
    public int totalNodesTraversed = 0;
    public int stackedNodes = 0;
    public int maxStackSize = 0;

    public int getTotalNodesTraversed() {
        return totalNodesTraversed;
    }

    public int getStackedNodes() {
        return stackedNodes;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public void incrementTotalNodesTraversed() {
        totalNodesTraversed++;
    }

    public void incrementStackedNodes() {
        stackedNodes++;
        maxStackSize = Math.max(stackedNodes,maxStackSize);
    }

    public void decrementStackedNodes() {
        stackedNodes--;
    }
}
