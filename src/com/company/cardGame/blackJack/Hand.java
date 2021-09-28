package com.company.cardGame.blackJack;

import com.company.cardGame.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private int bet = 0;
    private final Actor HOLDER;

    public Hand(Actor HOLDER) {
        this.HOLDER = HOLDER;

    }

    public byte getAction() {
        return HOLDER.getAction(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void doubleBet() {
        bet *= bet;
    }

//    public boolean hasPair() {
//
//        for (int i = 0; i < cards.size(); i++) {
//            for (int j = i + 1; j < cards.size(); j++) {
//                if (cards.get(i).getValue() == cards.get(j).getValue()) {
//                    return true;
//                }
//            }
//
//        }
//
//
//        return false;
//    }


    public boolean hasPair() {
        return cards.get(0).getRank() == cards.get(1).getRank();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String displayHand() {
        StringBuilder output = new StringBuilder();
        for (Card card : cards) {
            output.append(card.display()).append(" ");
        }

        return output.toString().trim();
    }

    public int getValue() {
        int score = 0;
        boolean haveAce11 = false;
        for (Card card : cards) {
            int value = card.getRank();
            switch (value) {
                case 1 -> {
                    value = score + 11 > 21 ? 1 : 11;
                    if (value == 11) {
                        haveAce11 = true;
                    }
                    score += value;
                }
                case 11, 12, 13 -> score += 10;
                default -> score += value;
            }
            if(score > 21 && haveAce11) {
                score -= 10;
                haveAce11 = false;
            }
        }

        return score;
    }

}
