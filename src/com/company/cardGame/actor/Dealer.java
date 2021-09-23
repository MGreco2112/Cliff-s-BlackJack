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
    public int getBet(int wallet) {
        return 0;
    }

    @Override
    public byte getAction(Hand hand) {
        int handValue = hand.getValue();
        if (handValue < 17) {
            return HIT;
        }
        return 0;
    }
}
