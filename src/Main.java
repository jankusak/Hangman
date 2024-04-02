import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Random random = new Random();
        final Scanner scanner = new Scanner(System.in);
        final String words[] = {"java", "skillmea", "python", "programming", "webinar"};
        final String wordToGues = selectWord(words, random);
        String hiddenWord = generateHiddenWord(wordToGues);
        System.out.println("Welcome to Hangman!");
        System.out.println("Guess to word: " + wordToGues);

        final int MAX_INCORRECT_GUESSES = 6;
        int incorrectGuesses = 0;


        while (incorrectGuesses < MAX_INCORRECT_GUESSES && hiddenWord.contains("_")) {
            System.out.println("Enter the letter");
            char guess = scanLetter(scanner);

            // podminka na zjisteni char v hiddenWord
            if (wordToGues.contains(String.valueOf(guess))) {
                // jestli ano tak updatujeme slovo
                hiddenWord = revelLetters(wordToGues, hiddenWord, guess);
                System.out.println("Spravny tip! Mas " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + "pokusu.");

            } else {
                incorrectGuesses++;
                System.out.println("Spatny tip! Mas " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + "pokusu.");
            }

        }
        if (!hiddenWord.contains("_")) {
            System.out.println("Gratulujeme. Uhadl si. Hledane slovo bylo: " + wordToGues);
        } else {
            System.out.println("Mas smulu. Vycerpal si pokusy. Hledane slovo bylo: " + wordToGues);
        }


    }

    public static void scanLetter(Scanner scanner) {
        char gues;
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Delka neni 1 znak. Zadej prosim ! znak.");
                }
                gues = line.charAt(0);
                if (!Character.isLetter(gues)) {
                    throw new Exception("Znak neni pismeno. Prosim vloz pismeno.");
                }
                break;

            } catch (Exception e) {
                System.out.println("Nevalidni vstup: " + e.getMessage());
            }
        }
        return scanner;
    }

    public static String selectWord(String[] words, Random random) {
        //metoda vraci String o stejne delce jako puvodni slovo, ale vsechny znaky jsou "_"
        return words[random.nextInt(words.length)];

    }

    public static String generateHiddenWord(String word) {
        return "_".repeat(word.length());
    }

    public static String revelLetters(String wordToGuess, String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }
        return String.valueOf(hiddenWordChars);
    }
}