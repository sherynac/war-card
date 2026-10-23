package com.svi.warcard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WarCardMain {
    public static void main(String[] args) {
        boolean playAgain = true;
        Scanner kbd = new Scanner(System.in);

        displayTitle();

        while(playAgain) {
            Game game = new Game(kbd);
            game.run();
            playAgain = willPlayAgain(kbd);
        }
    }

    public static void displayTitle(){
        try (InputStream input = Dealer.class.getClassLoader()
                .getResourceAsStream("title.txt")) {

            if (input == null) {
                System.out.println("Error reading file.");
                System.out.println("Kindly ensure title.txt is included in the application.");
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(input))) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            System.out.println("Kindly double check the text file and rerun the application.");
        }
    }

    public static boolean willPlayAgain(Scanner kbd){
        boolean isValid = false;
        boolean playAgain = false;

        while(!isValid){
            System.out.print("\nPlay Again (y/n): ");
            String input = kbd.nextLine().trim();
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