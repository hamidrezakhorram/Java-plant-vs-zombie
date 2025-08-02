package Controller;

import View.ChoosePlantPage;
import View.DayLevelMenuPage;
import View.Levels.Level4Page;
import View.NightLevelMenuPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NightLevelMenuController {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    void openDaylevel(MouseEvent event) {
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void openLevel4(MouseEvent event) {
          ChoosePlantController.setLevelNumber(4);
          choosePlantPage();

    }

    @FXML
    void openLevel5(MouseEvent event) {

    }

    @FXML
    void openLevel6(MouseEvent event) {

    }

    private void choosePlantPage() {
        ChoosePlantPage choosePlantPage = new ChoosePlantPage();
        try {
            choosePlantPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
