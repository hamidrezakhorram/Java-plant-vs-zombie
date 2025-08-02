package View;

import Controller.DayLevelMenuController;
import Controller.NightLevelMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NightLevelMenuPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/nightlevelmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Night Level Menu");
        NightLevelMenuController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
