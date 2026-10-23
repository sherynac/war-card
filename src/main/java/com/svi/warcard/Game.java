package com.svi.warcard;
import java.util.*;

public class Game {
    private Dealer dealer;
    private List<Player> players;
    private Scanner kbd;

    public Game(Scanner kbd){
        players = new ArrayList<Player>();
        dealer = new Dealer();
        this.kbd = kbd;
    }

    public void run() {
        startGame();
        int roundNum = 1;
        int maxRounds = 10000;

        while (!isGameOver() && roundNum <= maxRounds){
            Round round = new Round(roundNum);
            Player roundWinner = round.startRound(players);
            round.collectPot(roundWinner);
            eliminatePlayers();
            roundNum++;
        }

        declareWinner();
    }

    public void startGame(){
        System.out.println("\n" + "=== Welcome to War Card! ===");

        if (dealer.readDeck()) {
            System.out.println("\nDeck has been loaded successfully.");
        }

        int playerCount = inputPlayerCount();
        createPlayers(playerCount);

        int shuffleCount = inputshuffleCount();

        System.out.println("\nCURRENT DECK: " + dealer);
        dealer.shuffleDeck(shuffleCount);
        System.out.println("\nSHUFFLED DECK: " + dealer);

        dealer.dealCards(players);
    }

    public int inputPlayerCount(){
        boolean isValid = false;
        int playerCount = 0;

        while(!isValid){
            try{
                System.out.print("\nEnter number of players (2-8): ");
                playerCount = Integer.parseInt(kbd.nextLine().trim());
                if(playerCount < 2 || playerCount > 8) {
                    System.out.println("Only 2-8 players are allowed to play. Kindly re-enter a number from this range.");
                } else {
                    System.out.println("There will be " + playerCount + " players in the game.");
                    isValid = true;
                    return playerCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.");
            }

        }

        return playerCount;
    }

    public int inputshuffleCount(){
        boolean isValid = false;
        int shuffleCount = 0;

        while(!isValid){
            try{
                System.out.print("\nEnter number of shuffles (1-100): ");
                shuffleCount = Integer.parseInt(kbd.nextLine().trim());
                if(shuffleCount <= 0) {
                    System.out.println("There must be at least 1 shuffle. Kindly re-enter a number greater than 0.");
                } else if (shuffleCount > 100) {
                    System.out.println("Max of 100 shuffles only. Kindly re-enter a number from 1 to 100 only.");
                } else {
                    if(shuffleCount == 1){
                        System.out.println("There will be 1 shuffle.");
                    } else {
                        System.out.println("There will be " + shuffleCount + " shuffles.");
                    }
                    isValid = true;
                    return shuffleCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.");
            }

        }

        return shuffleCount;
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
            return true;
        }

        return false;
    }

    public void declareWinner(){
        if (players.size() == 1){
            System.out.println("\n!! We have a winner !!");System.out.println("Congratulations, Player " + players.get(0).getNumber());
        } else {
            System.out.println("!! Game ended in a DRAW !!");
        }

    }
}