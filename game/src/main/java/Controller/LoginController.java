package Controller;

import Model.Player;
import View.DayLevelMenuPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    private static Player currentPlayer;

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

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
    void LoginAction(MouseEvent event) {

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

    public static void openLevelMenu(){
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }

}

