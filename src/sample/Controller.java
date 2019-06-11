package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Controller {
    Game game = new Game ();
    Player player = new Player (300);

    @FXML
    private Label credit;

    @FXML
    private Label cardD1V;

    @FXML
    private Label cardD2V;

    @FXML
    private Label cardP1V;

    @FXML
    private Label cardP2V;

    @FXML
    private Label cardD3V;

    @FXML
    private Label cardD4V;

    @FXML
    private Label cardP3V;

    @FXML
    private Label cardP4V;

    @FXML
    private Label cardD5V;

    @FXML
    private Label cardD6V;

    @FXML
    private Label cardP5V;

    @FXML
    private Label cardP6V;

    @FXML
    private Label dealerScore;

    @FXML
    private Label playerScore;

    @FXML
    private Label alert;

    @FXML
    private ImageView cardD1S;

    @FXML
    private ImageView cardD2S;

    @FXML
    private ImageView cardD3S;

    @FXML
    private ImageView cardD4S;

    @FXML
    private ImageView cardD5S;

    @FXML
    private ImageView cardD6S;

    @FXML
    private ImageView cardP1S;

    @FXML
    private ImageView cardP2S;

    @FXML
    private ImageView cardP3S;

    @FXML
    private ImageView cardP4S;

    @FXML
    private ImageView cardP5S;

    @FXML
    private ImageView cardP6S;

    public void restart () {
        game = new Game ();
        player = new Player(300);

        StringProperty valueZero = new SimpleStringProperty("0");
        StringProperty stringNull = new SimpleStringProperty();

        credit.textProperty().bind(valueZero);
        playerScore.textProperty().bind(valueZero);
        dealerScore.textProperty().bind(valueZero);

        cardD1V.textProperty().bind(stringNull);
        cardD2V.textProperty().bind(stringNull);
        cardD3V.textProperty().bind(stringNull);
        cardD4V.textProperty().bind(stringNull);
        cardD5V.textProperty().bind(stringNull);
        cardD6V.textProperty().bind(stringNull);

        cardP1V.textProperty().bind(stringNull);
        cardP2V.textProperty().bind(stringNull);
        cardP3V.textProperty().bind(stringNull);
        cardP4V.textProperty().bind(stringNull);
        cardP5V.textProperty().bind(stringNull);
        cardP6V.textProperty().bind(stringNull);

        cardD1S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardD2S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardD3S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardD4S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardD5S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardD6S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));

        cardP1S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardP2S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardP3S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardP4S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardP5S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
        cardP6S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));
    }

    public void initialize() {
        String auxPlayerCredit = Double.toString(player.getCredit());
        StringProperty playerCredit = new SimpleStringProperty(auxPlayerCredit);

        credit.textProperty().bind(playerCredit);
    }

    public int getScore (Player player) {
        int counter = 0;
        boolean ace = false, figure = true;

        for (Card card : player.getPlayerCards()) {
            if (card.getNumber() > 10) {
                counter+=10;
                figure = true;
            }
            else if (card.getNumber() == 1) ace = true;
            else counter+=card.getNumber();
        }

        if (player.getPlayerCards().size() == 2 && ace && (figure || counter > 5)) counter+=11;
        else if (ace) counter+=1;

        return counter;
    }

    public void refreshScore () {
        StringProperty pScore = new SimpleStringProperty(Integer.toString(getScore(player)));
        playerScore.textProperty().bind(pScore);

        StringProperty dScore = new SimpleStringProperty(Integer.toString(getScore(game.dealer)));
        dealerScore.textProperty().bind(dScore);
    }

    public void alert(int result) {
        String auxAlert;
        StringProperty alertString;

        if (result == 0 ) auxAlert = "DRAW";
        else if (result == 1 ) auxAlert = "WIN";
        else if (result == 2 ) auxAlert = "LOSE";
        else if (result == 3 ) auxAlert = "BUST";
        else if (result == 4 ) auxAlert = "BLACK JACK";
        else auxAlert ="";

        alertString = new SimpleStringProperty(auxAlert);

        alert.textProperty().bind(alertString);
    }

    public StringProperty conversion (int card) {
        String auxCardValue;
        StringProperty cardValue;

        if (card == 1) auxCardValue = "A";
        else if (card == 11) auxCardValue = "J";
        else if (card == 12) auxCardValue = "Q";
        else if (card == 13) auxCardValue = "K";
        else auxCardValue = Integer.toString(card);
        cardValue = new SimpleStringProperty(auxCardValue);

        return cardValue;
    }

    public Image setSeed (Card.Seed seed) {
        Image image;

        if (seed == Card.Seed.HEARTS) image = new Image("http://irjneishere.altervista.org/other/javafxproject/seedHearts.png");
        else if (seed == Card.Seed.CLUBS) image = new Image("http://irjneishere.altervista.org/other/javafxproject/seedClubs.png");
        else if (seed == Card.Seed.DIAMONDS) image = new Image("http://irjneishere.altervista.org/other/javafxproject/seedDiamonds.png");
        else image = new Image("http://irjneishere.altervista.org/other/javafxproject/seedSpades.png");

        return image;
    }

    public void initialCards (Player player, int type) {
        Label name;
        ImageView seedName;
        StringProperty cardValue;

        for (int i=0; i<player.getPlayerCards().size(); i++) {
            int card = player.getPlayerCards().get(i).getNumber();
            Card.Seed seed = player.getPlayerCards().get(i).getSeed();

            cardValue = conversion(card);

            if (i==0 && type==0) {
                name = cardD1V;
                seedName = cardD1S;
            }
            else if (i==1 && type==0) {
                name = cardD2V;
                seedName = cardD2S;
            }
            else if (i==0 && type==1) {
                name = cardP1V;
                seedName = cardP1S;
            }
            else {
                name = cardP2V;
                seedName = cardP2S;
            }

            seedName.setImage(setSeed(seed));
            name.textProperty().bind(cardValue);
        }
    }

    public void dealerHitCards () {
        Label name;
        ImageView seedName;
        StringProperty cardValue;

        for (int i=2; i<game.dealer.getPlayerCards().size(); i++) {
            int card = game.dealer.getPlayerCards().get(i).getNumber();
            Card.Seed seed = player.getPlayerCards().get(i).getSeed();

            cardValue = conversion(card);

            if (i==2) {
                name = cardD3V;
                seedName = cardD3S;
            }
            else if (i==3) {
                name = cardD4V;
                seedName = cardD4S;
            }
            else if (i==4) {
                name = cardD5V;
                seedName = cardD5S;
            }
            else {
                name = cardD6V;
                seedName = cardD6S;
            }

            seedName.setImage(setSeed(seed));
            name.textProperty().bind(cardValue);
        }
    }

    public void newCard () {
        Label name;
        ImageView seedName;
        StringProperty cardValue;

        int position = player.getPlayerCards().size();
        System.out.println(position);
        if (position == 3) {
            name = cardP3V;
            seedName = cardP3S;
        }
        else if (position == 4) {
            name = cardP4V;
            seedName = cardP4S;
        }
        else if (position == 5) {
            name = cardP5V;
            seedName = cardP5S;
        }
        else {
            name = cardP6V;
            seedName = cardP6S;
        }

        int card = player.getPlayerCards().get(position-1).getNumber();
        Card.Seed seed = player.getPlayerCards().get(position-1).getSeed();

        cardValue = conversion(card);

        seedName.setImage(setSeed(seed));
        name.textProperty().bind(cardValue);
    }

    @FXML
    void bet5() {
        game.mixCards(10);
        game.betAndGetCards(player, 5);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
    }

    @FXML
    void bet10() {
        game.mixCards(10);
        game.betAndGetCards(player, 10);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
    }

    @FXML
    void bet100() {
        game.mixCards(10);
        game.betAndGetCards(player, 100);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
    }

    @FXML
    void bet50() {
        game.mixCards(10);
        game.betAndGetCards(player, 50);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
    }

    @FXML
    void betAll () {
        game.mixCards(10);
        double credit = player.getCredit();
        game.betAndGetCards(player, credit);

        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
    }

    @FXML
    void hit() {
        int result = game.hit(player);

        newCard();
        refreshScore();

        alert(result);
        initialize();
    }

    @FXML
    void stand() {
        int result = game.stand(player);
        dealerHitCards();

        refreshScore();

        alert(result);
        initialize();
    }

}
