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
    private String firstKey;

    public FrequencyCounter(String inputString, int kValue, int totalRandomNumbers) {
        this.inputString = inputString.replaceAll(" ", "_").replaceAll("\\s+", "");
        System.out.println(this.inputString);
        this.distinctKeyMap = new HashMap<>();
        this.kValue = kValue;
        this.totalRandomNumbers = totalRandomNumbers;

    }

    public String generateRandomCharactersBasedOnModel() {
        this.generateKeys(this.kValue);
        StringBuilder randomText = new StringBuilder();
        StringBuilder currentKeyPair = new StringBuilder();
        currentKeyPair.append(inputString, 0, kValue);
        for (int i = 0; i < this.totalRandomNumbers; i++) {
            char randomChar = ' ';
            if (distinctKeyMap.containsKey(currentKeyPair.toString())) {
               randomChar = distinctKeyMap.get((currentKeyPair.toString())).random();
            }
            if (randomChar == ' ') {
                randomChar = distinctKeyMap.get(firstKey).random();
                currentKeyPair.delete(0, currentKeyPair.length());
                currentKeyPair.append(firstKey);
            }
            randomText.append(randomChar);
            currentKeyPair.append(randomChar);
            currentKeyPair.deleteCharAt(0);
            if (i % 100 == 0) {
                randomText.append("\n");
            }
        }
        return randomText.toString();
    }


    private void generateKeys(int orderKMarkov) {
        ArrayList<String> allKeys = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(this.inputString);

        for (int i = 0; i < sb.length(); i++) {
            if ((sb.length() - 1) <= i + orderKMarkov) {
                break;
            }

            String splitString = sb.substring(i, i + orderKMarkov);
            if (i == 0) {
                firstKey = splitString;
            }

            if (!distinctKeyMap.containsKey(splitString)) {
                if (i + orderKMarkov <= sb.length()) {
                    distinctKeyMap.putIfAbsent(splitString, new Markov(splitString));
                    if (!allKeys.contains(splitString)) {
                        allKeys.add(splitString);
                    }
                    char suffix = sb.charAt(i + orderKMarkov);
                    if (suffix == ' ') {
                        suffix = '_';
                    }
                    if (suffix == '\n') {
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
                            if (suffix == '\n') {
                                suffix = '_';
                            }
                            currentMarkov.add(suffix);
                        }
                        currentMarkov.add();
                    }
                }
            }
        }
        ArrayList<String> itemsToRemove = new ArrayList<>();

        for (String key : distinctKeyMap.keySet()) {
            Markov currentMarkov = distinctKeyMap.get(key);
            if (currentMarkov.getSuffixList().isEmpty()) {
                itemsToRemove.add(key);
            }
            for (Suffix s : currentMarkov.getSuffixList()) {
                if (s.getCount() == 0) {
                    itemsToRemove.add(key);
                }
            }
        }
        for (String key : itemsToRemove) {
            distinctKeyMap.remove(key);
        }
        System.out.println(distinctKeyMap);



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
