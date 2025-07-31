package Controller;

import View.ChoosePlantPage;
import View.Levels.Level1Page;
import View.LosePage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        ChoosePlantPage choosePlantPage = new ChoosePlantPage();
        try {
            choosePlantPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//            Level1Page level1Page = new Level1Page();
//            try {
//                level1Page.start(currentStage);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
    }

    @FXML
    void ChooseLevel2(MouseEvent event) {

    }

    @FXML
    void ChooseLevel3(MouseEvent event) {

    }

    @FXML
    void chooseNighLevels(MouseEvent event) {

    }

}
