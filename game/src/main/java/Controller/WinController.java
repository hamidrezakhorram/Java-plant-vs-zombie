
package Controller;

import View.DayLevelMenuPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WinController {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    void openMenuPage(MouseEvent event) {
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

