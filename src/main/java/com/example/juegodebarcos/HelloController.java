package com.example.juegodebarcos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button start;
    @FXML
    private GridPane tablero;
    @FXML
    private GridPane tablero1;

    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) {
        barco();
    }

    public void barco(){

        Barco lancha = new Barco(tablero1);
        HiloBarco h1 = new HiloBarco(lancha,0,1);
        h1.start();

    }


}