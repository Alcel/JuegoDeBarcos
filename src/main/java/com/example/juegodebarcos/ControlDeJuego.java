package com.example.juegodebarcos;

import java.lang.reflect.Array;

public class ControlDeJuego {
    int numBarcos = 3;
    double posX[]= new double[numBarcos];
    double posY[]= new double[numBarcos];

    int vidas[]= new int[numBarcos];

    int bac;
    public ControlDeJuego(){

    }


    public  void posicion(double x,double y){
        posX[bac]=x;
        posY[bac]=y;
    }

    public void setVida(int i, int id){
        vidas[id]=i;
    }

    public int getVida(int i){
        int devolver = vidas[i];
        return devolver;
    }

    public  void setBac(int i){
        bac=i;
    }

    public synchronized int sonar(int cap){
        int barcoLocalizado=404;
        for (int i=0;i< posX.length;i++){
            if(Math.abs((posX[i]-posX[bac]))<cap&&!(posX[i]-posX[bac]==0)&&(Math.abs((posY[i]-posY[bac]))<cap&&!(posY[i]-posY[bac]==0))){
               /* System.out.println("posX["+i+"]"+posX[i]);
                System.out.println("posX["+bac+"(bac)]"+posX[bac]);
                 System.out.println("Barco abistado por"+ bac);*/
                barcoLocalizado=i;
            }
        }
        return barcoLocalizado;
    }

    public void conflicto(int ataque,int id) {
        vidas[id] = vidas[id] -ataque;
    }
}