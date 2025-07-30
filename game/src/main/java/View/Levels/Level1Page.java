package View.Levels;

import Controller.DayMapController;
import Controller.InitializeZombies;
import Model.zmobies.Zombie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Level1Page extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DayMapController.setZombieTotalNumber(10);
        ArrayList<Zombie> zombieList = new ArrayList<>();
        zombieList.add(InitializeZombies.simpleZombie());
        zombieList.add(InitializeZombies.coneheadZombie());
        zombieList.add(InitializeZombies.flagZombie());
        DayMapController.setZombieList(zombieList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/daymap_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Level 1");
        DayMapController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
