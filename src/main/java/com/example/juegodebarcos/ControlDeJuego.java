package com.example.juegodebarcos;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.control.Alert;
import javafx.util.Duration;

import java.util.HashMap;


public class ControlDeJuego {
    int numBarcos = 3;

    Barco [] barcos;
    double posXA[];
    double posYA[];
    HashMap<Integer,Timeline> mapa = new HashMap<Integer, Timeline>();


    HashMap<Integer,Double> vidas = new HashMap<Integer, Double>();
    Timeline timelineInterior;

    int bac;
    public ControlDeJuego(int tam){
        this.numBarcos=tam;
        posXA= new double[numBarcos];
        posYA= new double[numBarcos];
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
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(b.velocidad),event -> b.movimiento(b.sonarCap,b.ataque)));
            timeline.setCycleCount(Animation.INDEFINITE);
            mapa.put(cont,timeline);
            cont++;
            timeline.play();
            timelineInterior=timeline;

        }
    }


    public void conflicto(double ataque, int id,float precision,int equipo) {
        float probabilidad = (float) Math.random();

        System.out.println(probabilidad);
        if (probabilidad<precision&&barcos[id].equipo!=equipo&&barcos[id].vida>1){
            //Regla de tres
            double modificadorDeDanno = (ataque*probabilidad)/precision;
            vidas.put(id,vidas.get(id) -modificadorDeDanno);
        }
        partida();



    }

    public boolean getEngage(int id){
        return barcos[id].engagingCombat;

    }

    public void setEngage(boolean fuego,int id){
        barcos[id].engagingCombat=fuego;

    }

    public void partida(){
        double equipo0Vida=0;
        double equipo1Vida=0;
        for (Barco b:barcos){
            if (vidas.get(b.id)<0){
                vidas.put(b.id,0.0);
            }
            if(b.equipo==0){

                equipo0Vida+=vidas.get(b.id);
            }
            else {
                equipo1Vida+=vidas.get(b.id);
            }
        }

        if (equipo0Vida<1){
            timelineInterior.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin");
            alert.setContentText("Ha ganado el equipo 1");
            alert.show();
            
        } else if (equipo1Vida<1) {
            timelineInterior.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin");
            alert.setContentText("Ha ganado el equipo 2");
            alert.show();
            
        }
    }
}