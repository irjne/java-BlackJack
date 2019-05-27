import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Card> cards;

    public Game() {
        this.cards = new ArrayList<Card>();

        for(int i=1; i<14; i++) {
            this.cards.add(new Card(Card.Seed.HEARTS, i));
            this.cards.add(new Card(Card.Seed.DIAMONDS, i));
            this.cards.add(new Card(Card.Seed.CLUBS, i));
            this.cards.add(new Card(Card.Seed.SPADES, i));
        }
    }

    //estraiamo un oggetto di indice random e lo posizioniamo nell'indice "i" gestito dal ciclo for che scorre l'intero mazzo
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

    public void getCard (Player player) {
        //controlla se il mazzo è finito
        player.getPlayerCards().add(this.cards.get(0));
        this.cards.remove(0);
    }

    public boolean betAndGetCards (Player player, double bet) {
        if (bet > player.getCredit()) return false;

        for (int i=0; i<2; i++) {
            getCard(player);
        }
        return true;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.mixCards(5);
        System.out.println(game);
    }
}
