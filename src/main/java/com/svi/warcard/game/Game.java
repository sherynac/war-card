package com.svi.warcard.game;

import com.svi.warcard.utils.ColorHelper;
import com.svi.warcard.models.Player;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages overall game flow including game setup, round creation,
 * player elimination and winner declaration
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
public class Game {
    private final Dealer dealer;
    private final List<Player> players;
    private final Scanner scanner;

    /**
     * Constructs a Game object with provided scanner for user input
     *
     * @param scanner for reading user input
     */
    public Game(Scanner scanner) {
        players = new ArrayList<>();
        dealer = new Dealer();
        this.scanner = scanner;
    }

    /**
     * Executes the main game loop including game setup, round execution,
     * pot collection, player elimination and winner declaration
     */
    public void run() {
        setupGame();
        int roundNum = 1;

        while (!isGameOver()) {
            Round round = new Round(roundNum);
            Player roundWinner = round.startRound(players);
            round.collectPot(roundWinner);
            eliminatePlayers();
            roundNum++;
        }

        declareWinner();
    }

    /**
     * Initializes the game by displaying the title, loading the cards,
     * retrieving player and shuffle count, shuffling the deck,
     * and dealing cards to players.
     */
    private void setupGame() {
        displayTitle();

        try {
            dealer.readDeck();
            System.out.println("\nDeck has been loaded successfully.");
        } catch (IOException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Kindly double check the text file and rerun the application.");
            System.exit(1);
        }

        int playerCount = inputPlayerCount();
        createPlayers(playerCount);

        int shuffleCount = inputShuffleCount();
        System.out.println("\nDealer shows cards: \n" + dealer.showCards());
        dealer.shuffleDeck(shuffleCount);
        System.out.println("\nDealer shuffled cards: \n" + dealer.showCards());

        dealer.dealCards(players);
    }

    /**
     * Displays the game logo and welcome message
     */
    private void displayTitle() {
        try (InputStream input = Game.class.getClassLoader()
                .getResourceAsStream("title.txt")) {

            if (input == null) {
                return;
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(input))) {

                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append("\n").append(line);
                }

                System.out.println(ColorHelper.color(stringBuilder.toString(), "PURPLE"));
            }
        } catch (IOException e) {
            System.out.println("Skipping Game Logo...");
        } finally {
            System.out.println("\n=== Welcome to War Card! ===");
        }
    }

    /**
     * Prompts user for the number of players and validates the input
     *
     * @return validated number of players
     */
    private int inputPlayerCount() {
        int playerCount;

        while (true) {
            try {
                System.out.print("\nEnter number of players (2-8): ");
                playerCount = Integer.parseInt(scanner.nextLine().trim());
                if (playerCount < 2 || playerCount > 8) {
                    System.out.println("Only 2-8 players are allowed to play. "
                            + "Kindly re-enter a number from this range.");
                } else {
                    System.out.println(ColorHelper.color(
                            "There will be " + playerCount + " players in the game.", "YELLOW"));
                    return playerCount;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Make sure to enter a number.");
            }
        }
    }

    /**
     * Prompts user for the number of shuffles and validates the input
     *
     * @return validated number of shuffles
     */
    private int inputShuffleCount() {
        int shuffleCount;

        while (true) {
            try {
                System.out.print("\nEnter number of shuffles (1-100): ");
                shuffleCount = Integer.parseInt(scanner.nextLine().trim());
                if (shuffleCount < 1 || shuffleCount > 100) {
                    System.out.println("Number of shuffles must be in between 1 and 100. "
                            + "Kindly re-enter a number.");
                    continue;
                }

                String noun = (shuffleCount == 1) ? " shuffle." : " shuffles.";
                System.out.println(ColorHelper.color(
                        "There will be " + shuffleCount + noun, "YELLOW"));
                return shuffleCount;
            } catch (NumberFormatException nfe) {
                System.out.println("Make sure to enter a number.");
            }
        }
    }

    /**
     * Creates the specified number of players and adds them to the game
     *
     * @param numPlayers validated number of players
     */
    private void createPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i + 1));
        }
    }

    /**
     * Removes players who no longer have cards from the game
     */
    private void eliminatePlayers() {
        players.removeIf(player -> {
            if (player.hasNoCards()) {
                System.out.println("\n" + ColorHelper.color(
                        "✖ > P" + player.getNumber() + " is eliminated", "RED"));
                return true;
            }
            return false;
        });
    }

    /**
     * Checks if one player remains in the game
     *
     * @return true if one player remains; false otherwise
     */
    private boolean isGameOver() {
        return players.size() == 1;
    }

    /**
     * Displays the winner of the game
     */
    private void declareWinner() {
        System.out.println("\n" + ColorHelper.color("!! We have a winner !!", "BLACK", "GREEN_BG"));
        System.out.println("♔ Congratulations, Player " + players.getFirst().getNumber() + "!");
    }
}