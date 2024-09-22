package LanguageModel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Markov {
    private String currentSubString;
    private int substringCount;
    private List<Character> suffixes;

    public Markov(String substring) {
        this.suffixes = new LinkedList<>();
        this.currentSubString = substring;
        this.substringCount = 1;
    }

    public void add() {
        this.substringCount++;
    }

    public void add(char c) {
        this.suffixes.add(c);
    }
    @Override
    public String toString() {
        StringBuilder returnStringBuilder = new StringBuilder();
        returnStringBuilder.append(this.substringCount).append(" ").append(this.currentSubString).append(": ");
        Set<Character> uniqueSuffix = new HashSet<>(this.suffixes);
        System.out.println(uniqueSuffix);
        for (Character uniqueChar : uniqueSuffix) {
            int count = 0;
            for (Character currentChar : this.suffixes) {
                if (currentChar.equals(uniqueChar)) {
                    count++;
                }
            }
            returnStringBuilder.append(count).append(" ").append(uniqueChar).append(" ");

        }
        return returnStringBuilder.toString();
    }



}
