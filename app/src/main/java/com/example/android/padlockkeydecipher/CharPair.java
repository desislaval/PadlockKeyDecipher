package com.example.android.padlockkeydecipher;

public class CharPair implements Comparable<CharPair> {
    private char ch;
    private int count;

    //Conctructor fot the custom clas, witch holds the char letter and number of times it occurred in the string
    public CharPair(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }

    //The sorting logic of the ArrayList. Sorting is descending
    @Override
    public int compareTo(CharPair that) {
        if (this.getCount() == that.getCount()) {
            return 0;
        } else if (this.getCount() < that.getCount()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getCount() {
        return count;
    }

    public char getChar() {
        return ch;
    }
}
