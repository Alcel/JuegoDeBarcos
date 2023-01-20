module com.example.juegodebarcos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juegodebarcos to javafx.fxml;
    exports com.example.juegodebarcos;
}