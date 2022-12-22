package Classes;

import Interface.HashTable;

public class DoubleHashTable<AnyType> extends BaseHashTable<AnyType> implements HashTable<AnyType> {

    int greatestPrime; 


    public DoubleHashTable() {
        super();
        greatestPrime = getGreatestPrime();
    }

    public DoubleHashTable(int size) {
        super(size);
    }

    public DoubleHashTable(AnyType[] elem) {
        super(elem);
    }

    public int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);
        int greatestPrime = getGreatestPrime();
        int H2 = greatestPrime - (x.hashCode() % greatestPrime);

        while (getArray()[currentPos] != null &&
                !getArray()[currentPos].element.equals(x)) {
            currentPos += H2;
            if (currentPos >= getArray().length)
                currentPos %= getArray().length;
        }
        return currentPos;
    }

    private int getGreatestPrime() {
        for (int i = getArray().length - 1; i > 0; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 3;
    }

}
