/**
 * Represents the ranks of playing cards and their corresponding numerical values.
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */

package com.svi.warcard.enums;

public enum Suit {
    D(4),
    H(3),
    S(2),
    C(1);

    private final int value;

    /**
     * Constructs a suit with its corresponding value
     *
     * @param value numerical value assigned to the suit
     */
    Suit(int value) {
        this.value = value;
    }

    /**
     * Returns numerical value of suit
     *
     * @return value of Suit
     */
    public int getValue() {
        return value;
    }

    /**
     * Parse a String to its corresponding Suit
     *
     * @param input input the string representation of the suit
     * @return the corresponding Suit
     * @throws IllegalArgumentException if the input does not match any suit
     */
    public static Suit parse(String input) {
        for (Suit suit : values()) {
            if (suit.toString().equalsIgnoreCase(input)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("\nInvalid suit: " + input);
    }
}