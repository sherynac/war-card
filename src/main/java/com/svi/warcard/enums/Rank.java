/**
 * @author Sheryn Cagulada
 * @version October 30, 2026
 *
 * Contains the Rank enum for getting the value of each rank and method for parsing
 */

package com.svi.warcard.enums;

public enum Rank {
    A(14),
    K(13),
    Q(12),
    J(11),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    /**
     *
     * @return value of Rank
     */
    public int getValue() {
        return value;
    }

    /**
     * Parse the Rank based on input; Throws Exception if it does not exist
     * @param input
     * @return Rank
     */
    public static Rank parse(String input) {
        for (Rank rank : values()) {
            if (rank.toString().equalsIgnoreCase(input)) {
                return rank;
            } else if (String.valueOf(rank.getValue()).equalsIgnoreCase(input)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("\nInvalid rank: " + input);
    }
}