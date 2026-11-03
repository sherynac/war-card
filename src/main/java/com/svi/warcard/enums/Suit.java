/**
 * @author Sheryn Cagulada
 * @version October 30, 2026
 *
 * Contains the Suit enum for getting the value of each suit and method for parsing
 */

package com.svi.warcard.enums;

public enum Suit {
    D(4),
    H(3),
    S(2),
    C(1);

    private final int value;

    Suit(int value) {
        this.value = value;
    }

    /**
     *
     * @return value of Suit
     */
    public int getValue() {
        return value;
    }

    /**
     * Parse the Suit based on input; Throws Exception if it does not exist
     * @param input
     * @return Suit
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