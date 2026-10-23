package com.svi.warcard;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private final List<Card> pot = new ArrayList<>();
    private final int roundNum;
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

        // SHOW ONLY COLLECTED CARDS
//        System.out.println("\nP" + roundWinner.getNumber() + " wins Round #" + roundNum);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("Collected Cards (").append(pot.size()).append(" cards): ");
//
//        for (int i = 0; i < pot.size(); i++) {
//            sb.append(pot.get(i));
//
//            if ((i + 1) % 5 == 0 || i == pot.size() - 1) {
//                sb.append("\n");
//            } else {
//                sb.append(", ");
//            }
//        }
//
//        System.out.println(sb);

        roundWinner.addPot(pot);


        // SHOW ENTIRE PLAYER DECK
        System.out.println("\n> P" + roundWinner.getNumber() + " wins Round #" + roundNum + " <");
        System.out.println(roundWinner);

    }


}
