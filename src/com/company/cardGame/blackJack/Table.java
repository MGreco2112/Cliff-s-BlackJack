package com.company.cardGame.blackJack;

import com.company.Utils.Console;
import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.RiggedDeck;
import com.company.cardGame.deck.StandardDeck;
import com.company.cardGame.actor.Dealer;
import com.company.cardGame.actor.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {
//    private Hand player = new Hand(new Player("Player"));
    private Hand dealer = new Hand(new Dealer());
    private Deck deck;
    private int NUMBER_OF_HANDS;
    private final List<Hand> hands = new ArrayList<>();
    //todo get the multi player system working
    public static final int BUST_VALUE = 21;

    public Table() {
        NUMBER_OF_HANDS = Console.getInt(0, 8, "How many players?", "Too many Players...");

        while (hands.size() < NUMBER_OF_HANDS) {
            String name = Console.getString("Enter a Name: ", true);

            hands.add(new Hand(new Player(name)));
        }
    }

    public void playRound() {
        deck  = new StandardDeck();
//        deck = new RiggedDeck();
        deck.shuffle();
        /*
        0. take bets
        1. deal cards
        a2. declare winner
        b2. players turn
        b3. dealers turn
        b4. declare winner
         */
        for (Hand player : hands) {
            player.placeBet();
        }

        deal();
        for (Hand player : hands) {
            displayTable(player);
            while (turn(player)) {
                if (player.getValue() > BUST_VALUE) {
                    System.out.println("BUSTED!!!");
                    break;
                }
            }
        }
        turn(dealer);
        determineWinner();
//        System.out.println("Balance: " + player.getBalance());
    }

    private void deal() {
        for (int count = 0; count < 2; count++) {
            for (Hand player : hands) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }

    private void displayTable(Hand player) {
        StringBuilder output = new StringBuilder();

        output.append(dealer.getName()).append(" ").append(dealer.displayHand()).append("\n");
        output.append(player.getName()).append(" ").append(player.displayHand()).append("\n");

        System.out.println(output);
    }

    private void determineWinner() {

        Hand player = null;
        List<Hand> players = new ArrayList<>();
        int highestScore = 0;

        for (Hand checkedPlayer : hands) {
            if (checkedPlayer.getValue() > highestScore && checkedPlayer.getValue() <= BUST_VALUE) {
                player = checkedPlayer;
                highestScore = checkedPlayer.getValue();
                if (players.size() != 0) {
                    players.clear();
                }
            } else if (checkedPlayer.getValue() <= BUST_VALUE && checkedPlayer.getValue() == highestScore) {
                players.add(player);
                players.add(checkedPlayer);
                player = null;
            }
            /* else if (checkedPlayer.getBalance() == highestScore) {
                -Create an array of Hands of the same score
                -Compare them to the dealer's score
                -If they're higher without busting:
                    --They win
                -Else:
                    --The Dealer wins
            }
            */
        }


        if (player == null && players.size() == 0 || player.getValue() > BUST_VALUE) {
            System.out.println(dealer.getName() + " wins");
            return;
        }

        System.out.println(dealer.getName() + " " + dealer.getValue());
        System.out.println(player.getName() + " " + player.getValue());

        if (player.getValue() > dealer.getValue() || players.size() > 0 && players.get(0).getValue() > dealer.getValue() || dealer.getValue() > BUST_VALUE) {
            if (player != null) {
                System.out.println(player.getName() + " wins");
                player.payout(player.NORMALPAY);
            } else {
                for (Hand singlePlayer : players) {
                    System.out.println(singlePlayer.getName() + " wins!");
                    singlePlayer.payout(player.NORMALPAY);
                }
            }
            return;
        }

        if (player.getValue() == dealer.getValue() || players.size() > 0 && players.get(0).getValue() > dealer.getValue()) {
            System.out.println("Push");
            if (player != null) {
                player.payout(player.PUSHPAY);
            } else {
                for (Hand singlePlayer : players) {
                    singlePlayer.payout(player.PUSHPAY);
                }
            }
            return;
        }

        System.out.println(dealer.getName() + " wins");
    }

    private boolean turn(Hand activeHand) {

        System.out.println("Dealer: " + dealer.displayHand());
        byte action = activeHand.getAction();
        switch (action) {
            case Actor.QUIT -> stand(activeHand);
            case Actor.HIT -> hit(activeHand);
            case Actor.STAND -> stand(activeHand);
            case Actor.DOUBLE -> doubleDown(activeHand);
            case Actor.SPLIT -> split(activeHand);
            default -> {
                System.out.println("Invalid selection " + action);
                return false;
            }
        }



        if (action == Actor.QUIT || action == Actor.STAND) {
            return false;
        }

        return true;



    }

    private void hit(Hand activeHand) {
        //todo hit
        activeHand.addCard(deck.draw());
//        System.out.println(activeHand.displayHand());
        System.out.println(activeHand.displayHand() + "\n" + activeHand.getValue());
        System.out.println("HIT!");
    }

    private void stand(Hand activeHand) {
        //todo stand
        System.out.println("Stand!\n" + activeHand.displayHand() + "\n" + activeHand.getValue());
    }

    private void doubleDown(Hand activeHand) {
        //todo double
        activeHand.doubleBet();
        System.out.println("Doubled Down!");
        hit(activeHand);
        stand(activeHand);
    }

    private void split(Hand activeHand) {
        //todo split
        doubleDown(activeHand);
        // create a new hand with the name of the active hands HOLDER
        // newHand.placeBet();
        // deal two cards into it
        // add new hand into the hands list

//        Hand newHand = new Hand(new Player(activeHand.getName()));
//        newHand.placeBet();
//        while (newHand.getCards().size() > 2) {
//            newHand.addCard(deck.draw());
//        }
//        hands.add(newHand);
    }
}
