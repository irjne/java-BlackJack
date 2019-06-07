package sample;

public class Card {
    enum Seed {
        HEARTS, //cuori
        DIAMONDS, //quadri
        CLUBS, //fiori
        SPADES //picche
    }

    private Seed seed;
    private int number;

    public Card(Seed seed, int number) {
        this.seed = seed;
        this.number = number;
    }

    public Seed getSeed() {
        return seed;
    }
    public int getNumber() {
        return number;
    }
}
