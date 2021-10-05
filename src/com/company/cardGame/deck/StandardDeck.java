package com.company.cardGame.deck;

import java.util.*;

public class StandardDeck implements Deck {
    private List<BlackjackCard> blackjackCards = new ArrayList<>();;
    final private int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    final private String[] SUITS = {"\u2664", "\u2665", "\u2666", "\u2667"};

    public StandardDeck() {
        for (String suit : SUITS) {
            for (int val : VALUES) {
                blackjackCards.add(new BlackjackCard(val, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(blackjackCards);
    }

    public BlackjackCard draw() {
        return blackjackCards.remove(blackjackCards.size() - 1);
    }
}
