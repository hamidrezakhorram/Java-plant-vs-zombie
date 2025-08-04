package View;

import Controller.SettingController;
import Controller.StartController;
import Model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SettingPage extends Application {

    public void start(Stage stage) throws IOException, SQLException {
        Database database = Database.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/setting.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Setting");
        stage.setScene(scene);
//        stage.setFullScreen(true);
        SettingController.setCurrentStage(stage);
        stage.show();
    }
}
