package Controller;

import Model.Database;
import Model.Player;
import View.DayLevelMenuPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static Player currentPlayer;

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    @FXML
    private ImageView backgroundImage;
    @FXML
    private AnchorPane mainAncherPain;
    @FXML
    private Label erroLable;
    public static void setCurrentPlayer(Player currentPlayer) {
        LoginController.currentPlayer = currentPlayer;
    }

    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernamwField;

    @FXML
    void LoginAction(MouseEvent event) throws SQLException {
        ArrayList<Player> playerList =Database.getInstance().getPlayerList();
        for (Player player : playerList) {
            if (player.getUsername().equals(usernamwField.getText()) && player.getPassword().equals(passwordField.getText())) {
                currentPlayer = player;
                openLevelMenu();
                break;
            }
        }
        erroLable.setVisible(true);
    }

    @FXML
    void backAction(MouseEvent event) {
        StartPage startPage = new StartPage();
        try {
            startPage.start(currentStage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private  void openLevelMenu(){
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundImage.fitWidthProperty().bind(mainAncherPain.widthProperty());
        backgroundImage.fitHeightProperty().bind(mainAncherPain.heightProperty());
    }
}

