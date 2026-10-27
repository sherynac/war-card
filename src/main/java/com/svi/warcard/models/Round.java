package com.svi.warcard.models;

import com.svi.warcard.ColorHelper;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private final List<Card> pot = new ArrayList<>();
    private final int roundNum;
    private Card highestCard;

    public Round(int roundNum) {
        this.roundNum = roundNum;
    }

    public Player startRound(List<Player> players) {
        System.out.println("\n" + ColorHelper.color("--- ROUND #" + roundNum + " ---" , "BLACK", "YELLOW_BG"));
        Player roundWinner = new Player();

        for (Player player: players) {
            Card topCard = player.drawTopCard();

            if (highestCard == null || topCard.compareTo(highestCard) > 0 ) {
                highestCard = topCard;
                roundWinner = player;
            }

            System.out.println("P" + player.getNumber() + ": " + topCard);
            pot.add(topCard);
        }
        return roundWinner;
    }

    public void collectPot(Player roundWinner) {
        pot.remove(highestCard);
        pot.addFirst(highestCard);
        roundWinner.addPot(pot);

        System.out.println("\n" + ColorHelper.color("★ > P" + roundWinner.getNumber() + " wins Round #" + roundNum, "GREEN", null));
        System.out.println(roundWinner);
    }
}
