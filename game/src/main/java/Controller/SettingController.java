package Controller;

import Model.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
        Database.getInstance().updateInfo(LoginController.getCurrentPlayer().getId() ,"name" ,newName.getText());
    }

    @FXML
    void changePassword(MouseEvent event) throws SQLException {
        Database.getInstance().updateInfo(LoginController.getCurrentPlayer().getId() ,"password" ,newPassword.getText());

    }

    @FXML
    void changeUsername(MouseEvent event) throws SQLException {
        Database.getInstance().updateInfo(LoginController.getCurrentPlayer().getId() ,"username" ,newUsername.getText());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
