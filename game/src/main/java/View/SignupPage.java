package View;

import Controller.SignupController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 500);
        stage.setTitle("Signup");
        stage.setScene(scene);
        SignupController.setCurrentStage(stage);
        stage.show();
    }
}
