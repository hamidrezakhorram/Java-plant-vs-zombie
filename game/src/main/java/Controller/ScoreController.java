package Controller;

import Model.Database;
import Model.Player;
import View.DayLevelMenuPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
            HBox row = new HBox();

            row.getChildren().add(new Label(player.getId() + ""));
            row.getChildren().add(new Label(player.getUsername()));
            row.getChildren().add(new Label(player.getScore() + ""));
            row.getChildren().add(new Label(player.getWin() + ""));
            row.getChildren().add(new Label(player.getLoss() + ""));
            row.setSpacing(50.0);
            for (Node node : row.getChildren()) {
                Label label = (Label) node;
                label.setStyle("-fx-font-weight: bold Italic ; -fx-text-fill: #ff7903 ;-fx-font-size: 19px");

            }
            scoreTableVbox.getChildren().add(row);
        }
    }
    @FXML
    void backAction(MouseEvent event) {
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
