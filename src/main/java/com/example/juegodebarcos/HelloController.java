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

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        double deltaX=2;
        double deltaY=2;
        @Override
        public void handle(ActionEvent actionEvent) {
            imagen.setLayoutX(imagen.getLayoutX()+deltaX);
            imagen.setLayoutY(imagen.getLayoutY()+deltaY);
            Bounds bounds = scene.getBoundsInLocal();
            boolean rightBorder = imagen.getLayoutX()>=(bounds.getMaxX()-(imagen.getLayoutX()/2));
            boolean leftBorder = imagen.getLayoutX()<=(bounds.getMaxX()-(imagen.getLayoutX()/2));
            boolean bottomBorder = imagen.getLayoutY()>=(bounds.getMaxX()-(imagen.getLayoutY()/2));
            boolean topBorder = imagen.getLayoutY()<=(bounds.getMaxX()-(imagen.getLayoutY()/2));
            if (rightBorder||leftBorder){
                deltaX*=-1;
            }
            if (bottomBorder||topBorder){
                deltaY*=-1;
            }
        }
    }));



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        Image barco = new Image(getClass().getResourceAsStream("/images/PNG.png"));
        timeline.setCycleCount(Animation.INDEFINITE);
        imagen.setImage(barco);
        timeline.play();
        /*translate.setNode(imagen);
        translate.setDuration(Duration.millis(2000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        translate.setAutoReverse(true);
        translate.play();*/
    }




}