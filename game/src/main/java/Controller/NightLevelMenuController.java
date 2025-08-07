package Controller;

import Model.Database;
import View.ChoosePlantPage;
import View.DayLevelMenuPage;
import View.Levels.Level4Page;
import View.NightLevelMenuPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    void openLevel4(MouseEvent event) throws SQLException {
          ChoosePlantController.setLevelNumber(4);
          choosePlantPage();

    }

    @FXML
    void openLevel5(MouseEvent event) throws SQLException {
        ChoosePlantController.setLevelNumber(5);
        choosePlantPage();
    }

    @FXML
    void openLevel6(MouseEvent event) throws SQLException {
        ChoosePlantController.setLevelNumber(6);
        choosePlantPage();
    }

    private void choosePlantPage() throws SQLException {

        boolean isUnlocked = false;
        for (Integer levelNumber : DatabaseController.getLevelList(LoginController.getCurrentPlayer().getId())){
            if (levelNumber == ChoosePlantController.getLevelNumber()-1){
                isUnlocked = true;
            }
        }
        if (isUnlocked){
            ChoosePlantPage choosePlantPage = new ChoosePlantPage();
            try {
                choosePlantPage.start(currentStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            DayMapController.showDialog("Warning" ,"You have not Unlock this level!");
        }
    }
    @FXML
    void backAction(MouseEvent event) {
        StartPage startPage = new StartPage();
        try {
            startPage.start(currentStage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
