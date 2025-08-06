package View;

import Controller.DayMapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DayMap  extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/daymap_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 500);
        stage.setTitle("DAY MAP");
        DayMapController.setCurrentStage(stage);

        stage.setScene(scene);
//        stage.setFullScreenExitHint("");
//        stage.setFullScreen(true);
        stage.show();
    }
}
