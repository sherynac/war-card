package com.svi.warcard.models;

import com.svi.warcard.ColorHelper;
import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;
import java.util.StringTokenizer;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    public Card(String input) {
        StringTokenizer st = new StringTokenizer(input, "-");

        if (st.countTokens() != 2) {
            throw new IllegalArgumentException("Invalid card format: " + input);
        }

        this(Suit.parse(st.nextToken()), Rank.parse(st.nextToken()));
    }

    @Override
    public int compareTo(Card otherCard) {
        int result = Integer.compare(this.rank.getValue(), otherCard.rank().getValue());

        if (result != 0) { // rank is > or <
            return result;
        }

        return Integer.compare(this.suit.getValue(), otherCard.suit().getValue());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        switch (suit){
            case D -> stringBuilder.append("♦");
            case H -> stringBuilder.append("♥");
            case S -> stringBuilder.append("♠");
            case C -> stringBuilder.append("♣");

        }

        stringBuilder.append(suit).append("-");
        stringBuilder.append(((rank.getValue() > 10) ? rank : rank.getValue()));

        return ((suit.getValue() > 2
                ? ColorHelper.color(stringBuilder.toString(), "RED",null)
                : ColorHelper.color(stringBuilder.toString(), "BLUE", null)));

    }
}