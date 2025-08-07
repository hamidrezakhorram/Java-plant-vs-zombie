package Controller;

import Model.Database;
import View.*;
import View.Levels.Level1Page;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DayLevelMenuController implements Initializable {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private ImageView backgroundImage;

    @FXML
    private AnchorPane mainAncherPain;
    @FXML
    void ChooseLevel1(MouseEvent event) throws SQLException {
        ChoosePlantController.setLevelNumber(1);
        choosePlantPage();

    }

    @FXML
    void ChooseLevel2(MouseEvent event) throws SQLException {
       ChoosePlantController.setLevelNumber(2);
       choosePlantPage();
    }

    @FXML
    void ChooseLevel3(MouseEvent event) throws SQLException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        backgroundImage.fitWidthProperty().bind(mainAncherPain.widthProperty());
//        backgroundImage.fitHeightProperty().bind(mainAncherPain.heightProperty());
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
