package sample;

import java.util.ArrayList;

public class Player {
    private double credit;
    private double bet;
    private ArrayList<Card> playerCards;

    public Player(double credit) {
        this.credit = credit;
        this.playerCards = new ArrayList<Card>();
    }

    public Player() {
        this.playerCards = new ArrayList<Card>();
    }

    public double getCredit() {return credit;}
    public void setCredit(double credit) {this.credit = credit;}

    public double getBet() {return bet;}
    public void setBet(double bet) {this.bet = bet;}

    public ArrayList<Card> getPlayerCards() {return playerCards;}

    @Override
    public String toString() {
        String result =  "Player [credit: "+ credit + " " + "bet: " + bet + " --- ";

        for (Card card : playerCards) {
            result+= card.getNumber() + " ";
        }

        return result + "]";
    }
}
