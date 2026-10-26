package com.svi.warcard;
import java.io.*;
import java.util.*;

public class Dealer {
    private List<Card> cards;

    public Dealer(){
        this.cards = new ArrayList<>();
    }

    public boolean readDeck() {
        try (InputStream input = Dealer.class.getClassLoader()
                .getResourceAsStream("input.txt")) {

            if (input == null){
                System.out.println("\nFile does not exist.");
                System.out.println("Kindly double check the text file and rerun the application.");
                return false;
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(input))) {

                String line;

                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line, ",");

                    while (st.hasMoreTokens()) {
                        Card card = new Card(st.nextToken().trim());

                        if(cards.contains(card)){
                            System.out.println("\nDuplicate card detected.");
                            System.out.println("Kindly double check the text file and rerun the application.");
                            return false;
                        }
                        cards.add(card);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("\nError reading file.");
            System.out.println("Kindly double check the text file and rerun the application.");
            return false;
        } catch (IllegalArgumentException iae) {
            System.out.println("\nInformation mismatch.");
            System.out.println(iae.getMessage());
            System.out.println("Kindly double check the text file and rerun the application.");
            return false;
        }

        if (cards.size() != 52) {
            System.out.println("\nStandard deck should have exactly 52 cards. Read file had "
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
        System.out.println("\nDealer is shuffling cards...");

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
        while (!cards.isEmpty()) {
            for (Player player : players) {
                if (cards.isEmpty()) {
                    break;
                }
                Card card = cards.removeFirst();
                player.addToCards(card);
            }
        }
    }

    public String showCards(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            stringBuilder.append(cards.get(i));

            if ((i + 1) % 15 == 0) {
                stringBuilder.append("\n");
            } else if (i != cards.size() - 1){
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}