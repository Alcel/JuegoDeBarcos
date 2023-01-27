package com.example.juegodebarcos;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class Barco {
    ImageView barcoImg;
    AnchorPane tablero;
    public Barco(ImageView img, AnchorPane tablero){
        barcoImg=img;
        barcoImg.setRotate(90);
        this.tablero = tablero;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        double deltaX=2;
        double deltaY=2;
        @Override
        public void handle(ActionEvent actionEvent) {
            barcoImg.setLayoutX(barcoImg.getLayoutX()+deltaX);
            barcoImg.setLayoutY(barcoImg.getLayoutY()+deltaY);
            Bounds bounds = tablero.getBoundsInLocal();
            int numA=(int) ((Math.random() * 2 + 1));


            boolean rightBorder = false;
            boolean leftBorder = false;
            boolean bottomBorder = false;
            boolean topBorder = false;

            if(barcoImg.getLayoutX()>=(bounds.getWidth()-barcoImg.getLayoutX()/6.5)){
                rightBorder=true;
                barcoImg.setRotate(0);
            }
            if(barcoImg.getLayoutX()<10){
                barcoImg.setRotate(0);
                leftBorder=true;
            }
            if(barcoImg.getLayoutY()>=bounds.getHeight()-barcoImg.getLayoutY()/4.3){
                bottomBorder=true;
                barcoImg.setRotate(90);
            }
            if(barcoImg.getLayoutY()<10){
                topBorder=true;
                barcoImg.setRotate(90);
            }

            if (rightBorder||leftBorder){

                deltaX*=-1;

            }
            if (bottomBorder||topBorder){
                deltaY*=-1;
            }
            if ((rightBorder||leftBorder)&&numA==2){

                deltaY*=-1;

            }
            if ((bottomBorder||topBorder)&&numA==2){
                deltaX*=-1;
            }
        }
    }));

    public void iniciarMovimiento(int x,int y) {
        TranslateTransition translate = new TranslateTransition();
        Image barco = new Image(getClass().getResourceAsStream("/images/barcodeguerra.png"));
        timeline.setCycleCount(Animation.INDEFINITE);
        barcoImg.setImage(barco);
        timeline.play();
    }
}
