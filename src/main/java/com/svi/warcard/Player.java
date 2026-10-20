package com.svi.warcard;
import java.util.*;

public class Player{
    private int number;
    private List<Card> cardsOwned;

    public Player(){
        this.cardsOwned = new ArrayList<>();
    }

    public Player(int number){
        this.number = number;
        this.cardsOwned = new ArrayList<>();
    }

    public int getNumber(){
        return number;
    }

    public List<Card> getCardsOwned(){
        return cardsOwned;
    }

    public Card getTopCard(){
        return cardsOwned.removeFirst();
    }

    public void addToCards(Card card){
        this.cardsOwned.add(card);
    }

    public void addPot(List<Card> cards){
        this.cardsOwned.addAll(cards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nP").append(number)
                .append(" (").append(cardsOwned.size()).append(" cards)")
                .append("\nDeck:\n");

        for (int i = 0; i < cardsOwned.size(); i++) {
            sb.append(cardsOwned.get(i));

            if ((i + 1) % 5 == 0 || i == cardsOwned.size() - 1) {
                sb.append("\n");
            } else {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

}