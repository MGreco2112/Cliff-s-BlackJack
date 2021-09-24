package com.company.cardGame.actor;

import com.company.Utils.Console;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Player implements Actor {
    private final String NAME;
    private int balance = 1000;

    public Player(String name) {
        this.NAME = name;
    }

    public Player(String name, int startingBalance) {
        this.NAME = name;
        this.balance = startingBalance;
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
    public int getBet() {
        return Console.getInt(1, balance, "Enter a bet between 1 and " + balance, "Invalid bet");
    }

    public String getAvailableOptions() {
        StringBuilder output = new StringBuilder();
        output.append("0. Quit\n1. Hit\n2. Stand");
        //TODO create logic to add Double
        //TODO pt1 Confirm First Turn;
        //TODO pt2 Confirm has enough funds

        //TODO pt3 add logic for Split to detect pair
        return output.toString();
    }

    @Override
    public byte getAction(Hand hand) {
        //display hand and value
        System.out.println(hand.displayHand());
        System.out.println(hand.getValue());
        //display available actions
        System.out.println(getAvailableOptions());

        //get selected action
        return (byte) Console.getInt(0, 2, "", "Invalid Selection");
    }
}
