package Controller;

import Model.plants.Plant;
import View.ChoosePlantPage;
import View.DayMap;
import View.Levels.Level1Page;
import View.LosePage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChoosePlantController implements Initializable {
    private static int levelNumber =1;

    public static int getLevelNumber() {
        return levelNumber;
    }

    public static void setLevelNumber(int levelNumber) {
        ChoosePlantController.levelNumber = levelNumber;
    }

    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    private VBox availablePlantsVbox;

    @FXML
    private VBox choosenPlantsVbox;

    ArrayList<Plant> availablePlantList = new ArrayList<>();
    ArrayList<Plant> choosenPlantList = new ArrayList<>();
    @FXML
    void submitAction(MouseEvent event) {
        for (Node plantNode: choosenPlantsVbox.getChildren()) {
          ImageView  plantImageView = (ImageView) plantNode ;

            for (Plant plant: availablePlantList) {
                String imageUrl = plantImageView.getImage().getUrl();
                String plantGifUrl = getClass().getResource(plant.getGifUrl()).toExternalForm();
               if (imageUrl.equals(plantGifUrl)) {
                    choosenPlantList.add(plant);
                }
            }

        }
        DayMapController.setPlantList(choosenPlantList);
        if (ChoosePlantController.levelNumber == 1) {
            openLevel1();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availablePlantList.add(PlantInitialize.sunflower());
        availablePlantList.add(PlantInitialize.peashooter());
        availablePlantList.add(PlantInitialize.cherryBomb());
        availablePlantList.add(PlantInitialize.repeater());
        availablePlantList.add(PlantInitialize.snowpea());
        availablePlantList.add(PlantInitialize.wallNut());

        for (Plant plant : availablePlantList) {
            ImageView plantImageView = new ImageView();
            plantImageView.setImage(new Image(getClass().getResource(plant.getGifUrl()).toExternalForm()));
            plantImageView.setOnMouseClicked(event -> {
                if (availablePlantsVbox.getChildren().contains(plantImageView)) {
                    choosenPlantsVbox.getChildren().add(plantImageView);
                    availablePlantsVbox.getChildren().remove(plantImageView);
                }else {
                    availablePlantsVbox.getChildren().add(plantImageView);
                    choosenPlantsVbox.getChildren().remove(plantImageView);
                }

            });
            availablePlantsVbox.getChildren().add(plantImageView);
        }



    }

    private void openLevel1(){
        Level1Page level1Page = new Level1Page();
        try {
            level1Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
