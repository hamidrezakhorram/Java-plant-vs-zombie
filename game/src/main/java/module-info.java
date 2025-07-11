module org.example.game1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens View to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Model to javafx.fxml;
    exports View;
    exports Controller;
    exports Model;
    exports Model.plants;
    opens Model.plants to javafx.fxml;
}
