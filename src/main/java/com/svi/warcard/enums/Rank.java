/**
 * Represents the ranks of playing cards and their corresponding numerical values.
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
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

    /**
     * Constructs a rank with its corresponding numerical value
     *
     * @param value numerical value assigned to the rank
     */
    Rank(int value) {
        this.value = value;
    }

    /**
     * Returns numerical value of rank
     *
     * @return value of Rank
     */
    public int getValue() {
        return value;
    }

    /**
     * Parse a String to its corresponding Rank
     *
     * @param input string representation of the rank
     * @return the corresponding rank
     * @throws IllegalArgumentException if the input does not match any rank
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