module com.example.juegodebarcos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.juegodebarcos to javafx.fxml;
    exports com.example.juegodebarcos;
}