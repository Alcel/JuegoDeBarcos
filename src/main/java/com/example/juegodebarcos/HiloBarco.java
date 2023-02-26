package com.example.juegodebarcos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HiloBarco extends Thread{
    Barco b;
    int x;
    int y;

    public HiloBarco(Barco b, int x, int y){
        this.b=b;
        this.x=x;
        this.y=y;
    }

    @Override
    public void run() {
        boolean xA = false;
        boolean yA= false;
        ImageView imagen = b.barcoImg;
        while(true){
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(x-1<0){
                xA=true;
            }
            if(y-1<0){
                yA=true;
            }

            if (xA){
                x++;
            }else{
                x--;
            }
            if (yA){
                y++;
            }else{
                y--;
            }
            if(yA&&xA){

                imagen.setRotate(90);
            }
            if(!yA&&!xA){

                imagen.setRotate(180);
            }
        }
    }
}
