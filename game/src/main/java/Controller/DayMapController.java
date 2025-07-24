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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;

public class DayMapController implements Initializable {

    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    ArrayList<Zombie> row1ZombieList = new ArrayList<>() ;
    ArrayList<Zombie> row2ZombieList = new ArrayList<>() ;
    ArrayList<Zombie> row3ZombieList = new ArrayList<>() ;
    ArrayList<Zombie> row4ZombieList = new ArrayList<>() ;
    ArrayList<Zombie> row5ZombieList = new ArrayList<>() ;
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
    void setPlant(MouseEvent event) {

        Node clickedNode = (Node) event.getSource();
        for (Node node : mapGridPane.getChildren()) {

            Integer colIndex = GridPane.getColumnIndex(clickedNode);

            if (colIndex == null) {
                colIndex = 0;
            }
            if (node.equals(clickedNode)) {
                ImageView cell = (ImageView) node;
                if (cell.getImage() != null) {
                    showLimitAlert();
                }
                cell.setImage(new Image(getClass().getResource(currentPlant.getGifUrl()).toExternalForm()));
                new Thread(() -> {
                    final ImageView[] bullet = new ImageView[1];
                    Integer rowIndex = GridPane.getRowIndex((Node) cell);
                    if (rowIndex == null) {
                        rowIndex = 0;
                    }
                    while (rowIndex == 0 && !zombieRow1.getChildren().isEmpty()) {
                        Platform.runLater(() -> {
                             greenBulletAction(cell,  zombieRow1 , row1ZombieList.getFirst());
                        });


                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 1 && !zombieRow2.getChildren().isEmpty()) {

                        Platform.runLater(() -> {
                            greenBulletAction(cell,  zombieRow2 ,row2ZombieList.getFirst());
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 2 && !zombieRow3.getChildren().isEmpty()) {
                        Platform.runLater(() -> {
                            greenBulletAction(cell,  zombieRow3,row3ZombieList.getFirst());
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 3 && !zombieRow4.getChildren().isEmpty()) {
                        Platform.runLater(() -> {
                            greenBulletAction(cell,  zombieRow4,row4ZombieList.getFirst());
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (rowIndex == 4 && !zombieRow5.getChildren().isEmpty()) {
                        Platform.runLater(() -> {
                            greenBulletAction(cell, zombieRow5,row5ZombieList.getFirst());
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
        currentPlant = plant1;
    }

    @FXML
    void choosePlant2(MouseEvent event) {
        Plant SunFlower = new Plant();
        SunFlower.setName("SunFlower");
        SunFlower.setGifUrl("/assesst/sunflower.gif");
        SunFlower.setBuildCost(50);
        currentPlant = SunFlower;
    }

    @FXML
    void test(MouseEvent event) {
        System.out.println("test");
    }


    private void showLimitAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You already have a plant in this area");
        alert.showAndWait();
    }

    private Zombie simpleZombie() {
        Zombie zombie = new Zombie();
        zombie.setName("zombie");
        zombie.setGifUrl("/assesst/Zombie.gif");
        zombie.setHealth(50);
        return zombie;
    }

    private Zombie ConeheadZombie() {
        Zombie zombie = new Zombie();
        zombie.setName("zombie");
        zombie.setGifUrl("/assesst/Conehead_Zombie_3.gif");
        zombie.setHealth(50);
        return zombie;
    }

    private ImageView greenBulletAction(ImageView plant, AnchorPane zombieRow , Zombie detectedZombie) {
        ImageView zombieImageView =(ImageView) zombieRow.getChildren().getFirst();
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
                       if (detectedZombie.getHealth() >0){
                           detectedZombie.setHealth(detectedZombie.getHealth() -10);
                       }else {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Zombie> zombieList = new ArrayList<>();
        zombieList.add(simpleZombie());
        zombieList.add(ConeheadZombie());
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
        int zombieNumber = 4;
        for (int i = 0; i < zombieNumber; i++) {
            new Thread(() -> {
                Random random = new Random();
                int randomZombie = random.nextInt(2);
                int randomPlacer = random.nextInt(5);
                int speedRandom = random.nextInt(10);
                ImageView zombieImageView = new ImageView();
                zombieImageView.setFitWidth(100);
                zombieImageView.setFitHeight(100);
                zombieImageView.setLayoutX(600);
                zombieImageView.setLayoutY(0);
                Zombie zombie = zombieList.get(randomZombie);
                if (randomPlacer ==0){
                    row1ZombieList.add(zombie);
                }else if (randomPlacer ==1){
                    row2ZombieList.add(zombie);
                }else if (randomPlacer ==2){
                    row3ZombieList.add(zombie);
                }else if (randomPlacer ==3){
                    row4ZombieList.add(zombie);
                }else {
                    row5ZombieList.add(zombie);
                }
                zombieImageView.setImage(new Image(getClass().getResource(zombie.getGifUrl()).toExternalForm()));
                Platform.runLater(() -> {
                    zombieRowList.get(randomPlacer).getChildren().add(zombieImageView);
                    zombieImageView.setImage(new Image(getClass().getResource(zombie.getGifUrl()).toExternalForm()));
                    Timeline timeline = new Timeline(

                            new KeyFrame(Duration.seconds(30 + speedRandom),
                                    new KeyValue(zombieImageView.layoutXProperty(), -(zombieRowList.get(randomPlacer).getWidth()-550 ), Interpolator.LINEAR))
                    );
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

    private void openLosePage(){
        LosePage losePage = new LosePage();
        try {
            losePage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
