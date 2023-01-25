package com.example.juegodebarcos;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Barco {
    //GridPane tablero;
    /*public Barco(GridPane tablero) {
    this.tablero=tablero;
    }*/
    ImageView barcoImg;
    AnchorPane tablero;
    public Barco(ImageView img, AnchorPane tablero){
        barcoImg=img;
        this.tablero = tablero;
    }
  /*  public void movimiento(int x, int y){

            if(getNodeByRowColumnIndex(x,y,tablero).getText().equals("0")){ //Compruebo si el valor dentro del gridpane es 0 o un barco
                setNodeByRowColumnIndex(x,y,tablero); //Si no hay un barco, se mueve y se pinta su valor(Por ahora el numero 1
            }

    }
    public Text getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) //Recojo el valor que hay dentro del gridpane
    {
        ObservableList<Node> childrens = gridPane.getChildren();
        Text result = (Text) childrens.get((row*5)+column);

        return result;
    }

    public void setNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) //Establezco el nuevo valor que hay dentro del gridpane
    {   //!Arreglar teletransportacion y ajustar velocidad
        ObservableList<Node> childrens = gridPane.getChildren();
        Text result = (Text) childrens.get((row*5)+column);
        System.out.println((row*5)+" "+column);
        result.setText("\uD83D\uDEA2");
    }*/
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        double deltaX=2;
        double deltaY=2;
        @Override
        public void handle(ActionEvent actionEvent) {
            barcoImg.setLayoutX(barcoImg.getLayoutX()+deltaX);
            barcoImg.setLayoutY(barcoImg.getLayoutY()+deltaY);
            Bounds bounds = tablero.getBoundsInLocal();
            int numA=(int) ((Math.random() * 2 + 1));
            System.out.println(numA);


            // System.out.println(bounds.getMaxX()+" "+bounds.getMaxY());
            boolean rightBorder = false;
            boolean leftBorder = false;
            boolean bottomBorder = false;
            boolean topBorder = false;

            if(barcoImg.getLayoutX()>=(bounds.getWidth()-barcoImg.getLayoutX()/6.5)){
                rightBorder=true;
            }
            if(barcoImg.getLayoutX()==0){
                leftBorder=true;
            }
            if(barcoImg.getLayoutY()>=bounds.getHeight()-barcoImg.getLayoutY()/4.3){
                bottomBorder=true;
            }
            if(barcoImg.getLayoutY()==0){
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
            if ((bottomBorder||topBorder)&&numA==2){
                deltaX*=-1;
            }
        }
    }));



    
    public void iniciarMovimiento(int x,int y) {
        TranslateTransition translate = new TranslateTransition();
        Image barco = new Image(getClass().getResourceAsStream("/images/pngegg.png"));
        timeline.setCycleCount(Animation.INDEFINITE);
        barcoImg.setImage(barco);
        timeline.play();
        /*translate.setNode(barcoImg);
        translate.setDuration(Duration.millis(2000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        translate.setAutoReverse(true);
        translate.play();*/
    }
}
