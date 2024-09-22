package LanguageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyCounter {
    private String inputString;
    private int kValue;
    private Map<String, Markov> distinctKeyMap;
    private int totalRandomNumbers;

    public FrequencyCounter(String inputString, int kValue, int totalRandomNumbers) {
        this.inputString = inputString;
        this.distinctKeyMap = new HashMap<>();
        this.kValue = kValue;
        this.totalRandomNumbers = totalRandomNumbers;

    }

    public String generateRandomCharactersBasedOnModel() {
        this.generateKeys(this.inputString, this.kValue);
        StringBuilder randomText = new StringBuilder();
        StringBuilder currentKeyPair = new StringBuilder();
        currentKeyPair.append(this.inputString, 0, kValue);
        for (int i = 0; i < this.totalRandomNumbers; i++) {
            char randomChar = distinctKeyMap.get(currentKeyPair.toString()).random();
            randomText.append(randomChar);
            currentKeyPair.append(randomChar);
            currentKeyPair.deleteCharAt(0);
        }
        return randomText.toString();
    }


    private void generateKeys(String inputString, int orderKMarkov) {
        StringBuilder sb = new StringBuilder();
        sb.append(inputString);

        for (int i = 0; i < sb.length(); i++) {
            if (i + orderKMarkov > sb.length()) {
                break;
            }
            String splitString = sb.substring(i, i + orderKMarkov);

            if (!distinctKeyMap.containsKey(splitString)) {
                distinctKeyMap.putIfAbsent(splitString, new Markov(splitString));
                if (i + orderKMarkov + 1 <= sb.length()) {
                    char suffix = sb.charAt(i + orderKMarkov);
                    if (suffix == ' ') {
                        suffix = '_';
                    }
                    distinctKeyMap.get(splitString).add(suffix);
                }

            } else {
                for (String key : distinctKeyMap.keySet()) {
                    if (key.equals(splitString)) {
                        Markov currentMarkov = distinctKeyMap.get(key);
                        if (i + orderKMarkov + 1 < sb.length()) {
                            char suffix = sb.charAt(i + orderKMarkov);
                            if (suffix == ' ') {
                                suffix = '_';
                            }
                            currentMarkov.add(suffix);
                        }
                        currentMarkov.add();
                    }
                }
            }
        }



    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.distinctKeyMap.size()).append(" distinct keys\n");
        for (String key : distinctKeyMap.keySet()) {
            sb.append(distinctKeyMap.get(key).toString()).append("\n");
        }
        return sb.toString();
    }
}
