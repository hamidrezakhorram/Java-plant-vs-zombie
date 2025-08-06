package View;

import Controller.StartController;
import Model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StartPage extends Application {


    public void start(Stage stage) throws IOException, SQLException {
      Database database = Database.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Plant vs Zombies");
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        StartController.setCurrentStage(stage);
        stage.show();
    }

}
