package com.svi.warcard.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the card game and manages their deck of cards.
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
public class Player {
    private final int number;
    private final List<Card> cardsOwned;

    /**
     * Constructs a player with the specified player number
     *
     * @param number unique player assigned number
     */
    public Player(int number) {
        this.number = number;
        this.cardsOwned = new ArrayList<>();
    }

    /**
     * Retrieves player's assigned number
     *
     * @return player number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Checks whether the player's deck is empty
     *
     * @return true if no cards; false otherwise
     */
    public boolean hasNoCards() {
        return cardsOwned.isEmpty();
    }

    /**
     * Draws and removes top card from player's cards
     *
     * @return card drawn from the deck
     */
    public Card drawTopCard() {
        return cardsOwned.removeFirst();
    }

    /**
     * Adds a card to the bottom of player's deck
     *
     * @param card to be added
     */
    public void addCard(Card card) {
        this.cardsOwned.add(card);
    }

    /**
     * Adds all cards from round pot to the bottom of player's deck
     *
     * @param cards refers to the round pot
     */
    public void addPot(List<Card> cards) {
        this.cardsOwned.addAll(cards);
    }

    /**
     * Returns a formatted representation of the player's deck,
     * including the player number, card count, and cards.
     *
     * @return the formatted player information and deck
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("P").append(number)
                .append("'s Deck ")
                .append("(").append(cardsOwned.size())
                .append(" cards): ");

        for (int i = 0; i < cardsOwned.size(); i++) {
            sb.append(cardsOwned.get(i));

            if ((i + 1) % 15 == 0 && i != cardsOwned.size() - 1) {
                sb.append("\n");
            } else if (i != cardsOwned.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}