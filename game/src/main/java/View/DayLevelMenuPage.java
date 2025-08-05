package View;

import Controller.DayLevelMenuController;
import Controller.DayMapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class DayLevelMenuPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/dayLevelMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Day Level Menu");
        DayLevelMenuController.setCurrentStage(stage);
        stage.setScene(scene);
//        stage.setFullScreenExitHint("");
//        stage.setFullScreen(true);
        stage.show();
    }
}
