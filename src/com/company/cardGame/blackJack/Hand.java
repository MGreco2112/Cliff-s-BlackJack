package com.company.cardGame.blackJack;

import com.company.cardGame.deck.BlackjackCard;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<BlackjackCard> blackjackCards = new ArrayList<>();
    private int bet = 0;
    private final Actor HOLDER;

    public final byte PUSHPAY = 0;
    public final byte NORMALPAY = 1;

    public Hand(Actor HOLDER) {
        this.HOLDER = HOLDER;

    }

    public byte getAction() {
        return HOLDER.getAction(this);
    }

    public List<BlackjackCard> getCards() {
        return blackjackCards;
    }

    public void doubleBet() {
         HOLDER.addBalance(-bet);
         bet *= 2;
    }

    public String getName() {
        return HOLDER.getName();
    }

    public int getBet() {
        return bet;
    }

    public int placeBet() {
        bet = HOLDER.placeBet();
        return bet;
    }

    public void payout(byte typeOfWin) {
        switch (typeOfWin) {
            case PUSHPAY -> HOLDER.addBalance(bet);
            case NORMALPAY -> HOLDER.addBalance(bet *= 2);

        }

    }

    public BlackjackCard removeCard(int index) {
        return blackjackCards.remove(index);
    }

    public Hand splitHand() {
        bet = bet/2;
        Hand newHand = new Hand(HOLDER);
        newHand.addCard(blackjackCards.remove(0));
        newHand.bet = bet;

        return newHand;
    }

    public void clearHands() {
        blackjackCards.clear();
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

    public int getBalance() {
        return HOLDER.getBalance();
    }

    public boolean hasPair() {
        return blackjackCards.get(0).getRank() == blackjackCards.get(1).getRank();
    }

    public void addCard(BlackjackCard blackjackCard) {
        blackjackCards.add(blackjackCard);
    }

    public String displayHand() {
        StringBuilder output = new StringBuilder();
        for (BlackjackCard blackjackCard : blackjackCards) {
            output.append(blackjackCard.display()).append(" ");
        }

        return output.toString().trim();
    }

    public void revealHand() {
        for (BlackjackCard blackjackCard : blackjackCards) {
            if (blackjackCard.getIsFaceDown()) {
                blackjackCard.flip();
            }
        }
    }

    public int getValue() {
        int score = 0;
        boolean haveAce11 = false;
        for (BlackjackCard blackjackCard : blackjackCards) {
            int value = blackjackCard.getRank();
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

    public int getShownRank() {
        return blackjackCards.get(1).getRank();
    }

}
