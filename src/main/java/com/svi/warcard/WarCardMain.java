package com.svi.warcard;

import com.svi.warcard.game.Game;

import java.util.Scanner;

public class WarCardMain {
    static void main() {
        boolean willPlayAgain = true;
        Scanner scanner = new Scanner(System.in);

        while (willPlayAgain) {
            Game game = new Game(scanner);
            game.run();
            willPlayAgain = playAgain(scanner);
        }
        scanner.close();
    }

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