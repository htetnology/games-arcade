
//Thinzar Htet 180295461

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
	private static Random random = new Random();
	private static int numberToGuess = random.nextInt(5000);
	private static int numberOfGuesses;

	public static void askUserToGuessRandomNumber(PrintStream output) {
		output.print("Try to guess the number I'm thinking of: ");
	}

	private static void loopUntilUserGuessesCorrectly(Scanner input, PrintStream output, int numberToGuess) {
		int guess;
		boolean userGuessedWrongly = true;
		for (numberOfGuesses = 1; userGuessedWrongly;) {

			do {
				askUserToGuessRandomNumber(output);
				guess = getUserGuess(input);
				boolean guessIsSmallerThanNumberToGuess = guess < numberToGuess;
				boolean guessIsLargerThanNumberToGuess = guess > numberToGuess;
				userGuessedWrongly = didUserGuessNumber(numberToGuess, guess);
				String feedback = getAndReturnFeedbackMessageForUser(userGuessedWrongly,
						guessIsSmallerThanNumberToGuess, guessIsLargerThanNumberToGuess);
				showFeedbackMessageToUser(feedback);
				numberOfGuesses++;

			} while (userHasNotGuessedNumberAndHasGuessesRemaining(numberOfGuesses, userGuessedWrongly));
		}
	}

	private static int getUserGuess(Scanner in) {
		return in.nextInt();
	}

	private static boolean didUserGuessNumber(int numberToGuess, int guess) {
		return numberToGuess == guess;
	}

	public static int readAndReturnUserGuessAtRandomNumber(Scanner in) {
		int guess = Integer.parseInt(in.nextLine());
		return guess;
	}

	public static boolean guessIsSmallerThanNumberToGuess(int guess, int numberToGuess) {
		return guess < numberToGuess;
	}

	public static boolean guessIsLargerThanNumberToGuess(int guess, int numbertoGuess) {
		return guess > numberToGuess;
	}

	public static String getAndReturnFeedbackMessageForUser(boolean correct, boolean guessIsSmallerThanNumberToGuess,
			boolean guessIsLargerThanNumberToGuess) {
		if (correct) {
			return "Correct! The number of guesses you made was: " + numberOfGuesses;
		} else if (guessIsSmallerThanNumberToGuess) {
			return "Too low - guess again ";
		} else if (guessIsLargerThanNumberToGuess) {
			return "Too high - guess again ";
		} else {
			return "Invalid input - guess again ";
		}
	}

	public static void showFeedbackMessageToUser(String feedback) {
		System.out.println(feedback);
	}

	private static boolean userHasNotGuessedNumberAndHasGuessesRemaining(int numberOfGuesses,
			boolean guessedTheNumberCorrectly) {
		return numberOfGuesses > 0 && !guessedTheNumberCorrectly;
	}

	public static void play(Scanner input, PrintStream output) {
		loopUntilUserGuessesCorrectly(input, output, numberToGuess);
	}

	public static void main(String[] args) {
		NumberGuessingGame.play(new Scanner(System.in), System.out);
	}
}
