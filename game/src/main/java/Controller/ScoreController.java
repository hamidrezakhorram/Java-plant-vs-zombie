package Controller;

import Model.Database;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private VBox scoreTableVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Player> playerList =null;
        try {
            playerList = Database.getInstance().getPlayerList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(playerList, (p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        for (Player player : playerList) {
            Label playerInfo= new Label( player.getId()+ " " +player.getUsername() + " " + player.getScore() +
                    player.getWin() + " " + player.getLoss());
            scoreTableVbox.getChildren().add(playerInfo);
        }
    }
}
