package View;

import Controller.ChoosePlantController;
import Controller.DayLevelMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoosePlantPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/chooseplant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Choose Plant");
        ChoosePlantController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
