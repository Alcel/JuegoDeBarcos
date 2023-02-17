package com.example.juegodebarcos;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.HashMap;

public class ControlDeJuego {
    int numBarcos = 3;

    Barco [] barcos;
    double posXA[]= new double[numBarcos];
    double posYA[]= new double[numBarcos];
    HashMap<Integer,Timeline> mapa = new HashMap<Integer, Timeline>();
    int posX;
    int posY;
    int tamX;
    int tamY;


    HashMap<Integer,Double> vidas = new HashMap<Integer, Double>();
    Timeline timeline;

    int bac;
    public ControlDeJuego(){



    }


    public  void posicion(double x,double y){
        posXA[bac]=x;
        posYA[bac]=y;
    }



    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;

    }


    public double getVida(int id){
        double devolver = vidas.get(id);
        return devolver;
    }

    public  void setBac(int i){
        bac=i;
    }

    public synchronized int sonar(int cap){
        int barcoLocalizado=404;
        for (int i=0;i< posXA.length;i++){
            if(Math.abs((posXA[i]-posXA[bac]))<cap&&!(posXA[i]-posXA[bac]==0)&&(Math.abs((posYA[i]-posYA[bac]))<cap&&!(posYA[i]-posYA[bac]==0))){
               /* System.out.println("posX["+i+"]"+posX[i]);
                System.out.println("posX["+bac+"(bac)]"+posX[bac]);
                 System.out.println("Barco abistado por"+ bac);*/
                barcoLocalizado=i;
            }
        }
        return barcoLocalizado;
    }
    //ARREGLAR


    public void timeline(){
        int cont=0;
        System.out.println(barcos.length);
        for (Barco b:barcos) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),event -> b.movimiento(b.sonarCap,b.engagingCombat,b.ataque)));
            timeline.setCycleCount(Animation.INDEFINITE);
            mapa.put(cont,timeline);
            cont++;
            timeline.play();
        }
    }


    public void conflicto(double ataque, int id) {
        vidas.put(id,vidas.get(id) -ataque);
    }
}