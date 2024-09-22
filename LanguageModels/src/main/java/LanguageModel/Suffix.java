package LanguageModel;

public class Suffix {
    private char suffix;
    private int count;
    public Suffix(char c) {
        this.suffix = c;
        this.count = 1;
    }

    public char getSuffix() {
        return this.suffix;
    }

    public void add() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }


}
