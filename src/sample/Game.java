package sample;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Player dealer;
    private ArrayList<Card> cards;

    public Game() {
        this.cards = new ArrayList<Card>();
        this.dealer = new Player ();

        for(int i=1; i<14; i++) {
            this.cards.add(new Card(Card.Seed.HEARTS, i));
            this.cards.add(new Card(Card.Seed.DIAMONDS, i));
            this.cards.add(new Card(Card.Seed.CLUBS, i));
            this.cards.add(new Card(Card.Seed.SPADES, i));
        }
    }

    //mescolamento delle carte: estraiamo un oggetto di indice random e lo posizioniamo nell'indice "i" gestito dal ciclo for che scorre l'intero mazzo
    public void mixCards (int cicles) {
        for (int i=0; i<this.cards.size(); i++) {
            Random random = new Random();
            int extraction = random.nextInt(51);

            Card card = this.cards.get(extraction);
            this.cards.remove(card);
            this.cards.add(i, card);
        }

        if (cicles>0) mixCards(cicles-1);
    }

    @Override
    public String toString() {
        String result = "";
        for (Card card : this.cards) {
             result += card.getNumber() + " " + card.getSeed() + "\n";
        }
        return result;
    }

    //assegnazione carte
    public void getCard (Player player) {
        //controlla se il mazzo è finito
        player.getPlayerCards().add(this.cards.get(0));
        this.cards.remove(0);
    }

    //puntata e assegnazione delle carte
    public boolean betAndGetCards (Player player, double bet) {
        if (bet > player.getCredit()) return false;

        player.setCredit(player.getCredit()-bet);
        for (int i=0; i<2; i++) {
            getCard(player);
            getCard(dealer);
        }

        checkWinner(player, bet);
        return true;
    }

    public int dealerScore () {
        int value = 0;
        boolean ace = false;
        boolean jack = false;

        if (dealer.getPlayerCards().size()==2) {
            for (Card card : dealer.getPlayerCards()) {
                if (card.getNumber() == 1) ace = true;
                if (card.getNumber() > 5 ) jack = true;

                value+=card.getNumber();
            }


            if (ace && jack) return value+10;
            else return value;
        }

        for (Card card : dealer.getPlayerCards()) {
            value+=card.getNumber();
        }

        if (value < 17) {
            getCard(dealer);
            dealerScore();
        }

        return value;
    }

    public int checkWinner (Player player, double bet) {
        int value=0, dealerScore = 0;
        boolean ace = false, figure = false;

        //caso black jack
        if (player.getPlayerCards().size()==2) {
            for (int i=0; i<player.getPlayerCards().size(); i++ ) {
                if (player.getPlayerCards().get(i).getNumber() == 1) ace = true;
                if (player.getPlayerCards().get(i).getNumber() > 9 ) figure = true;
            }

            if (ace && figure) {
                player.setCredit(player.getCredit()+bet+(bet*1.5));
                return 1;
            }
        }

        for (Card card : player.getPlayerCards()) {
            value+=card.getNumber();
        }

        dealerScore = dealerScore();

        //vittoria
        if (value==21) {
            if (dealerScore == 21) player.setCredit(player.getCredit()+bet);
            else player.setCredit(player.getCredit()+bet*2);
            return 2;
        }
        //perdita
        if (value > 21 || dealerScore < 22 && dealerScore > value) return 0;

        //stand o hint, da implementare
        return 3;
    }

    //testing
    public static void main(String[] args) {
        Game game = new Game();
        /*System.out.println(game);
        System.out.println();
        game.mixCards(5);
        System.out.println(game);*/


    }
}
