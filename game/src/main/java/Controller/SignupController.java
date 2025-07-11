package Controller;

import View.SignupPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
    void SingupAction(MouseEvent event) {

    }
    @FXML
    void backAction(MouseEvent event) {
        StartPage startPage = new StartPage();
        try {
            startPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
