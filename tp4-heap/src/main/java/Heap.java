//package source;

import java.util.ArrayList;
public class Heap<AnyType extends Comparable<AnyType>> {

    private final ArrayList<AnyType> values;
    private final boolean isMaxHeap;
    private Integer currentSize;
    /**
     * Heap Constructor
     */
    public Heap(ArrayList<AnyType> valuesToAdd)
    {
        this(valuesToAdd, true);
    }

    /**
     * Heap Constructor
     * @param valuesToAdd arrayList that contains the values to heapify
     * @param isMax determine if is a max or min heap
     */
    public Heap(ArrayList<AnyType> valuesToAdd, boolean isMax)
    {
        this.isMaxHeap = isMax;
        this.values = new ArrayList<>();
        this.values.add(valuesToAdd.get(0));
        this.values.addAll(valuesToAdd);
        currentSize = values.size() - 1;
        heapify();
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void heapify() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which to percolate begins.
     */
    private void percolateDown(int hole)
    {
        int child;
        AnyType tmp = values.get(hole);
        boolean childIsGreater;
        for ( ; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && ((!isMaxHeap && (values.get(child + 1).compareTo(values.get(child)) < 0)) ||
                    (isMaxHeap && (values.get(child + 1).compareTo(values.get(child)) > 0)))) {
                child++;
            }

            childIsGreater = values.get(child).compareTo(tmp) > 0;
            if ((!isMaxHeap && !childIsGreater) ||
                    (isMaxHeap && childIsGreater)) {
                values.set(hole, values.get(child));
            }
            else {
                break;
            }
        }
        values.set(hole, tmp);
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert(AnyType x)
    {
        int hole = ++currentSize;
        boolean isSmallerThanParent = x.compareTo(values.get(hole / 2)) < 0;
        boolean isGreaterThanParent = x.compareTo(values.get(hole / 2)) > 0;

        for ( ; hole > 1 && ((!isMaxHeap && isSmallerThanParent) ||
                (isMaxHeap && isGreaterThanParent)); hole /= 2) {
            if (hole == currentSize) {
                values.add(values.get(hole / 2));
            }
            else {
                values.set(hole, values.get(hole / 2));
            }
            isSmallerThanParent = x.compareTo(values.get(hole / 2)) < 0;
            isGreaterThanParent = x.compareTo(values.get(hole / 2)) > 0;
        }
        values.set(hole, x);
    }

    /**
     * Delete the root element of the array
     * @return the deleted element
     */
    public AnyType delete() throws Exception {
        if (isEmpty()) {
            throw new Exception("The array is empty");
        }
        AnyType rootItem = values.get(1);
        values.set(1, values.remove((int) currentSize--)); // Bien que nous utilisons remove sur un élément autre que le premier, nous
        percolateDown(1);       // sommes dans l'obligation de l'utiliser ainsi puisque, contrairement au code
        return rootItem;              // de Weiss, modifier currentSize ne modifie pas la taille du tableau interne, et
    }                                 // donc remove est notre seul moyen de modifier la taille du tableau pour exclure le dernier élément

    /**
     * Determines if the heap is empty
     * @return boolean. True if empty, else false
     */
    private boolean isEmpty()
    {
        return this.values.toArray().length == 0;
    }

    /**
     * Standard heapsort.
     */
    public void heapsort()
    {
        this.values.remove(0);
        for (int i = values.toArray().length / 2; i >= 0; i--) {
            percDown(values, i, values.toArray().length, isMaxHeap);
        }

        for (int i = values.toArray().length - 1; i > 0; i--) {
            swapReferences(values, i);
            percDown(values, 0, i, isMaxHeap);
        }
        this.values.add(0,this.values.get(0));
    }

    /**
     * Internal method for heapsort
     * @param a: un tableau dont les éléments sont de type Comparable.
     * @int i: la position de l’élément à percoler.
     * @int n: la position après le dernier élément du monceau.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown(ArrayList <AnyType> a, int i, int n, boolean isMaxHeap)
    {
        int child;
        AnyType tmp;
        boolean childIsGreater;
        for (tmp = a.get(i); leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && ((isMaxHeap && (a.get(child + 1).compareTo(a.get(child)) < 0)) ||
                    (!isMaxHeap && (a.get(child + 1).compareTo(a.get(child)) > 0)))) {
                child++;
            }

            childIsGreater = a.get(child).compareTo(tmp) > 0;
            if ((isMaxHeap && !childIsGreater) ||
                    (!isMaxHeap && childIsGreater)) {
                a.set(i, a.get(child));
            } else {
                break;
            }
        }
        a.set(i, tmp);
    }
    /**
     * Internal method for percDown
     * @int i: the position of the element to percoleted.
     */
    private static int leftChild( int i )
    {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort
     * @ArrayList array: the array to effectuate the swap
     * @int i: the position of the element to swap.
     * @int j: the position of the element to swap.
     */
    private void swapReferences(ArrayList<AnyType> array, int j)
    {
        AnyType temp;
        int firstIndex = 0;
        temp = array.get(firstIndex);
        array.set(firstIndex, array.get(j));
        array.set(j, temp);
    }

    public ArrayList<AnyType> getValues() {
        return new ArrayList<>(values.subList(1, values.size()));
    }

    public boolean isMaxHeap() {
        return isMaxHeap;
    }
}
