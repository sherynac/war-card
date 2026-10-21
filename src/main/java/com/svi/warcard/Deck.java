package com.svi.warcard;
import java.io.*;
import java.util.*;

public class Deck{
    List<Card> cards;

    public Deck (){
        this.cards = new ArrayList<>();
    }

    public boolean readDeck() {
        try (InputStream input = Deck.class.getClassLoader()
                .getResourceAsStream("input.txt")) {

            if (input == null) {
                System.out.println("Error reading file.");
                System.out.println("Kindly ensure input.txt is included in the application.");
                return false;
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(input))) {

                String line;

                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line, ",");

                    while (st.hasMoreTokens()) {
                        Card card = new Card(st.nextToken().trim());
                        System.out.println("ADDED CARD: " + card);
                        cards.add(card);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
            System.out.println("Kindly double check the text file and rerun the application.");
            return false;

        } catch (IllegalArgumentException iae) {
            System.out.println("Information mismatch.");
            System.out.println(iae.getMessage());
            System.out.println("Kindly double check the text file and rerun the application.");
            return false;
        }

        if (cards.size() != 52) {
            System.out.println("Standard deck should have exactly 52 cards. Read file had "
                    + cards.size() + " cards.");
            System.out.println("Kindly double check the text file and rerun the application.");
            return false;
        }

        return true;
    }

    public void shuffleDeck(int numShuffle){
//        for (int k = 0; k < numShuffle; k++){
//            Collections.shuffle(cards);
//        }

//        PERFECT SHUFFLE
        for(int k = 0; k < numShuffle; k++ ){
            List<Card> list1 = cards.subList(0,26);
            List<Card> list2 = cards.subList(26,52);
            List<Card> shuffledDeck = new ArrayList<>();

            for(int i = 0; i < list1.size(); i++){
                shuffledDeck.add(list1.get(i));
                shuffledDeck.add(list2.get(i));
            }

            cards = shuffledDeck;
        }
    }

    public void dealCards(List<Player> players) {
        int numPerPlayer = cards.size() / players.size();

        System.out.println("CURRENT SIZE: " + cards.size());

        for (int i = 0; i < numPerPlayer; i++) {
            for (Player player : players) {
                Card card = cards.removeFirst();
                player.addToCards(card);
            }
        }

//        System.out.println("\nDISCARDED CARDS:");
//        for (Card card : cards) {
//            System.out.println(card);
//        }
    }


    public String toString(){
        StringBuilder deckString = new StringBuilder();
        for(int i = 0; i < cards.size(); i++){
            deckString.append(cards.get(i));
            if(i != cards.size() - 1){
                deckString.append(", ");
            }
        }
        return deckString.toString();
    }
}