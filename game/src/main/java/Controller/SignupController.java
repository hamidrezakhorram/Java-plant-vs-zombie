package Controller;

import Model.Database;
import Model.Player;
import View.DayLevelMenuPage;
import View.SignupPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignupController {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeadPasswordField;

    @FXML
    private TextField usernamwField;

    @FXML
    void SingupAction(MouseEvent event) throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setEmail(emailField.getText());
        newPlayer.setName(nameField.getText());
        newPlayer.setPassword(passwordField.getText());
        newPlayer.setUsername(usernamwField.getText());
        Database.getInstance().addNewPlayer(newPlayer);
        LoginController.setCurrentPlayer(newPlayer);
        openLevelMenu();
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

}
