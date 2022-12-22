/**
 *
 * @author invite
 */
import java.util.ArrayList;


public class Main {
    final static int rangeNumber = 10;
    final static int arrayLength = 25;
    public static void main(String[] args){

        ArrayList<Integer> arrayListOfRandomNumber1 = new ArrayList<>();
        ArrayList<Integer> arrayListOfRandomNumber2 = new ArrayList<>();
        ArrayList<Integer> arrayListOfRandomNumber3 = new ArrayList<>();

        //This array is used in Part 3
        int [] arrayOfRandomIntPart3 = new int[arrayLength] ;

        for (int i = 0; i < arrayLength; i++) {
            arrayListOfRandomNumber1.add((int) (Math.random() * rangeNumber));
            arrayListOfRandomNumber2.add((int) (Math.random() * rangeNumber));
            arrayListOfRandomNumber3.add((int) (Math.random() * rangeNumber));

            //This array is used in Part 3
            arrayOfRandomIntPart3[i] = ((int)(Math.random() * rangeNumber));
        }

        part2(arrayListOfRandomNumber1, arrayListOfRandomNumber2, arrayListOfRandomNumber3);
        part3(arrayOfRandomIntPart3);


    }

    /**
     * Call all the functions from part2
     * @ArrayList<Integer> arrayListOfRandomNumber1: An arrayList that contains values
     * @ArrayList<Integer> arrayListOfRandomNumber2: An arrayList that contains value
     * @ArrayList<Integer> arrayListOfRandomNumber3: An arrayList that contains value
     */
    public static void part2(ArrayList<Integer> arrayListOfRandomNumber1,
                      ArrayList<Integer>arrayListOfRandomNumber2,
                      ArrayList<Integer>arrayListOfRandomNumber3) {

        Heap<Integer> heapOfRandomNumber1 = new Heap<>(arrayListOfRandomNumber1);
        Heap<Integer> heapOfRandomNumber2 = new Heap<>(arrayListOfRandomNumber2);
        Heap<Integer> heapOfRandomNumber3 = new Heap<>(arrayListOfRandomNumber3);


        System.out.println("Part two :");
        System.out.println(" 3 Arraylists : ");

        System.out.println(arrayListOfRandomNumber1);
        System.out.println(arrayListOfRandomNumber2);
        System.out.println(arrayListOfRandomNumber3);


        System.out.print("\n");
        System.out.println("HeapSort :");

        System.out.println("Before Heap1 : " + heapOfRandomNumber1.getValues());
        System.out.println("Before Heap2: " + heapOfRandomNumber1.getValues());
        System.out.println("Before Heap3: " + heapOfRandomNumber1.getValues());

        heapOfRandomNumber1.heapsort();
        heapOfRandomNumber2.heapsort();
        heapOfRandomNumber3.heapsort();

        System.out.println("After Heap1 : " + heapOfRandomNumber1.getValues());
        System.out.println("After Heap2 : " + heapOfRandomNumber2.getValues());
        System.out.println("After Heap3 : " + heapOfRandomNumber3.getValues());
        System.out.println("------------------------------------------------------------------------------------------------");
    }

    /**
     * Call all the functions from part3
     * @ArrayList<Integer> arrayOfRandomIntPart3: An array that contains values
     */
    public static void part3(int [] arrayOfRandomIntPart3){
        int rankLookingFor = 1;

        System.out.println("Part three : \nRank element we are looking for : "
                + rankLookingFor);


        System.out.println("Element in array of numbers : " );
        for (int i : arrayOfRandomIntPart3) {
            System.out.print(i + ", ");
        }

        System.out.println("Array of numbers : " +
                findElementAccordingToFrequency(arrayOfRandomIntPart3, rankLookingFor));

        String[] arrayString = {"aaa", "abc", "aab", "AaAb", "zsw","aaccaa" };

        System.out.println("Array of Strings : " +
                findElementAccordingToFrequency(arrayString, rankLookingFor));
    }

    /**
     * Find the highest frequency of a number from an array
     * @int [] array: The array that contains the value
     * @int number: The rank of the frequency
     */
    private static int findElementAccordingToFrequency(int [] array, int rank){


        if (rank > array.length || rank <= 0) {
            rank = 1;
        }

        ArrayList<Integer> arrayListOfNumbers = new ArrayList<>();
        for (int k : array) {
            arrayListOfNumbers.add(k);
        }

        Heap<Integer> heapOfNumbers = new Heap<>(arrayListOfNumbers, true);
        //Sorted by values
        heapOfNumbers.heapsort();

        System.out.println("\nSorted Array : \n" + heapOfNumbers.getValues());
        //Sorted by frequency
        ArrayList<ValueComparable> arrayListOfValueComparable = new ArrayList<>();
        int valueOfNumber = heapOfNumbers.getValues().get(0);
        int countingElements = 1;

        for (int j = 0; j < heapOfNumbers.getValues().size(); j++ ) {
            if (valueOfNumber == heapOfNumbers.getValues().get(j)) {
                countingElements++;
            }
            else {
                arrayListOfValueComparable.add(new ValueComparable(valueOfNumber, countingElements));
                countingElements = 1;
                valueOfNumber = heapOfNumbers.getValues().get(j);
            }
        }

        Heap<ValueComparable> heapOfFrequency = new Heap<>(arrayListOfValueComparable,true);
        heapOfFrequency.heapsort();

        return heapOfFrequency.isMaxHeap() ?
                heapOfFrequency.getValues().get(rank - 1).getValueOfInteger() :
                heapOfFrequency.getValues().get(heapOfFrequency.getValues().size() - rank).getValueOfInteger();
    }

    /**
     * Find the highest frequency of a String with "a" from an array
     * @String [] array: The array that contains the value
     * @int number: The rank of the frequency
     */
    private static String findElementAccordingToFrequency(String [] array, int rank){

        if (rank > array.length || rank <= 0){
            rank = 1;
        }

        ArrayList<ValueComparable> arrayListOfStringComparable = new ArrayList<>();
        for (String s : array) {
            ValueComparable stringComparable = new ValueComparable(s);
            arrayListOfStringComparable.add(stringComparable);
        }

        Heap<ValueComparable> heapOfStrings= new Heap<>(arrayListOfStringComparable, false);
        heapOfStrings.heapsort();

        return heapOfStrings.isMaxHeap() ?
                heapOfStrings.getValues().get(rank - 1).getContent() :
                heapOfStrings.getValues().get(heapOfStrings.getValues().size() - rank).getContent();
    }
}
