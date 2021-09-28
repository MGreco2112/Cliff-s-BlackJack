package com.company.cardGame.blackJack;

import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.StandardDeck;
import com.company.cardGame.actor.Dealer;
import com.company.cardGame.actor.Player;

public class Table {
    private Hand player = new Hand(new Player("Player"));
    private Hand dealer = new Hand(new Dealer());
    private Deck deck;

    public void playRound() {
        deck  = new StandardDeck();
        deck.shuffle();
        /*
        0. take bets
        1. deal cards
        a2. declare winner
        b2. players turn
        b3. dealers turn
        b4. declare winner
         */
        deal();
        displayTable();
        turn(player);
        determineWinner();
    }

    private void deal() {
        for (int count = 0; count < 2; count++) {
            player.addCard(deck.draw());
            dealer.addCard(deck.draw());
        }
    }

    private void displayTable() {
        StringBuilder output = new StringBuilder();

        output.append("Dealer: ").append(dealer.displayHand()).append("\n");
        output.append("Player: ").append(player.displayHand()).append("\n");

        System.out.println(output);
    }

    private void determineWinner() {
        if (player.getValue() > dealer.getValue()) {
            System.out.println("Player wins");
            return;
        }

        if (player.getValue() == dealer.getValue()) {
            System.out.println("Push");
            return;
        }

        System.out.println("Dealer wins");
    }

    private void turn(Hand activeHand) {
        System.out.println("Dealer: " + dealer.displayHand());
        byte action = activeHand.getAction();
        switch (action) {
            case 0 -> System.out.println("Quit");
            case 1 -> System.out.println("Hit");
            case 2 -> System.out.println("Stand");
            case 3 -> System.out.println("Double Down");
            case 4 -> System.out.println("Split");
            default -> System.out.println("Invalid selection " + action);
        }
    }

    private void hit(Hand activeHand) {
        //todo hit
    }

    private void stand(Hand activeHand) {
        //todo stand
    }

    private void doubleDown(Hand activeHand) {
        //todo double
    }

    private void split(Hand activeHand) {
        //todo split
    }
}
