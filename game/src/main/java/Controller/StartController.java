package Controller;

import View.LoginPage;
import View.SignupPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private ImageView backgroundImage;
    @FXML
    private AnchorPane mainAncherPain;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundImage.fitWidthProperty().bind(mainAncherPain.widthProperty());
        backgroundImage.fitHeightProperty().bind(mainAncherPain.heightProperty());
    }
}
