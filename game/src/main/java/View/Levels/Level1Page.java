package View.Levels;

import Controller.DayMapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Level1Page extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DayMapController.setZombieTotalNumber(10);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/daymap_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Level 1");
        DayMapController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
