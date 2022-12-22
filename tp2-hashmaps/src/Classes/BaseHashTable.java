package Classes;

import Interface.HashTable;

import java.util.Arrays;

public abstract class BaseHashTable<AnyType> implements HashTable<AnyType> {

    private final static int DEFAULT_TABLE_SIZE = 11;
    private int currentSize;
    private HashEntry<AnyType>[] array;

    public BaseHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public BaseHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public BaseHashTable(AnyType[] array) {
        this();
        for (AnyType elem : array) {
            insert(elem);
        }
    }

    public void makeEmpty() {
        currentSize = 0;
        Arrays.fill(array, null);
    }

    public void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    public boolean isPrime(int n) {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 ) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public int nextPrime( int n ) {
        if( n <= 0 )
            n = 3;

        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }


    public static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element  = e;
            isActive = i;
        }
    }

    public boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<>(x, true);

        if (++currentSize > array.length/ 2)
            rehash();
    }

    public void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        for (HashEntry<AnyType> elem : oldArray)
            if (elem != null && elem.isActive)
                insert(elem.element);
    }

    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    public HashEntry<AnyType>[] getArray() {
        return array;
    }

    public void setArray(HashEntry<AnyType>[] array) {
        this.array = array;
    }

    public abstract int findPos(AnyType x);
}
