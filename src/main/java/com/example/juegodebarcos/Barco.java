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

    double tamX;
    double tamY;


    public Barco(AnchorPane tablero,double tamX,double tamY){
        Image barco = new Image(getClass().getResourceAsStream("/images/barcodeguerra.png"));
        barcoImg=new ImageView(barco);
        tablero.getChildren().add(barcoImg);
        this.tablero = tablero;
        this.tamX=tamX;
        this.tamY=tamY;


    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        double deltaX=2;
        double deltaY=2;

        double rotacion=45;


        @Override
        public void handle(ActionEvent actionEvent) {
            barcoImg.setLayoutX(barcoImg.getLayoutX()+deltaX);
            barcoImg.setLayoutY(barcoImg.getLayoutY()+deltaY);
            Bounds bounds = tablero.getBoundsInLocal();
            int numA=(int) ((Math.random() * 7 + 1));
            double aspectRatio = barcoImg.getImage().getWidth() / barcoImg.getImage().getHeight();
            double realWidth = Math.min(barcoImg.getFitWidth(), barcoImg.getFitHeight() * aspectRatio);
            double realHeight = Math.min(barcoImg.getFitHeight(), barcoImg.getFitWidth() * aspectRatio);


            boolean rightBorder = false;
            boolean leftBorder = false;
            boolean bottomBorder = false;
            boolean topBorder = false;


            if(barcoImg.getRotate()==0){
                barcoImg.setRotate(rotacion);
            }


            if(barcoImg.getLayoutX()>=(bounds.getWidth()-realWidth)){
                rightBorder=true;
            }
            if(barcoImg.getLayoutX()<10){

                leftBorder=true;

            }
            if(barcoImg.getLayoutY()>=(bounds.getHeight()-realHeight)*0.93){

                bottomBorder=true;

            }
            if(barcoImg.getLayoutY()<10){
                topBorder=true;


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
            if ((bottomBorder||topBorder)&&numA==2) {
                deltaX *= -1;

            }
            if ((rightBorder||leftBorder||bottomBorder||topBorder)&&numA!=2){
                rotacion+=90;


            }
            if (rotacion==405){
                rotacion=45;
            }
            barcoImg.setRotate(rotacion);

        }
    }));

    public void iniciarMovimiento(int x,int y) {
        barcoImg.setFitHeight(tamX);
        barcoImg.setFitWidth(tamY);
        barcoImg.setLayoutX(x);
        barcoImg.setLayoutY(y);
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }
}
