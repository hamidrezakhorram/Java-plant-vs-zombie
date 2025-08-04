package Controller;

import View.*;
import View.Levels.Level1Page;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DayLevelMenuController {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    void ChooseLevel1(MouseEvent event) {
        ChoosePlantController.setLevelNumber(1);
        choosePlantPage();

    }

    @FXML
    void ChooseLevel2(MouseEvent event) {
       ChoosePlantController.setLevelNumber(2);
       choosePlantPage();
    }

    @FXML
    void ChooseLevel3(MouseEvent event) {
      ChoosePlantController.setLevelNumber(3);
      choosePlantPage();
    }

    @FXML
    void chooseNighLevels(MouseEvent event) {
        NightLevelMenuPage nightLevelMenuPage = new NightLevelMenuPage();
        try {
            nightLevelMenuPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void choosePlantPage() {
        ChoosePlantPage choosePlantPage = new ChoosePlantPage();
        try {
            choosePlantPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void openScorepage(MouseEvent event) {
       ScorePage scorePage = new ScorePage();
        try {
            scorePage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void openSettingpage(MouseEvent event) {
        SettingPage settingPage = new SettingPage();
        try {
            settingPage.start(currentStage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
