package sample;
import java.util.TreeSet;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    private Game game = new Game();
    private Player player = new Player(50.000);

    @FXML
    private void deal () {
        //inserimento delle opzioni dello start

        game.betAndGetCards(player, 5);
    }

}
