package com.company.cardGame.deck;

public class BlackjackCard extends Card{

    public BlackjackCard(int rank, String suit) {
        super(rank, suit);
    }

    @Override
    public String display() {
        if (!isFaceDown) {
            String output = "";
            switch (rank) {
                case 1 -> output = "AC";
                case 11 -> output = "JA";
                case 12 -> output = "QU";
                case 13 -> output = "KI";
//            case 0 -> output = "JR"; //joker
                default -> output = rank == 10 ? Integer.toString(rank) : " " + rank;
            }
            return output + suit;
        }

        return "<*>";
    }
}
