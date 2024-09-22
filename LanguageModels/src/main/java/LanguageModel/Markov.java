package LanguageModel;

import java.util.*;

public class Markov {
    private String currentSubString;
    private int substringCount;
    private List<Character> allSuffixes;
    private List<Suffix> suffixList;

    public Markov(String substring) {
        this.suffixList = new ArrayList<>();
        this.allSuffixes = new LinkedList<>();
        this.currentSubString = substring;
        this.substringCount = 1;
    }

    public void add() {
        this.substringCount++;
    }

    public void add(char c) {
        boolean containsSuffix = false;
        this.allSuffixes.add(c);
        for (Suffix suffix : this.suffixList) {
            if (suffix.getSuffix() == c) {
                suffix.add();
                containsSuffix = true;
                break;
            }
        }
        if (!containsSuffix) {
            this.suffixList.add(new Suffix(c));
        }
    }

    public char random() {
        HashMap<Character, Double> probabilities = new HashMap<>();
        Random rand = new Random();
        double randomNum = rand.nextDouble();
        int totalCount = 0;
        double currentProbability = 0.0;
        for (Suffix suffix : this.suffixList) {
            totalCount += suffix.getCount();
        }
        for (Suffix suffix : this.suffixList) {
            probabilities.put(suffix.getSuffix(), ((double) suffix.getCount() / (double) totalCount));
        }
        for (Character probChar : probabilities.keySet()) {
            currentProbability += probabilities.get(probChar);
            if (randomNum <= currentProbability) {
                return probChar;
            }
        }
        return '/';


    }
    @Override
    public String toString() {
        StringBuilder returnStringBuilder = new StringBuilder();
        returnStringBuilder.append(this.substringCount).append(" ").append(this.currentSubString).append(": ");
        for (Suffix suffix : this.suffixList) {
            returnStringBuilder.append(suffix.getCount()).append(" ").append(suffix.getSuffix()).append(" ");
        }
        return returnStringBuilder.toString();
    }



}
