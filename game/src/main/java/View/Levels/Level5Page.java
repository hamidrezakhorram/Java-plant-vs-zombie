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

public class Level5Page extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DayMapController.setZombieTotalNumber(4);
        DayMapController.setZombieWaveNumber(6);
        ArrayList<Zombie> zombieList = new ArrayList<>();
        zombieList.add(InitializeZombies.simpleZombie());
        zombieList.add(InitializeZombies.coneheadZombie());
        zombieList.add(InitializeZombies.screenDoorZombie());
        DayMapController.setZombieList(zombieList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/nightmap_2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 500);
        stage.setTitle("Level 5");
        DayMapController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
