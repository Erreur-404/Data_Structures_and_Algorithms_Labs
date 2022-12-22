public class ValueComparable implements Comparable<ValueComparable>{
    private String content;

    private  int valueOfInteger;

    private final int frequency;

    /**
     * ValueComparable Constructor
     * @param string String that contains the value we wish to compare
     */
    public ValueComparable(String string)
    {
        this.content = string;
        char[] arrayCharContent = new char[string.length()];
        this.content.getChars(0,
                this.content.length(),
                arrayCharContent,0);
        this.frequency = numbersOfa(arrayCharContent);
    }

    /**
     * ValueComparable Constructor
     * @param value int that contains the number we wish to compare
     */
    public ValueComparable(int value, int frequency){
        this.valueOfInteger = value;
        this.frequency = frequency;
    }

    /**
     * Compare the frequency of the wanted value
     * @param compare ValueComparable that contains the object and the frequency we
     *                wish to compare
     */
    @Override
    public int compareTo(ValueComparable compare) {
        return this.frequency - compare.frequency;
    }

    private int numbersOfa(char [] arrayOfChar){
        int counter = 0;
        for (char c : arrayOfChar) {
            if (c == 'a') {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Return the int ValueOfInteger
     */
    public int getValueOfInteger() {
        return valueOfInteger;
    }

    /**
     * Return the String content
     */
    public String getContent() {
        return content;
    }
}
