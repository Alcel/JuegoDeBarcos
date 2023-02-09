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

import java.util.Timer;


public class Barco extends Thread{
    ImageView barcoImg;
    AnchorPane tablero;

    int posX;

    int posY;
    double tamX;
    double tamY;
    private ControlDeJuego cdj;
    int sonarCap;

    int id;

    boolean engagingCombat =false;
    long startEng;
    long endEng;

    int ataque;



    public Barco(AnchorPane tablero,int x,int y,double tamX,double tamY,int id,ControlDeJuego cdj, int sonar, int vida, int atq){
        Image barco = new Image(getClass().getResourceAsStream("/images/barcodeguerra.png"));
        barcoImg=new ImageView(barco);
        tablero.getChildren().add(barcoImg);
        posX=x;
        posY=y;
        this.tablero = tablero;
        this.tamX=tamX;
        this.tamY=tamY;
        this.cdj=cdj;
        this.id=id;
        sonarCap=sonar;
        this.ataque=atq;
        cdj.setVida(vida,id);
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
            cdj.setBac(id);
            cdj.posicion(barcoImg.getLayoutX(),barcoImg.getLayoutY());

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

            if(cdj.sonar(sonarCap)!=404&&engagingCombat==false){
                engagingCombat=true;
                System.out.println("El barco localizado es el "+cdj.sonar(sonarCap)+"estableciendo combate");
                cdj.conflicto(ataque);
                startEng= System.currentTimeMillis();
                    }
            endEng = System.currentTimeMillis();

            if (endEng - startEng>5000){

                engagingCombat=false;
            }
        }
    }));

    public void run() {
        barcoImg.setFitHeight(tamX);
        barcoImg.setFitWidth(tamY);
        barcoImg.setLayoutX(posX);
        barcoImg.setLayoutY(posY);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
        timeline.playFromStart();//No es codigo duplicado, exite por si la primera vez no se ejecuta bien


    }
}