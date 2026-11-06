/**
 * Manages the card deck including loading, shuffling, displaying and dealing cards to players
 *
 * @author Sheryn Cagulada
 * @version October 30, 2026
 */
package com.svi.warcard.game;

import com.svi.warcard.utils.ColorHelper;
import com.svi.warcard.models.Card;
import com.svi.warcard.models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Dealer {
    private List<Card> cards;

    /**
     * Constructs a Dealer Object with an empty card deck
     */
    public Dealer() {
        this.cards = new ArrayList<>();
    }

    /**
     * Loads card deck from text file and validates its content
     *
     * @throws IOException for I/O errors while reading the file
     * @throws FileNotFoundException if the file does not exist
     * @throws IllegalArgumentException for duplicate cards,
     *          does not contain exactly 52 cards, or contains invalid card data
     */
    public void readDeck() throws IOException {
        InputStream input = Dealer.class.getClassLoader()
                .getResourceAsStream("input.txt");

        if (input == null) {
            throw new FileNotFoundException("\nCard text file was not found.");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                while (st.hasMoreTokens()) {
                    Card card = new Card(st.nextToken().trim());

                    if (cards.contains(card)) {
                        throw new IllegalArgumentException("\nDuplicate card detected: " + card);
                    }

                    cards.add(card);
                }
            }
        }

        if (cards.isEmpty()) {
            throw new IllegalArgumentException("\nInput file did not contain any data.");
        }

        if (cards.size() != 52) {
            throw new IllegalArgumentException("\nStandard deck should have exactly 52 cards. Read file had "
                            + cards.size() + " cards.");
        }
    }

    /**
     * Performs perfect shuffle on card deck by splitting deck into two halves
     * and alternately adding cards from each half
     *
     * @param numShuffles the number of times deck has been shuffled
     */
    public void shuffleDeck(int numShuffles) {
        System.out.println("\n" + ColorHelper.color(
                "Dealer is shuffling cards...", "YELLOW"));

        for (int k = 0; k < numShuffles; k++) {
            List<Card> list1 = cards.subList(0,26);
            List<Card> list2 = cards.subList(26,52);
            List<Card> shuffledDeck = new ArrayList<>();

            for (int i = 0; i < list1.size(); i++) {
                shuffledDeck.add(list1.get(i));
                shuffledDeck.add(list2.get(i));
            }
            cards = shuffledDeck;
        }
    }

    /**
     * Deals cards to players in round-robin manner until the deck is empty.
     * Any excess cards are distributed among the players.
     *
     * @param players the list of players receiving cards
     */
    public void dealCards(List<Player> players) {
        while (!cards.isEmpty()) {
            for (Player player : players) {
                if (cards.isEmpty()) {
                    return;
                }
                Card card = cards.removeFirst();
                player.addCard(card);
            }
        }
    }

    /**
     * Returns a formatted string representation of the card deck
     *
     * @return the formatted card deck
     */
    public String showCards() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            stringBuilder.append(cards.get(i));

            if ((i + 1) % 15 == 0) {
                stringBuilder.append("\n");
            } else if (i != cards.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}