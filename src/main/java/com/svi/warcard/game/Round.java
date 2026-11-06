package com.svi.warcard.game;

import com.svi.warcard.utils.ColorHelper;
import com.svi.warcard.models.Card;
import com.svi.warcard.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages individual game rounds including card draws, winner determination and collection of round pot
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
public class Round {
    private final List<Card> pot = new ArrayList<>();
    private final int roundNum;
    private Card highestCard;

    /**
     * Constructs a round with corresponding round number
     *
     * @param roundNum assigned number to the round
     */
    public Round(int roundNum) {
        this.roundNum = roundNum;
    }

    /**
     * Executes a round by drawing a card from each player and
     * determining the player with the highest card
     *
     * @param players refers to the list of players in the game
     * @return Player object that refers to the round winner
     */
    public Player startRound(List<Player> players) {
        System.out.println("\n" + ColorHelper.color(
                "--- ROUND #" + roundNum + " ---", "BLACK", "YELLOW_BG"));
        Player roundWinner = null;

        for (Player player : players) {
            Card topCard = player.drawTopCard();

            if (highestCard == null || topCard.compareTo(highestCard) > 0) {
                highestCard = topCard;
                roundWinner = player;
            }

            System.out.println("P" + player.getNumber() + ": " + topCard);
            pot.add(topCard);
        }
        return roundWinner;
    }

    /**
     * Places winning card to top of the pot and transfers round pot to the winner
     *
     * @param roundWinner player who won the round
     */
    public void collectPot(Player roundWinner) {
        pot.remove(highestCard);
        pot.addFirst(highestCard);
        roundWinner.addPot(pot);

        System.out.println("\n" + ColorHelper.color(
                "★ > P" + roundWinner.getNumber() + " wins Round #" + roundNum, "GREEN", null));
        System.out.println(roundWinner);
    }
}
