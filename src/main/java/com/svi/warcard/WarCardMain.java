package com.svi.warcard;

import com.svi.warcard.models.Game;

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

    public static boolean playAgain(Scanner kbd) {
        while (true) {
            System.out.print("\nPlay Again (y/n): ");
            String input = kbd.nextLine().trim();
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