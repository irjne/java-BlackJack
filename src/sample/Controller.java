package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

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

    @FXML
    private Button bet5;

    @FXML
    private Button bet10;

    @FXML
    private Button bet50;

    @FXML
    private Button bet100;

    @FXML
    private Button betallin;

    @FXML
    private Button hit;

    @FXML
    private Button stand;

    @FXML
    private Pane cardP1;

    @FXML
    private Pane cardP2;

    @FXML
    private Pane cardP3;

    @FXML
    private Pane cardP4;

    @FXML
    private Pane cardP5;

    @FXML
    private Pane cardP6;

    @FXML
    private Pane cardD1;

    @FXML
    private Pane cardD2;

    @FXML
    private Pane cardD3;

    @FXML
    private Pane cardD4;

    @FXML
    private Pane cardD5;

    @FXML
    private Pane cardD6;

    public Game game1 = new Game();

    public void newGame () {
        bet5.setDisable(false);
        bet10.setDisable(false);
        bet50.setDisable(false);
        bet100.setDisable(false);
        betallin.setDisable(false);
        hit.setDisable(false);
        stand.setDisable(false);

        game = new Game ();
        game.dealer = new Player();
        player = new Player(300);

        StringProperty valueZero = new SimpleStringProperty("0");
        StringProperty stringNull = new SimpleStringProperty();

        credit.textProperty().bind(new SimpleStringProperty(Double.toString(player.getCredit())));
        playerScore.textProperty().bind(valueZero);
        dealerScore.textProperty().bind(valueZero);

        alert.textProperty().bind(stringNull);

        cardD1.setVisible(false);
        cardD2.setVisible(false);
        cardD3.setVisible(false);
        cardD4.setVisible(false);
        cardD5.setVisible(false);
        cardD6.setVisible(false);

        cardP1.setVisible(false);
        cardP2.setVisible(false);
        cardP3.setVisible(false);
        cardP4.setVisible(false);
        cardP5.setVisible(false);
        cardP6.setVisible(false);
    }

    public void restart () {
        bet5.setDisable(false);
        bet10.setDisable(false);
        bet50.setDisable(false);
        bet100.setDisable(false);
        betallin.setDisable(false);
        hit.setDisable(false);
        stand.setDisable(false);

        game.dealer = new Player();
        player.setPlayerCards(new ArrayList<Card>());

        StringProperty valueZero = new SimpleStringProperty("0");
        StringProperty stringNull = new SimpleStringProperty();

        credit.textProperty().bind(new SimpleStringProperty(Double.toString(player.getCredit())));
        playerScore.textProperty().bind(valueZero);
        dealerScore.textProperty().bind(valueZero);

        alert.textProperty().bind(stringNull);

        cardD1.setVisible(false);
        cardD2.setVisible(false);
        cardD3.setVisible(false);
        cardD4.setVisible(false);
        cardD5.setVisible(false);
        cardD6.setVisible(false);

        cardP1.setVisible(false);
        cardP2.setVisible(false);
        cardP3.setVisible(false);
        cardP4.setVisible(false);
        cardP5.setVisible(false);
        cardP6.setVisible(false);
    }

    public void initialize() {
        String auxPlayerCredit = Double.toString(player.getCredit());
        StringProperty playerCredit = new SimpleStringProperty(auxPlayerCredit);

        credit.textProperty().bind(playerCredit);
    }

    public void refreshScore () {
        StringProperty pScore = new SimpleStringProperty(Integer.toString(game.getScore(player)));
        playerScore.textProperty().bind(pScore);

        StringProperty dScore = new SimpleStringProperty(Integer.toString(game.getScore(game.dealer)));
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
        Pane cardLayer;

        for (int i=0; i<player.getPlayerCards().size(); i++) {
            int card = player.getPlayerCards().get(i).getNumber();
            Card.Seed seed = player.getPlayerCards().get(i).getSeed();

            cardValue = conversion(card);

            if (i==0 && type==0) {
                cardLayer = cardD1;
                name = cardD1V;
                seedName = cardD1S;
            }
            else if (i==1 && type==0) {
                cardLayer = cardD2;
                name = cardD2V;
                seedName = cardD2S;
            }
            else if (i==0 && type==1) {
                cardLayer = cardP1;
                name = cardP1V;
                seedName = cardP1S;
            }
            else {
                cardLayer = cardP2;
                name = cardP2V;
                seedName = cardP2S;
            }

            cardLayer.setVisible(true);
            seedName.setImage(setSeed(seed));
            name.textProperty().bind(cardValue);
        }
    }

    public void dealerHitCards () {
        Label name;
        ImageView seedName;
        StringProperty cardValue;
        Pane cardLayer;

        for (int i=2; i<game.dealer.getPlayerCards().size(); i++) {
            int card = game.dealer.getPlayerCards().get(i).getNumber();
            Card.Seed seed = game.dealer.getPlayerCards().get(i).getSeed();

            cardValue = conversion(card);

            if (i==2) {
                cardLayer = cardD3;
                name = cardD3V;
                seedName = cardD3S;
            }
            else if (i==3) {
                cardLayer = cardD4;
                name = cardD4V;
                seedName = cardD4S;
            }
            else if (i==4) {
                cardLayer = cardD5;
                name = cardD5V;
                seedName = cardD5S;
            }
            else {
                cardLayer = cardD6;
                name = cardD6V;
                seedName = cardD6S;
            }

            cardLayer.setVisible(true);
            seedName.setImage(setSeed(seed));
            name.textProperty().bind(cardValue);
        }
    }

    public void newCard () {
        Label name;
        ImageView seedName;
        StringProperty cardValue;
        Pane cardLayer;

        int position = player.getPlayerCards().size();
        if (position == 3) {
            cardLayer = cardP3;
            name = cardP3V;
            seedName = cardP3S;
        }
        else if (position == 4) {
            cardLayer = cardP4;
            name = cardP4V;
            seedName = cardP4S;
        }
        else if (position == 5) {
            cardLayer = cardP5;
            name = cardP5V;
            seedName = cardP5S;
        }
        else {
            cardLayer = cardP6;
            name = cardP6V;
            seedName = cardP6S;
        }

        int card = player.getPlayerCards().get(position-1).getNumber();
        Card.Seed seed = player.getPlayerCards().get(position-1).getSeed();

        cardValue = conversion(card);

        cardLayer.setVisible(true);
        seedName.setImage(setSeed(seed));
        name.textProperty().bind(cardValue);
    }

    @FXML
    void bet5() {
        bet5.setDisable(true);
        bet10.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        betallin.setDisable(true);

        if (game.getCards().size() == 52) game.mixCards(10);
        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        game.betAndGetCards(player, 5);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
        initialize();
    }

    @FXML
    void bet10() {
        bet5.setDisable(true);
        bet10.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        betallin.setDisable(true);

        if (game.getCards().size() == 52) game.mixCards(10);
        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        game.betAndGetCards(player, 10);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
        initialize();
    }

    @FXML
    void bet100() {
        bet5.setDisable(true);
        bet10.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        betallin.setDisable(true);

        if (game.getCards().size() == 52) game.mixCards(10);
        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        game.betAndGetCards(player, 100);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
        initialize();
    }

    @FXML
    void bet50() {
        bet5.setDisable(true);
        bet10.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        betallin.setDisable(true);

        if (game.getCards().size() == 52) game.mixCards(10);
        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        game.betAndGetCards(player, 50);
        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
        initialize();
    }

    @FXML
    void betAll () {
        bet5.setDisable(true);
        bet10.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        betallin.setDisable(true);

        if (game.getCards().size() == 52) game.mixCards(10);
        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        double credit = player.getCredit();
        game.betAndGetCards(player, credit);

        initialize();

        initialCards(game.dealer, 0);
        initialCards(player, 1);

        refreshScore();

        int result = game.blackJackOrDraw(player);
        alert(result);
        initialize();
    }

    @FXML
    void hit() {
        int result = game.hit(player);

        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        newCard();
        refreshScore();

        alert(result);
        if (result!= -1) {
            hit.setDisable(true);
            stand.setDisable(true);
        }

        initialize();
    }

    @FXML
    void stand() {
        game.dealerScore();
        int result = game.stand(player);

        if (game.getCards().size() == 0) {
            game.setCards(game1.getCards());
            game.mixCards(10);
        }

        if (game.dealer.getPlayerCards().size() > 2) dealerHitCards();

        refreshScore();

        alert(result);
        if (result!= -1) {
            hit.setDisable(true);
            stand.setDisable(true);
        }
        initialize();
    }

}
