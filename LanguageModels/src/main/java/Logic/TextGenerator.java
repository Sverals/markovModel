package Logic;

import LanguageModel.FrequencyCounter;

import java.util.Scanner;

public class TextGenerator {
    private Scanner scan;
    private FrequencyCounter frequencyCounter;
    public TextGenerator(Scanner scan) {
        this.scan = scan;
        this.frequencyCounter = null;
    }


    public void getCLIInputAndGenerateRandom() {
        System.out.println("Input K order (number):");
        int kOrder = Integer.parseInt(scan.nextLine());
        System.out.println("How many random numbers: ");
        int totalNumbersToGenerate = Integer.parseInt(scan.nextLine());
        System.out.println("Enter Text:");
        String stringInput = scan.nextLine();
        this.frequencyCounter = new FrequencyCounter(stringInput, kOrder, totalNumbersToGenerate);
        String randomString = this.frequencyCounter.generateRandomCharactersBasedOnModel();
        System.out.println("Randomly Generated Text:");
        System.out.println(randomString);

    }
}
