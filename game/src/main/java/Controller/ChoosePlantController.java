package Controller;

import Model.Database;
import Model.plants.Plant;
import View.ChoosePlantPage;
import View.DayMap;
import View.Levels.*;
import View.LosePage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private HBox availablePlantsHbox;

    @FXML
    private HBox availablePlantsHbox2;

    @FXML
    private HBox choosenPlantsHbox;
    @FXML
    private ImageView chosenImage;

    @FXML
    private AnchorPane mainAncherPain;

    @FXML
    private Button submitButten;
    @FXML
    private ImageView availabelImage;

  private   ArrayList<Plant> availablePlantList = new ArrayList<>();
  private   ArrayList<Plant> choosenPlantList = new ArrayList<>();
  private   ArrayList<Plant> allPlants  = new ArrayList<>();

    @FXML
    void submitAction(MouseEvent event) {
        for (Node plantNode: choosenPlantsHbox.getChildren()) {
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
        }else if (ChoosePlantController.levelNumber == 2) {
            openLevel2();
        }else if (ChoosePlantController.levelNumber == 3) {
            openLevel3();
        }else if (ChoosePlantController.levelNumber == 4) {
            openLevel4();
        }else if (ChoosePlantController.levelNumber == 5) {
            openLevel5();
        }else if (ChoosePlantController.levelNumber == 6) {
            openLevel6();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> nightPlantList = new ArrayList<>();
        nightPlantList.add("doomshroom");
        nightPlantList.add("iceshroom");
        nightPlantList.add("sunShroom");
        nightPlantList.add("scaredyShroom");
        nightPlantList.add("fumeShroom");
        nightPlantList.add("puffShroom");

        allPlants.add(PlantInitialize.sunflower());
        allPlants.add(PlantInitialize.peashooter());
        allPlants.add(PlantInitialize.cherryBomb());
        allPlants.add(PlantInitialize.repeater());
        allPlants.add(PlantInitialize.snowpea());
        allPlants.add(PlantInitialize.wallNut());
        allPlants.add(PlantInitialize.doomShroom());
        allPlants.add(PlantInitialize.puffShroom());
        allPlants.add(PlantInitialize.sunShroom());
        allPlants.add(PlantInitialize.fumeShroom());
        allPlants.add(PlantInitialize.iceShroom());
        allPlants.add(PlantInitialize.scaredyShroom());
        if (ChoosePlantController.levelNumber == 1 || ChoosePlantController.levelNumber == 2 || ChoosePlantController.levelNumber == 3) {
            for (String nightPlantName : nightPlantList) {
                allPlants.removeIf(plant -> nightPlantName.equals(plant.getName()));
            }
        }
        for (Plant plant: allPlants) {
            try {
                for (String plantName : Database.getInstance().getPlantList(LoginController.getCurrentPlayer().getId())) {
                    if (plantName.equals(plant.getName())) {
                        availablePlantList.add(plant);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        for (Plant plant : availablePlantList) {
            ImageView plantImageView = new ImageView();
            plantImageView.setImage(new Image(getClass().getResource(plant.getGifUrl()).toExternalForm()));
            plantImageView.setOnMouseClicked(event -> {
                if (availablePlantsHbox.getChildren().contains(plantImageView) && choosenPlantsHbox.getChildren().size() <= 5) {
                    choosenPlantsHbox.getChildren().add(plantImageView);
                    availablePlantsHbox.getChildren().remove(plantImageView);
                }else {
                    availablePlantsHbox.getChildren().add(plantImageView);
                    choosenPlantsHbox.getChildren().remove(plantImageView);
                }

            });
            availablePlantsHbox.getChildren().add(plantImageView);
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

    private void openLevel2(){
        Level2Page level2Page = new Level2Page();
        try {
            level2Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openLevel3(){
        Level3Page level3Page = new Level3Page();
        try {
            level3Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openLevel4()  {
        Level4Page level4Page = new Level4Page();
        try {
            level4Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openLevel5()  {
        Level5Page level5Page = new Level5Page();
        try {
            level5Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openLevel6()  {
        Level6Page level6Page = new Level6Page();
        try {
            level6Page.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
