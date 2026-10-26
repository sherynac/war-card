package com.svi.warcard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    private final Dealer dealer;
    private final List<Player> players;
    private final Scanner kbd;

    public Game(Scanner kbd){
        players = new ArrayList<>();
        dealer = new Dealer();
        this.kbd = kbd;
    }

    public void run() {
        startGame();
        int roundNum = 1;

        while (!isGameOver()){
            Round round = new Round(roundNum);
            Player roundWinner = round.startRound(players);
            round.collectPot(roundWinner);
            eliminatePlayers();
            roundNum++;
        }
    }

    public void startGame(){
        displayTitle();

        if (dealer.readDeck()) {
            System.out.println("\nDeck has been loaded successfully.");
        } else {
            System.exit(0);
        }

        int playerCount = inputPlayerCount();
        createPlayers(playerCount);

        int shuffleCount = inputShuffleCount();
        System.out.println("\nDealer shows cards: \n" + dealer.showCards());
        dealer.shuffleDeck(shuffleCount);
        System.out.println("\nDealer shuffled cards: \n" + dealer.showCards());

        dealer.dealCards(players);
    }

    public static void displayTitle(){
        try (InputStream input = Dealer.class.getClassLoader()
                .getResourceAsStream("title.txt")) {

            if (input == null){
                return;
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(input))) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Skipping Game Logo...");
        } finally {
            System.out.println("\n=== Welcome to War Card! ===");
        }
    }

    public int inputPlayerCount(){
        int playerCount;

        while(true){
            try{
                System.out.print("\nEnter number of players (2-8): ");
                playerCount = Integer.parseInt(kbd.nextLine().trim());
                if(playerCount < 2 || playerCount > 8) {
                    System.out.println("Only 2-8 players are allowed to play. " +
                            "Kindly re-enter a number from this range.");
                } else {
                    System.out.println("There will be " + playerCount +
                            " players in the game.");
                    return playerCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.");
            }
        }
    }

    public int inputShuffleCount(){
        int shuffleCount;

        while(true){
            try{
                System.out.print("\nEnter number of shuffles (1-100): ");
                shuffleCount = Integer.parseInt(kbd.nextLine().trim());
                if(shuffleCount <= 0) {
                    System.out.println("There must be at least 1 shuffle. " +
                            "Kindly re-enter a number greater than 0.");
                } else if (shuffleCount > 100) {
                    System.out.println("Max of 100 shuffles only. " +
                            "Kindly re-enter a number from 1 to 100 only.");
                } else {
                    if(shuffleCount == 1){
                        System.out.println("There will be 1 shuffle.");
                    } else {
                        System.out.println("There will be " + shuffleCount + " shuffles.");
                    }
                    return shuffleCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.");
            }
        }
    }

    public void createPlayers(int numPlayers){
        for(int i = 0; i < numPlayers; i++) {
            players.add(new Player((i+1)));
        }

    }

    public void eliminatePlayers(){
        players.removeIf(player -> {
            if (player.getCardsOwned().isEmpty()) {
                System.out.println("\n> P" + player.getNumber() + " is eliminated <");
                return true;
            }
            return false;
        });
    }

    public boolean isGameOver() {
        if (players.size() == 1){
            System.out.println("\n!! We have a winner !!");
            System.out.println("Congratulations, Player " + players.getFirst().getNumber());
            return true;
        }
        return false;
    }
}