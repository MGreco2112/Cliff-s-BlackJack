package com.company;

import com.company.Utils.Console;
import com.company.cardGame.actor.Player;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;
import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.RiggedDeck;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new RiggedDeck();
        deck.shuffle();
        Actor dealer = new Player(Console.getString("Player Name?", true));
        Hand myHand = new Hand(dealer);
        myHand.addCard(deck.draw());
        myHand.addCard(deck.draw());
        System.out.println(myHand.checkPair());
//        while (myHand.getAction() == Actor.HIT) {
//            myHand.addCard(deck.draw());
//            System.out.println("HIT!");
//
//        }
        while (myHand.getAction() == Actor.DOUBLE) {
            myHand.doubleBet();
            myHand.addCard(deck.draw());
            System.out.println("Doubled Down!");
        }
        System.out.println("Done");
    }
}
