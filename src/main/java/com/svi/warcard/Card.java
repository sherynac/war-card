package com.svi.warcard;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    public Card(String input) {
        String[] parts = input.split("-");
        this(Suit.fromSymbol(parts[0]), Rank.fromString(parts[1]));
    }

    @Override
    public int compareTo(Card otherCard) {
        int result = Integer.compare(this.rank.getValue(), otherCard.rank().getValue());

        if (result != 0) { // rank is > or <
            return result;
        }

        return Integer.compare(this.suit.getValue(), otherCard.suit().getValue());
    }

    public String toString() {
        return suit + "-" + ((rank.getValue() > 10) ? rank : rank.getValue());
    }
}