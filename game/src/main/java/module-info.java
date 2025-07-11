module org.example.game1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens view to javafx.fxml;
    opens assesst to javafx.fxml;

    exports view;
    exports assesst;
}
