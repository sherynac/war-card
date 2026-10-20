package com.svi.warcard;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Card> pot = new ArrayList<Card>();
    private int roundNum;
    private Card highestCard;

    public Round(int roundNum){
        this.roundNum = roundNum;
        highestCard = new Card(Suit.C, Rank.TWO);
    }

    public Player startRound(List<Player> players){
        System.out.println("\n--- ROUND #" + roundNum + " ---");
        Player roundWinner = new Player();

        for (Player player: players){
            Card topCard = player.getTopCard();
            if (topCard.compareTo(highestCard) > 0){
                highestCard = topCard;
                roundWinner = player;
            }
            System.out.println("P" + player.getNumber() + ": " + topCard);
            pot.add(topCard);
        }

        return roundWinner;
    }

    public void collectPot(Player roundWinner){
        pot.remove(highestCard);
        pot.addFirst(highestCard);
        roundWinner.addPot(pot);

        pot.clear();
        System.out.println("\nP" + roundWinner.getNumber() + " wins Round #" + roundNum);
        System.out.println(roundWinner);

    }


}
