package com.svi.warcard;
import java.util.*;

public class WarCardMain {
    public static void main(String[] args) {
        boolean playAgain = true;
        Scanner kbd = new Scanner(System.in);

        while(playAgain) {
            Game game = new Game(kbd);
            game.run();
            playAgain = willPlayAgain(kbd);
        }
    }

    public static boolean willPlayAgain(Scanner kbd){
        boolean isValid = false;
        boolean playAgain = false;

        while(!isValid){
            System.out.print("\nPlay Again (y/n): ");
            String input = kbd.nextLine();
            if (input.equalsIgnoreCase("y")) {
                isValid = true;
                playAgain = true;
            } else if (input.equalsIgnoreCase("n")){
                isValid = true;
                System.out.println("\n!! Thank you for playing War Card !!");
            } else {
                System.out.println("\nMake sure entered value is only either 'y' or 'n'");
            }
        }

        return playAgain;
    }


}