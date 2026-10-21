# Welcome to War Card!

## About The Project
War Card is a 2-8 player game, where players face against each other by placing the top card from their decks. The player with the highest card wins each round until a player gets all the cards of the deck.

## Technical Details
Built with Java 25 and Apache Maven

## Game Mechanics
1. There are 52 cards on the deck. The card values in descending order are: 
    - *suits*: diamond, heart, spades, clubs
    - *ranks*: ace, king, queen, jack, ten, nine, eight, seven, six, five, four, three, two
2. The initial deck will be given in a table in memory.
3. Shuffling means cutting the card deck in half, then sequencing the cards from the half decks alternately to a new deck. 
4. Distribute the cards to the players
5. Each player plays the top card of his deck on each round. 
6. The winner of the round is the player with the highest card. The winner gets the cards of the round and places them at the bottom of his deck. Note: winning card should be on top
7. The winner of the game is the player that gets all the cards.

