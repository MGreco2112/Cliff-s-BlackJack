package com.company;

import com.company.Utils.Console;
import com.company.cardGame.actor.Player;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;
import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.StandardDeck;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new StandardDeck();
        deck.shuffle();
        Actor dealer = new Player(Console.getString("Player Name?", true));
        Hand myHand = new Hand(dealer);
        myHand.addCard(deck.draw());
        myHand.addCard(deck.draw());
        System.out.println(myHand.hasPair());
        byte action = myHand.getAction();
        while (action != Actor.QUIT) {
            if (action == Actor.HIT) {
                myHand.addCard(deck.draw());
                System.out.println("HIT!");
            } else if (action == Actor.STAND) {
                System.out.println("STAND!");
            } else if (action == Actor.DOUBLE) {
                myHand.doubleBet();
                myHand.addCard(deck.draw());
                System.out.println("Doubled Down!");
            } else if (action == Actor.SPLIT) {
                System.out.println("Will create a new Hand that goes to that player");
            }

            action = myHand.getAction();
        }

//        while (myHand.getAction() == Actor.HIT) {
//            myHand.addCard(deck.draw());
//            System.out.println("HIT!");
//
//        }
//        while (myHand.getAction() == Actor.DOUBLE) {
//            myHand.doubleBet();
//            myHand.addCard(deck.draw());
//            System.out.println("Doubled Down!");
//        }
        System.out.println("Done");
    }
}
