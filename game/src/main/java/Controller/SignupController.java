package Controller;

import Model.Database;
import Model.Player;
import View.DayLevelMenuPage;
import View.SignupPage;
import View.StartPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
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
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeadPasswordField;

    @FXML
    private TextField usernamwField;

    @FXML
    void SingupAction(MouseEvent event) throws SQLException {
        Player newPlayer = new Player();
        newPlayer.setEmail(emailField.getText());
        newPlayer.setName(nameField.getText());
        newPlayer.setPassword(passwordField.getText());
        newPlayer.setUsername(usernamwField.getText());
        Database.getInstance().addNewPlayer(newPlayer);
        Database.getInstance().addNewPlant(Database.getInstance().setId() -1 ,"peashooter");
        Database.getInstance().addNewPlant(Database.getInstance().setId() -1 ,"sunflower");
        Database.getInstance().addNewLevel(Database.getInstance().setId() -1 ,0);
        LoginController.setCurrentPlayer(newPlayer);
        openLevelMenu();
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
    private  void openLevelMenu(){
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundImage.fitWidthProperty().bind(mainAncherPain.widthProperty());
        backgroundImage.fitHeightProperty().bind(mainAncherPain.heightProperty());
    }
}
