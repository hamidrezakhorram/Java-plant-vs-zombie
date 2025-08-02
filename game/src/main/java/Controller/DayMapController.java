package Controller;

import Model.plants.Plant;
import Model.zmobies.SpecialZombie;
import Model.zmobies.StrongZombie;
import Model.zmobies.Zombie;
import View.LosePage;
import View.WinPage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DayMapController implements Initializable {
    private static int zombieTotalNumber = 1;
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

    private static ArrayList<Plant> plantList = new ArrayList<>();

    public static ArrayList<Plant> getPlantList() {
        return plantList;
    }

    public static void setPlantList(ArrayList<Plant> plantList) {
        DayMapController.plantList = plantList;
    }

    private static int zombieWaveNumber = 1;

    public static int getZombieWaveNumber() {
        return zombieWaveNumber;
    }

    public static void setZombieWaveNumber(int zombieWaveNumber) {
        DayMapController.zombieWaveNumber = zombieWaveNumber;
    }

    ArrayList<Zombie> row1ZombieList = new ArrayList<>();
    ArrayList<Zombie> row2ZombieList = new ArrayList<>();
    ArrayList<Zombie> row3ZombieList = new ArrayList<>();
    ArrayList<Zombie> row4ZombieList = new ArrayList<>();
    ArrayList<Zombie> row5ZombieList = new ArrayList<>();
    ArrayList<Plant> row1PlantList = new ArrayList<>();
    ArrayList<Plant> row2PlantList = new ArrayList<>();
    ArrayList<Plant> row3PlantList = new ArrayList<>();
    ArrayList<Plant> row4PlantList = new ArrayList<>();
    ArrayList<Plant> row5PlantList = new ArrayList<>();
    private Map<Zombie, Timeline> zombieTimelineList = new HashMap<>();
    private int sunAmount = 1000;
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
    private HBox plantsBar;
    @FXML
    private Slider zombieProgressBar;

    @FXML
    void setPlant(MouseEvent event) {
        String currentPlantName = currentPlant.getName();
        Node clickedNode = (Node) event.getSource();
        for (Node node : mapGridPane.getChildren()) {

            Integer colIndex = GridPane.getColumnIndex(clickedNode);

            if (colIndex == null) {
                colIndex = 0;
            }
            if (colIndex == 0) {
                row1PlantList.add(currentPlant);
            } else if (colIndex == 1) {
                row2PlantList.add(currentPlant);
            } else if (colIndex == 2) {
                row3PlantList.add(currentPlant);
            } else if (colIndex == 3) {
                row4PlantList.add(currentPlant);
            } else if (colIndex == 4) {
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
                if (currentPlant.getName().equals("sunflower")) {
                    new Thread(() -> {

                        while (true) {
                            Random rand = new Random();
                            int randNum = rand.nextInt(1000, 2000);
                            try {
                                Thread.sleep(10000 + randNum);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            porduceSun("sunflower", cell);

                        }


                    }).start();
                }
                new Thread(() -> {
                    final ImageView[] bullet = new ImageView[1];
                    Integer rowIndex = GridPane.getRowIndex((Node) cell);
                    if (rowIndex == null) {
                        rowIndex = 0;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Plant plant = null;
                    try {
                        plant = currentPlant.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    while (true) {
                        if (rowIndex == 0 && !zombieRow1.getChildren().isEmpty()) {
                            try {
                                rowAction(0, zombieRow1, cell, row1ZombieList, plant);
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (rowIndex == 1 && !zombieRow2.getChildren().isEmpty()) {
                            try {
                                rowAction(1, zombieRow2, cell, row2ZombieList, plant);
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (rowIndex == 2 && !zombieRow3.getChildren().isEmpty()) {
                            try {
                                rowAction(2, zombieRow3, cell, row3ZombieList, plant);
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (rowIndex == 3 && !zombieRow4.getChildren().isEmpty()) {
                            try {
                                rowAction(3, zombieRow4, cell, row4ZombieList, plant);
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (rowIndex == 4 && !zombieRow5.getChildren().isEmpty()) {
                            try {
                                rowAction(4, zombieRow5, cell, row5ZombieList, plant);
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }).start();
            }


        }


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

    private ImageView greenBulletAction(ImageView plant, AnchorPane zombieRow, Zombie detectedZombie, String bulletType) {

        ImageView zombieImageView = (ImageView) zombieRow.getChildren().getFirst();
        ImageView greenbullet = new ImageView();
        if (bulletType.equals("snowy")) {
            greenbullet.setImage(new Image(getClass().getResource("/assesst/snowyBullet.png").toExternalForm()));
        }else if (bulletType.equals("buble")) {

            greenbullet.setImage(new Image(getClass().getResource("/assesst/ShroomBullet.gif").toExternalForm()));

        }else if (bulletType.equals("shroom")) {
            greenbullet.setImage(new Image(getClass().getResource("/assesst/shroomBullet.png").toExternalForm()));

        }
        else {
            greenbullet.setImage(new Image(getClass().getResource("/assesst/greenBullet.png").toExternalForm()));
        }

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
                Timeline zombieTimeline = zombieTimelineList.get(detectedZombie);
                if (greenBulletBounds.intersects(zombieBounds)) {
                    this.stop();
                    timeline.stop();
                    mainAncharPain.getChildren().remove(greenbullet);
                    if (detectedZombie instanceof StrongZombie) {
                        StrongZombie strongZombie = (StrongZombie) detectedZombie;
                        if (strongZombie.getName().equals("screenDoorZombie")) {
                            if (strongZombie.getAttackResistance() > 0) {

                                strongZombie.setAttackResistance(strongZombie.getAttackResistance() - 10);
                            } else if (strongZombie.getHealth() > 0) {
                                strongZombie.setHealth(strongZombie.getHealth() - 10);
                                if (bulletType.equals("snowy")) {
                                    zombieTimeline.setRate(zombieTimeline.getRate() * .5);
                                }
                            } else {
                                zombieRow.getChildren().remove(zombieImageView);

                            }
                        }

                    } else if (detectedZombie instanceof SpecialZombie) {
                        SpecialZombie specialZombie = (SpecialZombie) detectedZombie;
                        if (specialZombie.getName().equals("newspaperZombie")) {
                            System.out.println("newpaper");
                            if (specialZombie.getChangeablePower() >0){
                                specialZombie.setChangeablePower(specialZombie.getChangeablePower() - 10);
                            }else if (specialZombie.getHealth() > 0) {
                                specialZombie.setHealth(specialZombie.getHealth() - 10);

                                if (bulletType.equals("snowy")) {
                                    zombieTimeline.setRate(zombieTimeline.getRate() * .5);
                                }else {
                                    zombieTimeline.setRate(zombieTimeline.getRate() * 1.5);
                                }
                            }else {
                                zombieRow.getChildren().remove(zombieImageView);
                            }
                        }

                    } else {
                        if (detectedZombie.getHealth() > 0) {
                            detectedZombie.setHealth(detectedZombie.getHealth() - 10);
                            if (bulletType.equals("snowy")) {
                                zombieTimeline.setRate(zombieTimeline.getRate() * .5);
                            }

                        } else {
                            zombieRow.getChildren().remove(zombieImageView);
                        }
                    }


                }


            }
        };
        animationTimer.start();
        timeline.play();
        mainAncharPain.getChildren().add(greenbullet);
        return greenbullet;
    }

    private void zombieEatPlant(ImageView plant, AnchorPane zombieRow, ArrayList<Zombie> zombieList, Plant detectedPlant) {
        for (Node node : zombieRow.getChildren()) {

            if (node instanceof ImageView) {
                ImageView zombieImageView = (ImageView) node;
                Bounds plantBounds = plant.localToScene(plant.getBoundsInLocal());
                Bounds zombieBounds = zombieImageView.localToScene(zombieImageView.getBoundsInLocal());

                Timeline zombieTimeline = zombieTimelineList.get(zombieList.get(zombieRow.getChildren().indexOf(node)));

                if (plantBounds.intersects(zombieBounds) & detectedPlant.getHealth() > 0) {

                    detectedPlant.setHealth(detectedPlant.getHealth() - 10);
                    System.out.println(detectedPlant.getHealth() + " " + detectedPlant.getName());
                    zombieTimeline.pause();
                } else {
                    zombieTimeline.play();
                }
                if (detectedPlant.getHealth() <= 0) {
                    mapGridPane.getChildren().remove(plant);
                }
            }
        }


    }

    private void porduceSun(String type, ImageView plantImageView) {
        ImageView flowerClone = new ImageView();
        flowerClone.setImage(sunImage.getImage());
        flowerClone.setFitWidth(sunImage.getFitWidth());
        flowerClone.setFitHeight(sunImage.getFitHeight());
        flowerClone.setX(sunImage.getX());
        flowerClone.setY(sunImage.getY());
        flowerClone.setVisible(sunImage.isVisible());
        flowerClone.setOnMouseClicked(mouseEvent -> {
            sunAmount += 50;
            sunAmountlabel.setText(String.valueOf(sunAmount));
            stackpainAncherpain.getChildren().remove(flowerClone);
        });
        if (type.equals("sunflower")) {
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

    private void explosivePlant(String type, ImageView plantImageView, Plant detectedPlant, ArrayList<AnchorPane> zombieRowList) {

        for (AnchorPane zombieRow : zombieRowList) {
            if (!zombieRow.getChildren().isEmpty()) {
                ImageView zombieImageView = (ImageView) zombieRow.getChildren().getFirst();
                Bounds plantBounds = plantImageView.localToScene(plantImageView.getBoundsInLocal());
                Bounds zombieBounds = zombieImageView.localToScene(zombieImageView.getBoundsInLocal());
                Bounds explosiveRadious = new BoundingBox(
                        zombieBounds.getMinX() - 500,
                        zombieBounds.getMinY() - 500,
                        zombieBounds.getWidth() + 500,
                        zombieBounds.getHeight() + 500
                );
                if (plantBounds.intersects(zombieBounds)) {
                    zombieRow.getChildren().remove(zombieImageView);

                }

            }


        }
        mapGridPane.getChildren().remove(plantImageView);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Plant plant : plantList) {
            ImageView plantImageView = new ImageView();
            plantImageView.setImage(new Image(getClass().getResource(plant.getGifUrl()).toExternalForm()));
            plantImageView.setOnMouseClicked(mouseEvent -> {
                int index = plantsBar.getChildren().indexOf(plantImageView);
                currentPlant = plantList.get(index);
            });

            plantsBar.getChildren().add(plantImageView);
        }

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
        int zombieNumber = DayMapController.zombieTotalNumber;
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                porduceSun("field", new ImageView());

            }


        }).start();
        Timeline zombieWaveTimeline = new Timeline();

        for (int j = 0; j < DayMapController.zombieWaveNumber; j++) {
            int finalJ = j;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(11 * j),
                    e -> {
                        if (finalJ != DayMapController.zombieWaveNumber - 1) {
                            try {
                                spwanZombie(zombieNumber, zombieRowList);
                            } catch (CloneNotSupportedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        zombieProgressBar.setValue(zombieProgressBar.getValue() + (100 / DayMapController.zombieWaveNumber));
                    }
            );
            zombieWaveTimeline.getKeyFrames().add(keyFrame);


        }
        zombieWaveTimeline.play();

        new Thread(() -> {
            AtomicBoolean run = new AtomicBoolean(true);
            while (run.get()) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                    if (zombieProgressBar.getValue() >= 100) {

                        winAction();
                        run.set(false);
                    }
                });
            }
        }).start();

    }

    private void openLosePage() {
        LosePage losePage = new LosePage();
        try {
            losePage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void spwanZombie(int zombieNumber, ArrayList<AnchorPane> zombieRowList) throws CloneNotSupportedException {
        for (int i = 0; i < zombieNumber; i++) {
            Random random = new Random();
            int randomZombie = random.nextInt(3);


            Zombie zombie = zombieList.get(randomZombie).clone();
            new Thread(() -> {


                int randomPlacer = random.nextInt(5);
                int speedRandom = random.nextInt(10);
                ImageView zombieImageView = new ImageView();
                zombieImageView.setFitWidth(100);
                zombieImageView.setFitHeight(100);
                zombieImageView.setLayoutX(600);
                zombieImageView.setLayoutY(0);
                zombieImageView.setPreserveRatio(true);
                zombieImageView.setSmooth(true);
                zombieImageView.setCache(true);


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

    private void rowAction(int rowIndex, AnchorPane zombieRowList, ImageView cell, ArrayList<Zombie> zombieList, Plant clonePlant) throws CloneNotSupportedException {

        String currentPlantName = currentPlant.getName();
        String plantAddress = cell.getImage().getUrl();
        Platform.runLater(() -> {

            zombieEatPlant(cell, zombieRowList, zombieList, clonePlant);

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.play();
        });
        if (currentPlantName.equals("peashooter") && plantAddress.equals(getClass().getResource("/assesst/Peashooter.gif").toExternalForm())) {

            Platform.runLater(() -> {
                greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "green");
            });
        } else if (currentPlantName.equals("repeater")) {
            Platform.runLater(() -> {

                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                pause.setOnFinished(event -> {
                    greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "green");
                });
                pause.play();
            });

            Platform.runLater(() -> {
                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "green");
                pause.setOnFinished(event -> {

                });
                pause.play();


            });
        } else if (currentPlantName.equals("snowpea")) {
            Platform.runLater(() -> {
                greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "snowy");
            });
        } else if (currentPlantName.equals("cherrybomb")) {
            ArrayList<AnchorPane> zombieAncherpaneList = new ArrayList<>();
            zombieAncherpaneList.add(zombieRow1);
            zombieAncherpaneList.add(zombieRow2);
            zombieAncherpaneList.add(zombieRow3);
            zombieAncherpaneList.add(zombieRow4);
            zombieAncherpaneList.add(zombieRow5);
            Platform.runLater(() -> {
                explosivePlant("cherrybomb", cell, currentPlant, zombieAncherpaneList);
            });
        }else if (currentPlantName.equals("fumeShroom")) {

            Platform.runLater(() -> {

                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                pause.setOnFinished(event -> {
                    greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "buble");
                });
                pause.play();
            });

            Platform.runLater(() -> {
                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "buble");
                pause.setOnFinished(event -> {

                });
                pause.play();


            });
        }else if (currentPlantName.equals("puffShroom")) {
            Platform.runLater(() -> {
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    greenBulletAction(cell, zombieRowList, zombieList.getFirst(), "shroom");
                });
                pause.play();

            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void winAction() {
        WinPage winPage = new WinPage();
        try {
            winPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
