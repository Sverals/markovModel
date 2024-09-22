package Logic;

import LanguageModel.FrequencyCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextGenerator {
    private Scanner scan;
    private FrequencyCounter frequencyCounter;
    public TextGenerator(Scanner scan) {
        this.scan = scan;
        this.frequencyCounter = null;
    }


    public void getCLIInputAndGenerateRandom() {
        StringBuilder inputTextBuilder = new StringBuilder();
        System.out.println("Input K order (number):");
        int kOrder = Integer.parseInt(scan.nextLine());
        System.out.println("How many random numbers: ");
        int totalNumbersToGenerate = Integer.parseInt(scan.nextLine());
        try {
            Files.lines(Paths.get("src/main/java/Logic/inputText.txt")).forEach(line -> inputTextBuilder.append(line).append(" "));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.frequencyCounter = new FrequencyCounter(inputTextBuilder.toString().trim(), kOrder, totalNumbersToGenerate);
        String randomString = this.frequencyCounter.generateRandomCharactersBasedOnModel();
        System.out.println("Randomly Generated Text:");
        System.out.println(randomString);

    }
}
