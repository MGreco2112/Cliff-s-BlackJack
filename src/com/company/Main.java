package com.company;

import com.company.cardGame.blackJack.Hand;
import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.StandardDeck;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new StandardDeck();
        deck.shuffle();
        Hand myHand = new Hand();
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());
        myHand.addCard(deck.draw());
        System.out.println(myHand.displayHand());
        System.out.println(myHand.getValue());

    }
}
