import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scnr = new Scanner(new File("C:/Users/PC/Documents/test/testing.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while(scnr.hasNext()) {
            words.add(scnr.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        System.out.println("Word Guessing!");

        List<Character> PlayerGuesses = new ArrayList<>();

        int lives = 6;  //You can change the lives here

        System.out.println("\nYou have "+ lives +" lives.");

        while(true) {
            
            printWordState(word, PlayerGuesses);
            getPlayerGuess(keyboard, word, PlayerGuesses);

            if(printWordState(word, PlayerGuesses)) {
                System.out.println("\nYou Win!");
                break;
            }

            System.out.println("\nPlease Enter your guess word:");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("\nYou Win!");
                break;
            } else {
                System.out.println("\nWrong Guess.");
                lives--;
                System.out.println("\nYou have "+ lives +" lives left."); 
            }
            if (lives == 0) {
                System.out.println("\nGame Over!");
                System.out.println("The word was: "+ word);
                break;
            }
        }
    }


    private static boolean getPlayerGuess (Scanner keyboard, String word, List<Character> PlayerGuesses) {

        System.out.println("\nPlease Enter a Letter.");
        String letterguess = keyboard.nextLine();
        PlayerGuesses.add(letterguess.charAt(0));

        return (word.contains(letterguess));
    }

    private static boolean printWordState(String word, List<Character> PlayerGuesses) {

        int correctCount = 0;
        for (int i=0;i<word.length();i++) {
            if (PlayerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
