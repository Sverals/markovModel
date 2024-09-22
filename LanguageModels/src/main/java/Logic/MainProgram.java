package Logic;

import LanguageModel.FrequencyCounter;

import java.util.Scanner;

public class MainProgram {


    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        TextGenerator textGenerator = new TextGenerator(scan);
        textGenerator.getCLIInputAndGenerateRandom();
    }
}
