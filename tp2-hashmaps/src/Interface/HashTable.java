package Interface;


import Classes.BaseHashTable;

public interface HashTable<AnyType> {
    void allocateArray(int arraySize);
    int nextPrime( int n );
    boolean isPrime(int n);
    class HashEntry<AnyType> {}
    boolean isActive(int currentPos);
    void rehash();
    int myhash(AnyType x);
    int findPos(AnyType x);
    void insert(AnyType x);
    void remove(AnyType x);
    void makeEmpty();
    boolean contains(AnyType x);
    BaseHashTable.HashEntry<AnyType>[] getArray();
    void setArray(BaseHashTable.HashEntry<AnyType>[] array);
}
