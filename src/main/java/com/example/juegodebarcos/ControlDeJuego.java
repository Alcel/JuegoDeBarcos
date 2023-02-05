package com.example.juegodebarcos;

import java.lang.reflect.Array;

public class ControlDeJuego {
    double posX[]= new double[2];
    double posY[]= new double[2];

    int bac;
    public ControlDeJuego(){

    }

    public  void posicion(double x,double y){
        posX[bac]=x;
        posY[bac]=y;
    }

    public  void setBac(int i){

        bac=1;
    }

    public synchronized  void sonar(){

        for (int i=0;i< posX.length;i++){
            if(posX[i]-posX[bac]<200&&posX[i]-posX[bac]>0){
                System.out.println("Barco abistado");
            }
            else if(posY[i]-posY[bac]<200&&posY[i]-posY[bac]>0){
                System.out.println("Barco abistado");
            }
        }

    }

}
