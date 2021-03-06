package com.company.cardGame.blackJack;

import com.company.Utils.Console;
import com.company.cardGame.deck.BlackjackCard;
import com.company.cardGame.deck.Deck;
import com.company.cardGame.deck.StandardDeck;
import com.company.cardGame.actor.Dealer;
import com.company.cardGame.actor.Player;

import java.util.ArrayList;
import java.util.List;

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

    public void playGame() {
        while (true) {
            playRound();
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
        displayTable();
        playerTurns();
        turn(dealer);
        determineWinner();
        displayBalances();
        clearHands();
    }

    private void playerTurns() {
        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).revealHand();
            while (turn(hands.get(i))) {
                if (hands.get(i).getValue() > BUST_VALUE) {
                    System.out.println("BUSTED!!!");
                    break;
                }
            }
            Console.getString("Press Enter to Continue", false);
        }
    }

    private void displayBalances() {
        for (Hand player : hands) {
            System.out.println(player.getName() + "'s Balance: $" + player.getBalance());
        }
//        System.out.println("Balance: " + player.getBalance());
    }

    private void deal() {
        for (int count = 0; count < 2; count++) {
            dealer.addCard(deck.draw());
            for (Hand player : hands) {
                if (count == 0) {
                    player.addCard(deck.draw());
                } else {
                    BlackjackCard blackjackCard = deck.draw();
                    blackjackCard.flip();
                    player.addCard(blackjackCard);
                }
            }
        }
    }

    private void displayTable() {
        StringBuilder output = new StringBuilder();

        output.append(dealer.getName()).append(" ").append(dealer.displayHand()).append("\n");
        for (Hand player : hands) {
            output.append(player.getName()).append(" ").append(player.displayHand()).append(" | ");
        }

        System.out.println(output);
    }

    private void determineWinner() {

        Hand player = null;
        List<Hand> players = new ArrayList<>();
        int highestScore = 0;

        dealer.revealHand();

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


        if (player == null && players.size() == 0 || player != null && player.getValue() > BUST_VALUE) {
            System.out.println(dealer.getName() + " wins");
            return;
        }

        System.out.println(dealer.getName() + " " + dealer.getValue());
//        System.out.println(player.getName() + " " + player.getValue());

        if (player != null && player.getValue() > dealer.getValue() || player == null &&
                players.size() > 0 && players.get(0).getValue() > dealer.getValue() ||
                dealer.getValue() > BUST_VALUE) {
            if (player != null) {
                System.out.println(player.getName() + " wins");
                player.payout(player.NORMALPAY);
            } else {
                for (Hand singlePlayer : players) {
                    System.out.println(singlePlayer.getName() + " wins!");
                    singlePlayer.payout(singlePlayer.NORMALPAY);
                }
            }
            return;
        }

        if (player != null && player.getValue() == dealer.getValue()
                || player == null && players.size() > 0 && players.get(0).getValue() > dealer.getValue()) {
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
        System.out.println(activeHand.getName());
        byte action = activeHand.getAction(dealer.getValue());
        switch (action) {
            case Actor.QUIT -> System.exit(0);
            case Actor.HIT -> hit(activeHand);
            case Actor.STAND -> stand(activeHand);
            case Actor.DOUBLE -> doubleDown(activeHand);
            case Actor.SPLIT -> split(activeHand);
            default -> {
                System.out.println("Invalid selection " + action);
                return false;
            }
        }


        return action != Actor.STAND;


    }

    private void hit(Hand activeHand) {
        //todo hit
        BlackjackCard blackjackCard = deck.draw();
        blackjackCard.flip();
        activeHand.addCard(blackjackCard);
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
//        doubleDown(activeHand);
        activeHand.doubleBet();
        Hand newHand = activeHand.splitHand();
        BlackjackCard blackjackCard1 = deck.draw();
        blackjackCard1.flip();
        activeHand.addCard(blackjackCard1);
        BlackjackCard blackjackCard2 = deck.draw();
        blackjackCard2.flip();
        newHand.addCard(blackjackCard2);
        hands.add(newHand);

    }

    private void clearHands() {
        for (Hand player : hands) {
            player.clearHands();
        }

        dealer.clearHands();

        while (hands.size() > NUMBER_OF_HANDS) {
            hands.remove(hands.size() - 1);
        }

    }
}
