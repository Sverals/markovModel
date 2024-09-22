package LanguageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyCounter {
    private Scanner scan;
    private int kValue;
    private Map<String, Markov> distinctKeyMap;

    public FrequencyCounter() {
        this.distinctKeyMap = new HashMap<>();
       this.scan = new Scanner(System.in);

       this.kValue = 0;
    }

    public void getInputAndGenerateKeys() {
        System.out.println("Enter K Value:");
        this.kValue = Integer.parseInt(scan.nextLine());
        System.out.println("Enter a string:");
        String inputString = scan.nextLine();
        this.generateKeys(inputString, this.kValue);
        System.out.println(this);

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
                    distinctKeyMap.get(splitString).add(suffix);
                }

            } else {
                for (String key : distinctKeyMap.keySet()) {
                    if (key.equals(splitString)) {
                        Markov currentMarkov = distinctKeyMap.get(key);
                        if (i + orderKMarkov + 1 < sb.length()) {
                            char suffix = sb.charAt(i + orderKMarkov);
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
