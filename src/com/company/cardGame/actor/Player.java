package com.company.cardGame.actor;

import com.company.Utils.Console;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Player implements Actor {
    private final String NAME;
    private int balance = 1000;
    private int maxSelection = 2;

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
    public int placeBet() {
        int bet = Console.getInt(1, balance, "Enter a bet between 1 and " + balance, "Invalid bet");

        balance -= bet;

        return bet;
    }


    public String getAvailableOptions(Hand hand) {
        maxSelection = 2;
        StringBuilder output = new StringBuilder();
        output.append("0. Quit\n1. Hit\n2. Stand");
        //TODO create logic to add Double
        //TODO pt1 Confirm First Turn;
        //TODO pt2 Confirm has enough funds
        if (hand.getCards().size() == 2 && balance >= hand.getBet()) {
            output.append("\n3. Double");
            maxSelection++;
            if (hand.hasPair()) {
                output.append("\n4. Split");
                maxSelection++;
            }
        }
        //Doubles current bet and hits another card, stands after that
        //TODO pt3 add logic for Split to detect pair

        return output.toString();
    }

    @Override
    public byte getAction(Hand hand, int dealerValue) {
        //display hand and value
        System.out.println(hand.displayHand());
        System.out.println(hand.getValue());
        //display available actions
        System.out.println(getAvailableOptions(hand));

        //get selected action
        return (byte) Console.getInt(0, maxSelection, "", "Invalid Selection");
    }

    @Override
    public void addBalance(int winnings) {
        balance += winnings;
    }
}
