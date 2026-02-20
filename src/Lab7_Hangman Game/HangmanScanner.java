package Lab7;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
/**
 * Name: Jieping Zhou; Student Number: 24250243
 * Name: Eoghan Rainey; Student Number: 25259006
 * Hangman Game (Scanner Version)
 * This version uses Scanner to read words from a file.
 * High scores are saved using FileWriter.
 */
public class HangmanScanner {
    //file names
    private static final String WORDS_FILE = "words.txt";
    private static final String HIGH_SCORES_FILE = "high_scores.txt";
    //Scanner for user input
    private static Scanner input = new Scanner(System.in);

    //program entry point
    public static void main(String[] args) {
        //Load words using Scanner
        List<String> words = readWords(); 

        if (words.isEmpty()) { 
            System.out.println("Word file is empty or missing."); 
            return; 
        } 

        System.out.println("===== Welcome to Hangman =====");

        boolean playAgain ;
        do{
           playGame(words); 
           //Play the game 
           System.out.print("Do you want to play again? (y/n): "); 
           String response = input.nextLine();
           playAgain = response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes");
        }while(playAgain);
        System.out.println("Thanks for playing!");
        }
    /**
     * Read words from words.txt using Scanner
     */
    private static List<String> readWords() {
        List<String> words = new ArrayList<>();
        
        // Reading with Scanner
        try {
            File file = new File(WORDS_FILE);
            Scanner filescanner = new Scanner(file);
            //Read file line by line
            while (filescanner.hasNextLine()) {
                String word = filescanner.nextLine().trim().toLowerCase();
                words.add(word);
            }
            filescanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return words;
}               
    //Main game logic
    public static void playGame(List<String> words) { 
        Random random = new Random(); 
        String  secretWord = words.get(random.nextInt(words.size()));
        // Display array(_ _ _)
        char[] display = new char[secretWord.length()]; 
        for (int i = 0; i < display.length; i++) { 
            display[i] = '_'; 
            } 
            // Game state variables 
            //Dynamic attempts based on word length
            int attemptsLeft = secretWord.length();
            //Store guessed letters to prevent duplicates
            List<Character> guessedLetters = new ArrayList<>();
            System.out.println("\nYou have " + attemptsLeft + " incorrect attempts.");
            //Game loop
            while(attemptsLeft > 0 && new String(display).contains("_")) { 
                System.out.println("Current word: " + String.valueOf(display));
                System.out.println("Guessed letters: " + guessedLetters); 
                System.out.print("Enter a letter: "); 
                String guessInput = input.nextLine().trim().toLowerCase(); 
                  //Input validation :The input length is not 1 or the input characters are not letters
                if (guessInput.length() != 1 || !Character.isLetter(guessInput.charAt(0))){ 
                    System.out.println("Please enter a single letter."); 
                    continue; //Skip this loop and proceed to the next one
                } 
                char guess = guessInput.charAt(0); 
                  //check if letter was already guessed
                if (guessedLetters.contains(guess)){ 
                   System.out.println("You already guessed that letter!"); 
                   continue; 
                } 
                guessedLetters.add(guess); 
                  //Check if guess is correct
                if (secretWord.indexOf(guess) >= 0) { 
                    //Correct guess ,reveal matching letters in display
                    for (int i = 0; i < secretWord.length(); i++){ 
                    if (secretWord.charAt(i) == guess) { 
                        display[i] = guess; 
                    } 
                } 
                    System.out.println("Good guess!"); 
                } else { //Incorrect guess 
                    attemptsLeft--; 
                    System.out.println("Wrong! Attempts left: " + attemptsLeft); 
                }
             } 
             //Check win or lose
                if (!new String(display).contains("_")) { 
                    //Player wins 
                    System.out.println("\nCongratulations! You guessed the word: " + secretWord); 
                    saveHighScore(attemptsLeft); //Save high score based on remaining attempts
                } else { 
                    //Player loses 
                    System.out.println("\nGame Over! The word was: " + secretWord); 
                    saveHighScore(0); //Save score even when losing
                }
}
//Save high score using FileWriter
private static void saveHighScore(int score){ 
    System.out.print("Enter your name: ");
    String name = input.nextLine().trim(); 
    try {
        FileWriter writer = new FileWriter(HIGH_SCORES_FILE, true);
        //save as CSV format
        writer.write(name +"," + score + "\n");
        writer.close();
        System.out.println("High score saved!");
    } catch (IOException e) {
        System.out.println("Error saving high score: " + e.getMessage());
    }
}
}


