module com.example.zadanie3 {
    requires javafx.controls;
    requires javafx.fxml;


    exports sample.gui;
    opens sample.gui to javafx.fxml;
}