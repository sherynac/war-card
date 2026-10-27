package com.svi.warcard.models;

import java.util.ArrayList;
import java.util.List;

public class Player{
    private int number;
    private final List<Card> cardsOwned;

    public Player() {
        this.cardsOwned = new ArrayList<>();
    }

    public Player(int number) {
        this.number = number;
        this.cardsOwned = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public boolean hasNoCards(){
        return cardsOwned.isEmpty();
    }

    public Card drawTopCard() {
        return cardsOwned.removeFirst();
    }

    public void addCard(Card card) {
        this.cardsOwned.add(card);
    }

    public void addPot(List<Card> cards) {
        this.cardsOwned.addAll(cards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("P").append(number)
                .append("'s Deck ")
                .append("(").append(cardsOwned.size())
                .append(" cards): ");

        for (int i = 0; i < cardsOwned.size(); i++) {
            sb.append(cardsOwned.get(i));

            if ((i + 1) % 15 == 0 && i!= cardsOwned.size() - 1) {
                sb.append("\n");
            } else if (i != cardsOwned.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}