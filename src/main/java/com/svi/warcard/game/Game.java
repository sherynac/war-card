package com.svi.warcard.game;

import com.svi.warcard.ColorHelper;
import com.svi.warcard.models.Player;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Dealer dealer;
    private final List<Player> players;
    private final Scanner scanner;

    public Game(Scanner scanner) {
        players = new ArrayList<>();
        dealer = new Dealer();
        this.scanner = scanner;
    }

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

    private void setupGame() {
        displayTitle();

        if(dealer.readDeck()){
            System.out.println("\nDeck has been loaded successfully.");
        } else {
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

                System.out.println(ColorHelper.color(stringBuilder.toString(), "PURPLE", null));
            }
        } catch (IOException e) {
            System.out.println("Skipping Game Logo...");
        } finally {
            System.out.println("\n=== Welcome to War Card! ===");
        }
    }

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
                    System.out.println(ColorHelper.color("There will be " + playerCount
                            + " players in the game.", "YELLOW", null));
                    return playerCount;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Make sure to enter a number.");
            }
        }
    }

    private int inputShuffleCount() {
        int shuffleCount;

        while (true) {
            try {
                System.out.print("\nEnter number of shuffles (1-100): ");
                shuffleCount = Integer.parseInt(scanner.nextLine().trim());
                if (shuffleCount <= 0 || shuffleCount > 100) {
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

    private void createPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i + 1));
        }
    }

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

    private boolean isGameOver() {
        return players.size() == 1;
    }

    private void declareWinner() {
        System.out.println("\n" + ColorHelper.color("!! We have a winner !!", "BLACK", "GREEN_BG"));
        System.out.println("♔ Congratulations, Player " + players.getFirst().getNumber() + "!");
    }
}