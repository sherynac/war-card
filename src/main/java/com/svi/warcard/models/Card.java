/**
 * Represents a playing card consisting of a suit and a rank.
 * Provides card parsing, comparison, and formatted string representation.
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
package com.svi.warcard.models;

import com.svi.warcard.ColorHelper;
import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;

import java.util.StringTokenizer;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    /**
     * Constructs a card based on its String representation
     *
     * @param input card representation in suit-rank format
     * @throws IllegalArgumentException if the input has an invalid format, suit, or rank
     */
    public Card(String input) {
        StringTokenizer st = new StringTokenizer(input, "-");

        if (st.countTokens() != 2) {
            throw new IllegalArgumentException("\nInvalid card format: " + input);
        }

        this(Suit.parse(st.nextToken()), Rank.parse(st.nextToken()));
    }

    /**
     * Compares this card with another card by rank, then by suit.
     *
     * @param otherCard the card to compare with this card
     * @return a negative integer, zero, or a positive integer if this card
     *         is lower than, equal to, or higher than the specified card
     */
    @Override
    public int compareTo(Card otherCard) {
        int result = Integer.compare(this.rank.getValue(), otherCard.rank().getValue());

        if (result != 0) { // rank is > or <
            return result;
        }

        return Integer.compare(this.suit.getValue(), otherCard.suit().getValue());
    }

    /**
     * Returns the card's formatted string representation, including its suit symbol
     * and color.
     *
     * @return the formatted card representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        switch (suit) {
            case D -> stringBuilder.append("♦");
            case H -> stringBuilder.append("♥");
            case S -> stringBuilder.append("♠");
            case C -> stringBuilder.append("♣");

        }

        stringBuilder.append(suit).append("-");
        stringBuilder.append(((rank.getValue() > 10) ? rank : rank.getValue()));

        return ((suit.getValue() > 2
                ? ColorHelper.color(stringBuilder.toString(), "RED")
                : ColorHelper.color(stringBuilder.toString(), "BLUE")));
    }
}