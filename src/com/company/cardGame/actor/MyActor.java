package com.company.cardGame.actor;

import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class MyActor implements Actor {

    /*
    This guy right here is gonna be a stinky cheater, because that's fun
    If the Dealer is reasonably under 21 he's going to access the rigged deck and build out the perfect hand
    This will be done via doubling the bet if not at 21, then getting the card from RD to get to 21
    This, in theory, should win every time
     */

    @Override
    public String getName() {
        return null;
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
        return 0;
    }

    @Override
    public void addBalance(int winnings) {

    }
}
