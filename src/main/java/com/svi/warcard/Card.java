package com.svi.warcard;

public class Card implements Comparable<Card>{
    private Rank rank;
    private Suit suit;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Card (String input) {
        String[] parts = input.split("-");

        this.suit = Suit.fromSymbol(parts[0]);
        this.rank = Rank.fromString(parts[1]);
    }

    public Rank getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }
    @Override
    public int compareTo(Card otherCard){
        int result = Integer.compare(this.rank.getValue(), otherCard.getRank().getValue());

        if (result != 0) { // rank is > or <
            return result;
        }

        return Integer.compare(this.suit.getValue(), otherCard.getSuit().getValue());
    }

    public String toString(){
        return suit + "-" + ((rank.getValue() > 11) ? rank : rank.getValue());
    }
}