import java.io.PrintStream;
import java.util.Scanner;

public class GamesArcade {
	private static Scanner input = new Scanner(System.in);
	private static PrintStream output = System.out;
	private static boolean keepPlaying = true;

	public static void play() {
		showWelcomeMessage();
		mainLoop();
	}

	private static void showWelcomeMessage() {
		output.println("GAMES ARCADE");
		output.println();
	}

	private static void mainLoop() {
		while (keepPlaying) {
			showMenu();
			askUserToChoose();
			int choice = getUserChoice();
			executeChoice(choice);
		}
	}

	private static void showMenu() {
		output.println("Choose one of the following options:");
		output.println("1. Number Guessing Game");
		output.println("2. Word Scramble");
		output.println("3. Quit");
		output.println();
	}

	private static void askUserToChoose() {
		output.print("Enter your choice: ");
	}

	private static int getUserChoice() {
		String s = input.nextLine();
		return Integer.parseInt(s);
	}

	private static void executeChoice(int choice) {
		if (choice == 1) {
			output.println("Starting Number Guessing Game...");
			NumberGuessingGame.play(input, output);
		} else if (choice == 2) {
			output.println("Starting Word Scramble...");
			WordScramble.play(input, output);
		} else if (choice == 3) {
			output.println("Bye!");
			keepPlaying = false;
		} else {
			output.println("I don't know what that is.");
		}
		output.println();
	}

	public static void main(String[] args) {
		play();
	}
}
