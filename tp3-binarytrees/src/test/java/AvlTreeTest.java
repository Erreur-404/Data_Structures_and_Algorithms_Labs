import java.util.ArrayList;

class AVLTreeTester {
    public static void main(String[] args) {

        AvlTree<Integer> avl = new AvlTree<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        Integer[] toInsert = {5,4,3,2,1,9,8,7,6};
        for(int i = 0; i < toInsert.length; i++) {
            avl.add(toInsert[i]);
            bst.add(toInsert[i]);
        }

        System.out.println("Parcours préordre de BST: "); bst.printPreOrder(); System.out.println("");
        System.out.println("Parcours en ordre de BST: "); bst.printInOrder(); System.out.println("");
        System.out.println("Parcours postordre de BST: "); bst.printPostOrder(); System.out.println("");

        System.out.println("Parcours préordre de AVL: "); avl.printPreOrder(); System.out.println("");
        System.out.println("Parcours en ordre de AVL: "); avl.printInOrder(); System.out.println("");
        System.out.println("Parcours postordre de AVL: "); avl.printPostOrder(); System.out.println("");

        part2();
        part3();
        System.out.println("============================= End =====================================");
    }

    /*
     * Insert values into a tree and compute the insertion time
     * @param tree The tree into which we want to insert values
     * @param values The values to insert
     * @return The insertion time
     */
    private static <T extends Comparable<T>> Long insertValues(Tree<T> tree, ArrayList<T> values) {
        Counter counter = new Counter();
        counter.beginTime();

        for (T value : values) {
            tree.add(value);
        }
        return counter.endTimer();
    }

    /*
     * The name says it all. This function computes the average search time in the given tree
     * @param tree The tree to search in
     * @param possibleValues An ArrayList containing the possible values to search for
     * @param numberOfSearches The amount of searches to do
     * @return The average search time
     */
    private static <T extends Comparable<T>> Long computeAverageSearchTime(Tree<T> tree,
                                                                           ArrayList<T> possibleValues,
                                                                           Integer numberOfSearches) {
        T currentValue;
        Long averageTime = 0L;
        Counter counter = new Counter();
        for (int i = 0; i < numberOfSearches; i++) {
            currentValue = possibleValues.get((int) (Math.random() * possibleValues.size()));

            counter.beginTime();
            tree.contains(currentValue);
            averageTime += counter.endTimer();
        }
        return averageTime / numberOfSearches;
    }

    /*
     * Performs the lab's part2 tasks
     */
    private static void part2() {
        System.out.println("\n============================ PART 2 ===================================\n");

        ArrayList<Long> insertionTimesAVL = new ArrayList<>();
        ArrayList<Long> insertionTimesBST = new ArrayList<>();
        ArrayList<Long> averageSearchTimesAVL = new ArrayList<>();
        ArrayList<Long> averageSearchTimesBST = new ArrayList<>();

        Integer numberOfXValues = 50000;
        ArrayList<Integer> xValues = new ArrayList<>();
        AvlTree<Integer> avlPart2;
        BinarySearchTree<Integer> bstPart2;
        ArrayList<Integer> randomValues = new ArrayList<>();

        for (int i = 48950; i < numberOfXValues; i += 5) {
            xValues.add(i);
            avlPart2 = new AvlTree<>();
            bstPart2 = new BinarySearchTree<>();

            randomValues.clear();
            for (int j = 0; j < i; j++) {
                randomValues.add((int) (Math.random() * i));
            }
            insertionTimesAVL.add(insertValues(avlPart2, randomValues));
            insertionTimesBST.add(insertValues(bstPart2, randomValues));

            Integer numberOfSearches = i;
            averageSearchTimesAVL.add(computeAverageSearchTime(avlPart2, randomValues, numberOfSearches));
            averageSearchTimesBST.add(computeAverageSearchTime(bstPart2, randomValues, numberOfSearches));
        }
        printInsertion(xValues, insertionTimesAVL, insertionTimesBST);
        printTime(averageSearchTimesAVL, averageSearchTimesBST);

    }

    /*
     * Performs the lab's part3 tasks
     */
    private static void part3() {
        System.out.println("\n============================ PART 3 ===================================\n");

        ArrayList<Long> insertionTimesAVL = new ArrayList<>();
        ArrayList<Long> insertionTimesBST = new ArrayList<>();

        Integer numberOfXValues = 5000;
        ArrayList<Integer> xValues = new ArrayList<>();
        AvlTree<Integer> avlPart3;
        BinarySearchTree<Integer> bstPart3;
        ArrayList<Integer> worstValues = new ArrayList<>();

        for (int i = 4850; i < numberOfXValues; i += 5) {
            xValues.add(i);
            avlPart3 = new AvlTree<>();
            bstPart3 = new BinarySearchTree<>();

            worstValues.clear();
            for (int j = 0; j < i; j++) {
                worstValues.add(i*j);
            }
            insertionTimesAVL.add(insertValues(avlPart3, worstValues));
            insertionTimesBST.add(insertValues(bstPart3, worstValues));
        }
        printInsertion(xValues,insertionTimesAVL,insertionTimesBST);
    }


    private static void printInsertion(ArrayList<Integer> xValues,
                                  ArrayList<Long> insertionTimesAVL,
                                  ArrayList<Long> insertionTimesBST ){
        System.out.println("Number of values in tree (X axis):");
        System.out.println(xValues);
        System.out.println("\nInsertion times for AVL Trees:");
        System.out.println(insertionTimesAVL);
        System.out.println("\nInsertion times for Binary Search Trees:");
        System.out.println(insertionTimesBST);
    }

    private static void printTime(ArrayList<Long> averageSearchTimesAVL,
                                  ArrayList<Long> averageSearchTimesBST ){
        System.out.println("\nSearch times for AVL Trees:");
        System.out.println(averageSearchTimesAVL);
        System.out.println("\nSearch times for Binary Search Trees:");
        System.out.println(averageSearchTimesBST);
    }
}
