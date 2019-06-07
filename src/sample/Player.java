package sample;

import java.util.ArrayList;

public class Player {
    private double credit;
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

    public ArrayList<Card> getPlayerCards() {return playerCards;}
}
