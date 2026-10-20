package com.svi.warcard;

public enum Suit {
    D(4),
    H(3),
    S(2),
    C(1);

    private int value;

    Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Suit fromSymbol(String input) {
        for (Suit suit : values()) {
            if (suit.toString().equalsIgnoreCase(input)) {
                return suit;
            }
        }


        throw new IllegalArgumentException("Invalid suit: " + input);
    }
}