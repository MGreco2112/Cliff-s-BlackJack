package com.company.cardGame.actor;

import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;
import com.company.cardGame.deck.BlackjackCard;
import com.company.cardGame.deck.Card;

import java.util.Random;


public class MyActor implements Actor {
    private final String NAME;
    private int balance = 1000;



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


    @Override
    public byte getAction(Hand hand, int dealerValue) {

        if (hand.getValue() < 18 && !hand.hasPair()) {
            return Actor.HIT;

        } else if (hand.hasPair()) {
            return Actor.SPLIT;
        }


        return Actor.STAND;
    }

    @Override
    public void addBalance(int winnings) {
        balance += winnings;
    }
}
