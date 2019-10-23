module local.example.seed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens local.example.seed to javafx.fxml;
    exports local.example.seed;
}
