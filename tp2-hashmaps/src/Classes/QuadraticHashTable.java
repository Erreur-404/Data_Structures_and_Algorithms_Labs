package Classes;

import Interface.HashTable;


public class QuadraticHashTable<AnyType> extends BaseHashTable<AnyType> implements HashTable<AnyType> {

    public QuadraticHashTable() {
        super();
    }

    public QuadraticHashTable(int size) {
        super(size);
    }

    public QuadraticHashTable(AnyType[] elem) {
        super(elem);
    }

    public int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (getArray()[currentPos] != null &&
                !getArray()[currentPos].element.equals(x)) {
            currentPos += offset;  // Compute ith probe
            offset += 2;
            if (currentPos >= getArray().length)
                currentPos %= getArray().length;
        }
        return currentPos;
    }
}
