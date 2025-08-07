package Controller;

import Model.Database;
import View.DayLevelMenuPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    private static Stage currentStage;
    public static Stage getCurrentStage() {
        return currentStage;
    }
    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    @FXML
    private TextField newName;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField newUsername;
    @FXML
    void musicOffAction(MouseEvent event) {
        StartController.getMusicPlayer().pause();
    }

    @FXML
    void musicOnAction(MouseEvent event) {
       StartController.getMusicPlayer().play();
    }

    @FXML
    void changeName(MouseEvent event) throws SQLException {
        DatabaseController.updateInfo(LoginController.getCurrentPlayer().getId() ,"name" ,newName.getText());
        newUsername.clear();
    }

    @FXML
    void changePassword(MouseEvent event) throws SQLException {
        DatabaseController.updateInfo(LoginController.getCurrentPlayer().getId() ,"password" ,newPassword.getText());
        newPassword.clear();

    }

    @FXML
    void changeUsername(MouseEvent event) throws SQLException {
        DatabaseController.updateInfo(LoginController.getCurrentPlayer().getId() ,"username" ,newUsername.getText());
        newUsername.clear();

    }
    @FXML
    void backAction(MouseEvent event) {
        DayLevelMenuPage dayLevelMenuPage = new DayLevelMenuPage();
        try {
            dayLevelMenuPage.start(currentStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newPassword.setText(LoginController.getCurrentPlayer().getPassword());
        newUsername.setText(LoginController.getCurrentPlayer().getUsername());
        newName.setText(LoginController.getCurrentPlayer().getName());
    }
}
