package com.example.juegodebarcos;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private AnchorPane scene;

    //@FXML
    /*public void onStartButtonClick(ActionEvent actionEvent) {
        barco();
    }*/

    /*public void barco(){

        Barco lancha = new Barco(tablero1);
        HiloBarco h1 = new HiloBarco(lancha,0,1);
        h1.start();

    }*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Barco lancha = new Barco(imagen,scene);
        lancha.iniciarMovimiento(0,0);
    }






}