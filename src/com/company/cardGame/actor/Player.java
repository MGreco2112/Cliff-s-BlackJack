package com.company.cardGame.actor;

import com.company.Utils.Console;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Player implements Actor {
    private final String NAME;
    private int balance = 1000;
    private int currentTurn = 1;
    private int maxSelection = 2;
    private int currentBet = 0;
    private Hand HAND;

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
        int bet = Console.getInt(1, balance, "Enter a bet between 1 and " + balance, "Invalid bet");

        currentBet = bet;

        return bet;
    }

    public String getAvailableOptions() {
        maxSelection = 2;
        StringBuilder output = new StringBuilder();
        output.append("0. Quit\n1. Hit\n2. Stand");
        //TODO create logic to add Double
        //TODO pt1 Confirm First Turn;
        //TODO pt2 Confirm has enough funds
        if (currentTurn == 1 && balance >= currentBet) {
            output.append("\n3. Double");
            maxSelection++;
        }
        //Doubles current bet and hits another card, stands after that
        //TODO pt3 add logic for Split to detect pair

        return output.toString();
    }

    public int checkPair(Hand hand) {
        HAND = hand;

        for (int i = 0; i < hand.getCards().size(); i++) {
            int pairs = 0;
            for (int j = i + 1; j < hand.getCards().size(); j++) {
                if (hand.getCards().get(i).getValue() == hand.getCards().get(j).getValue()) {
                    pairs++;
                }
            }
            if (pairs > 0) {
                return 1;
            }
        }


        return 0;
    }

    @Override
    public byte getAction(Hand hand) {
        //display hand and value
        System.out.println(hand.displayHand());
        System.out.println(hand.getValue());
        //display available actions
        System.out.println(getAvailableOptions());

        //get selected action
        currentTurn++;
        return (byte) Console.getInt(0, maxSelection, "", "Invalid Selection");
    }
}
