package com.company.cardGame.actor;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Dealer implements Actor{

    @Override
    public String getName() {
        return "John Wick";
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public int placeBet() {
        return 0;
    }




    @Override
    public byte getAction(Hand hand) {

        return hand.getValue() < 17 ? HIT : STAND;
    }

    @Override
    public void addBalance(int winnings) {
    }
}
