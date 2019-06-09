package sample;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public Player getDealer() {
        return dealer;
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

    //assegnazione carte
    public boolean getCard (Player player) {
        if (this.cards.size() > 0 ) {
            player.getPlayerCards().add(this.cards.get(0));
            this.cards.remove(0);
            return true;
        }
        return false;
    }

    //puntata e assegnazione delle carte
    public boolean betAndGetCards (Player player, double bet) {
        if (bet > player.getCredit()) return false;

        player.setBet(bet);
        player.setCredit(player.getCredit()-bet);
        for (int i=0; i<2; i++) {
            getCard(player);
            getCard(dealer);
        }

        return true;
    }

    public int getScore (Player player) {
        int score = 0;

        for (Card card : player.getPlayerCards()) {
            if (card.getNumber()> 10) score+=10;
            else score+=card.getNumber();
        }
        return score;
    }

    public int dealerScore () {
        int score = getScore(dealer);
        boolean ace = false;
        boolean jack = false; //controlla se l'asso può valere 11

        while (score < 17) {
            getCard(dealer);
            score = getScore(dealer);
        }

        if (dealer.getPlayerCards().size()==2) {
            for (Card card : dealer.getPlayerCards()) {
                if (card.getNumber() == 1) ace = true;
                if (card.getNumber() > 5 ) jack = true;
            }

            if (ace && jack) return score+10;
            else return score;
        }

        return score;
    }

    public int bustOrBlackJack (Player player) {
        int score=getScore(player);
        boolean ace = false, figure = false;

        //bust
        if (score > 21) return 0;

        //black jack
        if (player.getPlayerCards().size()==2) {
            for (int i=0; i<player.getPlayerCards().size(); i++ ) {
                if (player.getPlayerCards().get(i).getNumber() == 1) ace = true;
                if (player.getPlayerCards().get(i).getNumber() > 9 ) figure = true;
            }

            if (ace && figure) {
                return 1;
            }
        }

        return 2;
    }

    public void stand (Player player) {
        int score=getScore(player), dealerScore = dealerScore();

        if (score == dealerScore) {
            player.setCredit(player.getCredit()+player.getBet());
            System.out.println("DRAW.");
        }

        else if ((score==21 && dealerScore < 21) || (score < 22 && score > dealerScore)) {
            player.setCredit(player.getCredit()+player.getBet()*2);
            System.out.println("WIN.");
        }

        else if (score < 22 && score < dealerScore) {
            System.out.println("LOSE.");
        }
    }

    public boolean hit (Player player) {
        if (bustOrBlackJack(player) == 2) return getCard(player);
        return false;
    }

    @Override
    public String toString() {
        String result =  "Dealer [";

        for (Card card : dealer.getPlayerCards()) {
            result+= card.getNumber() + " ";
        }

        return result + "]";

        /*String result = "";
        for (Card card : this.cards) {
            result += card.getNumber() + " " + card.getSeed() + "\n";
        }
        return result;*/
    }

    //testing
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player (4000);
        int choice, end = 0;

        game.mixCards(5);
        game.betAndGetCards(player, 100);

        Scanner scanIn = new Scanner(System.in);
        System.out.println(player);

        if (end != 1 && game.getScore(player) < 21 && game.bustOrBlackJack(player) == 2) {
            do {
                System.out.println("Stand 1, Hit 2: ");
                choice = scanIn.nextInt();

                switch (choice) {
                    case 1: {
                        System.out.println(player);
                        System.out.println(game);
                        game.stand(player);
                        System.out.println(player);
                        System.out.println(game);
                    }
                    break;
                    case 2: {
                        if (game.getScore(player) < 21) game.hit(player);
                        else {
                            System.out.println("BUST.");
                            end = 1;
                            return;
                        }

                        System.out.println(player);
                        System.out.println(game);
                    }
                    break;
                    default: System.out.println("Error: invalid choice.");
                }
            } while (choice!=1 && end != 1);
        }
        else if (game.bustOrBlackJack(player) == 1 && game.getScore(game.getDealer()) < 21) {
            player.setCredit(player.getCredit()+player.getBet()+(player.getBet()*1.5));
            System.out.println("BLACK JACK.");
            return;
        }
        else if (game.bustOrBlackJack(player) == 1 && game.getScore(game.getDealer()) == 21) {
            player.setCredit(player.getCredit()+player.getBet());
            System.out.println("DRAW.");
            return;
        }
    }
}
