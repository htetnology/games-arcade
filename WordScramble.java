
//Thinzar Htet 180295461

/* The game can begin with a general hint before suggesting the first letter of the word. 
 * The hint can be short definitions or category that the word falls under. 
 * For example, "formula" and "medicine" are categorized under "Science". 
 * The third and last hint can be the last letter of the word.
 * For example, the last letter of "mechanic" is "c".
 * With more hints, users are able to construct their answers better. 
   
 * The game can also replace the lives system with a high score system. 
 * Users can continue guessing until correct answer is reached.
 * High score reflects the least number of guesses taken to reach the correct answer. 
 * One guess equates to one point.
 * The lesser the guesses, the better the high score; maximum score of one point. 
 * System updates high score every time a new score is achieved.  
 * For example, if a user took 14 tries to guess the word, the high score is 14 points. 
 * If 10 tries were taken on the 2nd word, the new high score is 10 points.
*/

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class WordScramble {
	private static Random random = new Random();
	private static String[] words = { "formula", "mechanic", "embolden", "scramble", "medicine", "autopilot",
			"photocopier" };
	private static final int NUMBER_OF_LIVES = 2;

	public static void play(Scanner input, PrintStream output) {
		String wordToGuess = getWordToGuess();
		String scrambledWord = scramble(wordToGuess);

		showScrambledWord(output, scrambledWord);
		loopUntilUserGuessesCorrectlyOrRunsOutOfLives(input, output, wordToGuess);
	}

	private static String getWordToGuess() {
		int i = random.nextInt(words.length);
		return words[i];
	}

	private static String scramble(String word) {
		char[] scramble = word.toCharArray();

		int length = scramble.length;
		int counter = length * 2;
		int x, y;
		while (counter > 0) {
			x = random.nextInt(length);
			y = random.nextInt(length);
			swapChars(x, y, scramble);
			counter--;
		}

		return new String(scramble);
	}

	private static void swapChars(int x, int y, char[] scramble) {
		char z = scramble[x];
		scramble[x] = scramble[y];
		scramble[y] = z;
	}

	private static void showScrambledWord(PrintStream output, String scrambledWord) {
		output.println("Unscramble this word: " + scrambledWord);
	}

	private static void loopUntilUserGuessesCorrectlyOrRunsOutOfLives(Scanner input, PrintStream output,
			String wordToGuess) {
		int guesses = NUMBER_OF_LIVES;
		boolean guessedCorrectly;
		do {
			askUserToGuess(output);
			String guess = getUserGuess(input);
			guesses--;
			guessedCorrectly = didUserGuessWord(wordToGuess, guess);
			String message = getFeedbackMessageForUser(guessedCorrectly, guesses, wordToGuess);
			output.println(message);
		} while (userHasNotGuessedWordAndHasGuessesRemaining(guesses, guessedCorrectly));
	}

	private static void askUserToGuess(PrintStream output) {
		output.print("Enter your guess: ");
	}

	private static String getUserGuess(Scanner in) {
		return in.nextLine();
	}

	private static boolean didUserGuessWord(String wordToGuess, String guess) {
		return wordToGuess.equalsIgnoreCase(guess);
	}

	private static String getFeedbackMessageForUser(boolean correct, int guessesLeft, String wordBeingGuessed) {
		if (correct) {
			return "Correct. You win!";
		} else if (!correct && guessesLeft == 1) {
			return "That's not the answer. Here's a hint: " + getHint(wordBeingGuessed);
		} else {
			return "Incorrect. You Lose! The word was '" + wordBeingGuessed + "'.";
		}
	}

	private static boolean userHasNotGuessedWordAndHasGuessesRemaining(int numberOfGuessesLeft,
			boolean guessedTheWordCorrectly) {
		return numberOfGuessesLeft > 0 && !guessedTheWordCorrectly;
	}

	private static String getHint(String word) {
		return "the first letter of the word is " + word.charAt(0);
	}

	public static void main(String[] args) {
		WordScramble.play(new Scanner(System.in), System.out);
	}
}
