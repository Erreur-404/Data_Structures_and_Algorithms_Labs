package Classes;

import Interface.HashTable;

public class LinearHashTable<AnyType> extends BaseHashTable<AnyType> implements HashTable<AnyType> {

    public LinearHashTable() {
        super();
    }

    public LinearHashTable(int size) {
        super(size);
    }

    public LinearHashTable(AnyType[] elem) {
        super(elem);
    }

    public int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash( x );

        while(getArray()[currentPos] != null &&
                !getArray()[currentPos].element.equals( x ) )
        {
            currentPos = ++currentPos % getArray().length;
        }
        return currentPos;
    }
}
