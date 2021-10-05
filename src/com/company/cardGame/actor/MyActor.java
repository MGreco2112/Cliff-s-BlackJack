package com.company.cardGame.actor;

import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;
import com.company.cardGame.deck.BlackjackCard;
import com.company.cardGame.deck.Card;

import java.util.Random;


public class MyActor implements Actor {
    private final String NAME;
    private int balance = 1000;
    final private String[] SUITS = {"\u2664", "\u2665", "\u2666", "\u2667"};



    public MyActor(String name) {
        this.NAME = name;
    }

    public MyActor(String name, int balance) {
        this.NAME = name;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int placeBet() {
        int bet = balance / 3;

        balance -= bet;

        return bet;
    }

    /*
    This guy right here is gonna be a stinky cheater, because that's fun
    If the Dealer is reasonably under 21 he's going to access the rigged deck and build out the perfect hand
    This will be done via doubling the bet if not at 21, then getting the card from RD to get to 21
    This, in theory, should win every time
    His winnings will also be multiplied by 5 because he's awful
    This is a stinky man I've made and he will be banned from every Vegas casino at best
     */

    @Override
    public byte getAction(Hand hand, int dealerValue) {
        Random random = new Random();

        if (dealerValue < 10 && hand.getValue() < 21 && !hand.hasPair()) {
            BlackjackCard forgedCard = new BlackjackCard(hand.getValue() - 21, SUITS[random.nextInt(SUITS.length)]);
            hand.addCard(forgedCard);

            System.out.println("HIT!!!");

            return Actor.STAND;

        } else if (hand.hasPair()) {
            return Actor.SPLIT;
        }


        return Actor.STAND;
    }

    @Override
    public void addBalance(int winnings) {
        balance += winnings * 5;
    }
}
