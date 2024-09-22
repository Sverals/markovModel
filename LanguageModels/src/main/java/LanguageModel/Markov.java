package LanguageModel;

public class Markov {
    private String currentSubString;
    private int substringCount;

    public Markov(String substring) {
        this.currentSubString = substring;
        this.substringCount = 1;
    }

    public void add() {
        this.substringCount++;
    }
    @Override
    public String toString() {
        return this.substringCount + " " + this.currentSubString;
    }



}
