package Controller;

import Model.plants.Plant;
import Model.zmobies.Zombie;
import View.DayLevelMenuPage;
import View.LosePage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DayMapController implements Initializable {
    private static int zombieTotalNumber=1;
    private static ArrayList<Zombie> zombieList = new ArrayList<>();

    public static ArrayList<Zombie> getZombieList() {
        return zombieList;
    }

    public static void setZombieList(ArrayList<Zombie> zombieList) {
        DayMapController.zombieList = zombieList;
    }

    public static int getZombieTotalNumber() {
        return zombieTotalNumber;
    }

    public static void setZombieTotalNumber(int zombieTotalNumber) {
        DayMapController.zombieTotalNumber = zombieTotalNumber;
    }

    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    ArrayList<Zombie> row1ZombieList = new ArrayList<>();
    ArrayList<Zombie> row2ZombieList = new ArrayList<>();
    ArrayList<Zombie> row3ZombieList = new ArrayList<>();
    ArrayList<Zombie> row4ZombieList = new ArrayList<>();
    ArrayList<Zombie> row5ZombieList = new ArrayList<>();
    ArrayList<Plant>  row1PlantList = new ArrayList<>();
    ArrayList<Plant>  row2PlantList = new ArrayList<>();
    ArrayList<Plant>  row3PlantList = new ArrayList<>();
    ArrayList<Plant>  row4PlantList = new ArrayList<>();
    ArrayList<Plant>  row5PlantList = new ArrayList<>();
    private Map<Zombie, Timeline> zombieTimelineList = new HashMap<>();
    private int sunAmount = 500;
    @FXML
    private Label sunAmountlabel;
    @FXML
    private ImageView mapImage;
    @FXML
    private AnchorPane mainAncharPain;
    private static Plant currentPlant;
    @FXML
    private ImageView plant1;

    @FXML
    private ImageView sunflower;
    @FXML
    private ImageView sunImage;
    @FXML
    private GridPane mapGridPane;
    @FXML
    private AnchorPane zombieRow1;

    @FXML
    private AnchorPane zombieRow2;

    @FXML
    private AnchorPane zombieRow3;

    @FXML
    private AnchorPane zombieRow4;

    @FXML
    private AnchorPane zombieRow5;
    @FXML
    private StackPane mapStackPain;
    @FXML
    private AnchorPane stackpainAncherpain;

    @FXML
    void setPlant(MouseEvent event) {
        String currentPlantName = currentPlant.getName();
        Node clickedNode = (Node) event.getSource();
        for (Node node : mapGridPane.getChildren()) {

            Integer colIndex = GridPane.getColumnIndex(clickedNode);

            if (colIndex == null) {
                colIndex = 0;
            }
            if (colIndex ==0){
                row1PlantList.add(currentPlant);
            }else if (colIndex ==1){
                row2PlantList.add(currentPlant);
            }else if (colIndex ==2){
                row3PlantList.add(currentPlant);
            }
            else if (colIndex ==3){
                row4PlantList.add(currentPlant);
            }
            else if (colIndex ==4){
                row5PlantList.add(currentPlant);
            }



            if (node.equals(clickedNode)) {
                if (sunAmount >= currentPlant.getBuildCost()) {
                    sunAmount = sunAmount - currentPlant.getBuildCost();
                    sunAmountlabel.setText(Integer.toString(sunAmount));
                } else {
                    showNotEnoughSunAlert();
                    return;

                }
                ImageView cell = (ImageView) node;
                if (cell.getImage() != null) {
                    showLimitAlert();
                    return;
                }
                cell.setImage(new Image(getClass().getResource(currentPlant.getGifUrl()).toExternalForm()));
                if (currentPlant.getName().equals("SunFlower")) {
                    new Thread(() -> {

                        while (true) {
                            Random rand = new Random();
                            int randNum = rand.nextInt(1000,5000);
                            try {
                                Thread.sleep(10000 + randNum);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            porduceSun("Sunflower" , cell);

                        }


                    }).start();
                }
                new Thread(() -> {
                    final ImageView[] bullet = new ImageView[1];
                    Integer rowIndex = GridPane.getRowIndex((Node) cell);
                    if (rowIndex == null) {
                        rowIndex = 0;
                    }
                    while (rowIndex == 0 && !zombieRow1.getChildren().isEmpty() ) {
                        Platform.runLater(() -> {
                            zombieEatPlant(cell, zombieRow1, row1ZombieList.getFirst(),currentPlant);
                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();
                        });
                        if (currentPlantName.equals("plant1")){
                        Platform.runLater(() -> {
                            greenBulletAction(cell, zombieRow1, row1ZombieList.getFirst());
                        });}


                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 1 && !zombieRow2.getChildren().isEmpty() ) {
                        Platform.runLater(() -> {
                            zombieEatPlant(cell, zombieRow2, row2ZombieList.getFirst(),currentPlant);
                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();
                        });
                        if (currentPlantName.equals("plant1")){
                            Platform.runLater(() -> {
                                greenBulletAction(cell, zombieRow2, row2ZombieList.getFirst());
                            });
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    while (rowIndex == 2 && !zombieRow3.getChildren().isEmpty() ) {
                        Platform.runLater(() -> {
                            zombieEatPlant(cell, zombieRow3, row3ZombieList.getFirst(),currentPlant);
                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();
                        });
                        if (currentPlantName.equals("plant1")){
                            Platform.runLater(() -> {
                                greenBulletAction(cell, zombieRow3, row3ZombieList.getFirst());
                            });
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 3 && !zombieRow4.getChildren().isEmpty() ) {
                        Platform.runLater(() -> {
                            zombieEatPlant(cell, zombieRow4, row4ZombieList.getFirst(),currentPlant);
                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();
                        });
                        if (currentPlantName.equals("plant1")){
                            Platform.runLater(() -> {
                                greenBulletAction(cell, zombieRow4, row4ZombieList.getFirst());
                            });
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 4 && !zombieRow5.getChildren().isEmpty() && currentPlantName.equals("plant1")) {
                        Platform.runLater(() -> {
                            zombieEatPlant(cell, zombieRow5, row5ZombieList.getFirst(),currentPlant);
                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();
                        });
                        Platform.runLater(() -> {
                            greenBulletAction(cell, zombieRow5, row5ZombieList.getFirst());
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }


                }).start();
            }


        }


    }

    @FXML
    void choosePlane1(MouseEvent event) {
        Plant plant1 = new Plant();
        plant1.setName("plant1");
        plant1.setBuildCost(100);
        plant1.setGifUrl("/assesst/plant1.gif");
        plant1.setHealth(100);
        currentPlant = plant1;
    }

    @FXML
    void choosePlant2(MouseEvent event) {
        Plant SunFlower = new Plant();
        SunFlower.setName("SunFlower");
        SunFlower.setGifUrl("/assesst/sunflower.gif");
        SunFlower.setBuildCost(50);
        SunFlower.setHealth(30);
        currentPlant = SunFlower;
    }

    @FXML
    void test(MouseEvent event) {
    }


    private void showLimitAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You already have a plant in this area");
        alert.showAndWait();
    }

    private void showNotEnoughSunAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You have not enough sun for this plant");
        alert.showAndWait();
    }

    private Zombie simpleZombie() {
        Zombie zombie = new Zombie();
        zombie.setName("zombie");
        zombie.setGifUrl("/assesst/SimpleZombie.gif");
        zombie.setHealth(50);
        return zombie;
    }

    private Zombie ConeheadZombie() {
        Zombie zombie = new Zombie();
        zombie.setName("zombie");
        zombie.setGifUrl("/assesst/ConeheadZombie.gif");
        zombie.setHealth(50);
        return zombie;
    }

    private ImageView greenBulletAction(ImageView plant, AnchorPane zombieRow, Zombie detectedZombie) {
        ImageView zombieImageView = (ImageView) zombieRow.getChildren().getFirst();
        ImageView greenbullet = new ImageView(new Image(getClass().getResource("/assesst/greenBullet.png").toExternalForm()));
        greenbullet.setFitHeight(30);
        greenbullet.setFitWidth(30);
        greenbullet.setX(plant.getLayoutX() + 50);

        greenbullet.setY(plant.getLayoutY());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(greenbullet.translateXProperty(), mainAncharPain.getWidth(), Interpolator.EASE_IN))
        );
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Bounds greenBulletBounds = greenbullet.localToScene(greenbullet.getBoundsInLocal());
                Bounds zombieBounds = zombieImageView.localToScene(zombieImageView.getBoundsInLocal());
                if (greenBulletBounds.intersects(zombieBounds)) {
                    this.stop();
                    timeline.stop();
                    mainAncharPain.getChildren().remove(greenbullet);
                    System.out.println(detectedZombie.getName() + " " + detectedZombie.getHealth());
                    if (detectedZombie.getHealth() > 0) {
                        detectedZombie.setHealth(detectedZombie.getHealth() - 10);
                    } else {
                        zombieRow.getChildren().remove(zombieImageView);
                    }

                }


            }
        };
        animationTimer.start();
        timeline.play();
        mainAncharPain.getChildren().add(greenbullet);
        return greenbullet;
    }

    private void zombieEatPlant(ImageView plant, AnchorPane zombieRow, Zombie detectedZombie , Plant detectedPlant) {
        ImageView zombieImageView = (ImageView) zombieRow.getChildren().getFirst();
        Bounds plantBounds = plant.localToScene(plant.getBoundsInLocal());
        Bounds zombieBounds = zombieImageView.localToScene(zombieImageView.getBoundsInLocal());
        Timeline zombieTimeline = zombieTimelineList.get(detectedZombie);

       if (plantBounds.intersects(zombieBounds) & detectedPlant.getHealth()>0) {
           System.out.println("zombieEatPlant");
           detectedPlant.setHealth(detectedPlant.getHealth() - 10);
            zombieTimeline.pause();
        }else{
           zombieTimeline.play();
       }
        if (detectedPlant.getHealth() <=0){
            mapGridPane.getChildren().remove(plant);
        }


    }

    private void porduceSun(String type , ImageView plantImageView) {
        ImageView flowerClone = new ImageView();
        flowerClone.setImage(sunImage.getImage());
        flowerClone.setFitWidth(sunImage.getFitWidth());
        flowerClone.setFitHeight(sunImage.getFitHeight());
        flowerClone.setX(sunImage.getX());
        flowerClone.setY(sunImage.getY());
        flowerClone.setVisible(sunImage.isVisible());
        flowerClone.setOnMouseClicked(mouseEvent -> {
            sunAmount+=50;
            sunAmountlabel.setText(String.valueOf(sunAmount));
            stackpainAncherpain.getChildren().remove(flowerClone);
        });
        if (type.equals("Sunflower")) {
            Platform.runLater(() -> {
                stackpainAncherpain.getChildren().add(flowerClone);
                flowerClone.setLayoutX(plantImageView.getLayoutX() + 10);
                flowerClone.setLayoutY(plantImageView.getLayoutY() + 10);
                flowerClone.setVisible(true);
            });
        } else {

            Random random = new Random();
            int randomPositionX = random.nextInt(1, 400);
            int randomPositionY = random.nextInt(1, 300);

            Platform.runLater(() -> {
                stackpainAncherpain.getChildren().add(flowerClone);
                flowerClone.setLayoutX(randomPositionX);
                flowerClone.setLayoutY(randomPositionY);
                flowerClone.setVisible(true);
            });

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sunAmountlabel.setText(Integer.toString(sunAmount));
        ArrayList<Zombie> zombieList = DayMapController.zombieList;
        ArrayList<AnchorPane> zombieRowList = new ArrayList<>();
        zombieRowList.add(zombieRow1);
        zombieRowList.add(zombieRow2);
        zombieRowList.add(zombieRow3);
        zombieRowList.add(zombieRow4);
        zombieRowList.add(zombieRow5);
        zombieRow1.setMouseTransparent(true);
        zombieRow2.setMouseTransparent(true);
        zombieRow3.setMouseTransparent(true);
        zombieRow4.setMouseTransparent(true);
        zombieRow5.setMouseTransparent(true);
        int zombieNumber =DayMapController.zombieTotalNumber ;
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                porduceSun("field" , new ImageView());

            }


        }).start();
        for (int i = 0; i < zombieNumber; i++) {
            new Thread(() -> {
                Random random = new Random();
                int randomZombie = random.nextInt(3);
                int randomPlacer = random.nextInt(5);
                int speedRandom = random.nextInt(10);
                ImageView zombieImageView = new ImageView();
                zombieImageView.setFitWidth(100);
                zombieImageView.setFitHeight(100);
                zombieImageView.setLayoutX(600);
                zombieImageView.setLayoutY(0);

               Zombie zombie = zombieList.get(randomZombie);

//                try {
//                    zombie = zombieList.get(randomZombie).clone();
//                } catch (CloneNotSupportedException e) {
//                    throw new RuntimeException(e);
//                }

                if (randomPlacer == 0) {
                    row1ZombieList.add(zombie);
                } else if (randomPlacer == 1) {
                    row2ZombieList.add(zombie);
                } else if (randomPlacer == 2) {
                    row3ZombieList.add(zombie);
                } else if (randomPlacer == 3) {
                    row4ZombieList.add(zombie);
                } else {
                    row5ZombieList.add(zombie);
                }
                zombieImageView.setImage(new Image(getClass().getResource(zombie.getGifUrl()).toExternalForm()));
                Platform.runLater(() -> {
                    zombieRowList.get(randomPlacer).getChildren().add(zombieImageView);
                    zombieImageView.setImage(new Image(getClass().getResource(zombie.getGifUrl()).toExternalForm()));
                    Timeline timeline = new Timeline(

                            new KeyFrame(Duration.seconds(30 + speedRandom),
                                    new KeyValue(zombieImageView.layoutXProperty(), -(zombieRowList.get(randomPlacer).getWidth() - 550), Interpolator.LINEAR))
                    );
                    zombieTimelineList.put(zombie, timeline);
                    timeline.play();

                    timeline.setOnFinished(event -> {
                        if (zombieRowList.get(randomPlacer).getChildren().contains(zombieImageView)) {
                            openLosePage();
                        }
                        zombieRowList.get(randomPlacer).getChildren().remove(zombieImageView);

                    });
                });
            }).start();
        }
    }

    private void openLosePage() {
        LosePage losePage = new LosePage();
        try {
            losePage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
