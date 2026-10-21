package com.svi.warcard;

public record Card(Rank rank, Suit suit) implements Comparable<Card> {
    public Card(Suit suit, Rank rank) {
        this(rank, suit);
    }

    public Card(String input) {
        String[] parts = input.split("-");

        this(Rank.fromString(parts[1]), Suit.fromSymbol(parts[0]));
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
        return suit + "-" + ((rank.getValue() > 11) ? rank : rank.getValue());
    }
}