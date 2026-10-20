package com.svi.warcard;
import java.util.*;

public class Game {
    private Deck deck;
    private List<Player> players;
    private Scanner kbd;

    public Game(Scanner kbd){
        players = new ArrayList<Player>();
        deck = new Deck();
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

        declareWinner();
    }

    public void startGame(){
        System.out.println("Welcome to War Card!\n");

        if (deck.readDeck()) {
            System.out.println("Deck has been loaded successfully.\n");
        }

        int playerCount = inputPlayerCount();
        createPlayers(playerCount);

        int shuffleCount = inputshuffleCount();

        System.out.println("CURRENT DECK: " + deck + "\n");
        deck.shuffleDeck(shuffleCount);
        System.out.println("SHUFFLED DECK: " + deck + "\n");

        deck.dealCards(players);
    }

    public int inputPlayerCount(){
        boolean isValid = false;
        int playerCount = 0;

        while(!isValid){
            try{
                System.out.print("Enter number of players: ");
                playerCount = Integer.parseInt(kbd.nextLine());
                if(playerCount < 2 || playerCount > 8) {
                    System.out.println("Only 2-8 players are allowed to play. Kindly re-enter a number from this range.\n");
                } else {
                    System.out.println("There will be " + playerCount + " players in the game.\n");
                    isValid = true;
                    return playerCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.\n");
            }

        }

        return playerCount;
    }

    public int inputshuffleCount(){
        boolean isValid = false;
        int shuffleCount = 0;

        while(!isValid){
            try{
                System.out.print("Enter number of shuffles: ");
                shuffleCount = Integer.parseInt(kbd.nextLine());
                if(shuffleCount <= 0) {
                    System.out.println("There must be at least 1 shuffle. Kindly re-enter a number greater than 0.\n");
                } else {
                    System.out.println("There will be " + shuffleCount + " shuffles.\n");
                    isValid = true;
                    return shuffleCount;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Make sure to enter a number.\n");
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
                System.out.println("P" + player.getNumber() + " is eliminated");
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
        System.out.println("\n!! We have a winner !!");System.out.println("Congratulations, Player " + players.get(0).getNumber());
    }
}