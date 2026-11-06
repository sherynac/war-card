package com.svi.warcard;

import com.svi.warcard.game.Game;

import java.util.Scanner;

/**
 * Serves as the entry point of the War Card game application.
 * Manages game initialization and replay prompts.
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
public class Main {

    /**
     * Starts the game and manages the replay loop.
     */
    static void main() {
        try {
            boolean willPlayAgain = true;
            Scanner scanner = new Scanner(System.in);

            while (willPlayAgain) {
                Game game = new Game(scanner);
                game.run();
                willPlayAgain = playAgain(scanner);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("\nProgram has encountered an error: " + e.getMessage() );
            System.out.println("Kindly rerun the program.");
        }

    }

    /**
     * Prompts the user to play another game and validates the response.
     *
     * @param scanner the scanner used to read user input
     * @return true if the user chooses to play again; false otherwise
     */
    private static boolean playAgain(Scanner scanner) {
        while (true) {
            System.out.print("\nPlay Again (y/n): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("\n!! Thank you for playing War Card !!");
                return false;
            } else {
                System.out.println("\nMake sure entered value is only either 'y' or 'n'");
            }
        }
    }
}