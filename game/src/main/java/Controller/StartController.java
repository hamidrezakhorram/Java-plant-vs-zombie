package Controller;

import View.LoginPage;
import View.SignupPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    void exitAction(MouseEvent event) {
      Platform.exit();
    }

    @FXML
    void openLoginPage(MouseEvent event) {
        LoginPage loginPage = new LoginPage();
        try {
            loginPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void openSignupPage(MouseEvent event) {
        SignupPage signupPage = new SignupPage();
        try {
            signupPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
