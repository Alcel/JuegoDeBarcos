package com.example.juegodebarcos;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView imagen;

    //@FXML
    /*public void onStartButtonClick(ActionEvent actionEvent) {
        barco();
    }*/

    /*public void barco(){

        Barco lancha = new Barco(tablero1);
        HiloBarco h1 = new HiloBarco(lancha,0,1);
        h1.start();

    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        Image barco = new Image(getClass().getResourceAsStream("/images/PNG.png"));
        imagen.setImage(barco);
        translate.setNode(imagen);
        translate.setDuration(Duration.millis(2000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        translate.setAutoReverse(true);
        translate.play();
    }




}