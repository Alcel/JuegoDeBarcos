package com.example.juegodebarcos;


import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Barco extends Thread{
    ImageView barcoImg;
    AnchorPane tablero;

    int posX;

    int posY;
    double tamX;
    double tamY;

    int sonarCap;
    Timeline mio;

    int id;

    boolean engagingCombat =false;

    int ataque;
    ControlDeJuego cdj = new ControlDeJuego();
    double deltaX=2;
    double deltaY=2;

    double rotacion=45;





    public Barco(AnchorPane tablero,int x,int y,double tamX,double tamY,int id, int sonar, int vida, int atq){
        Image barco = new Image(getClass().getResourceAsStream("/images/barcodeguerra.png"));
        barcoImg=new ImageView(barco);
        barcoImg.setFitHeight(tamX);
        barcoImg.setFitWidth(tamY);
        posX=x;
        posY=y;
        barcoImg.setLayoutX(posX);

        barcoImg.setLayoutY(posY);
        tablero.getChildren().add(barcoImg);

        this.tablero = tablero;
        this.tamX=tamX;
        this.tamY=tamY;
        cdj.setVida(vida,id);

        this.id=id;
        sonarCap=sonar;
        this.ataque=atq;
    }
    public void movimiento(int sonarCap, Boolean engagingCombat, int ataque){


        long startEng = 0;
        long endEng;


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
        if(cdj.getVida(id)<=0){
            mio.pause();
        }else{


            if(barcoImg.getRotate()==0){
                barcoImg.setRotate(rotacion);
            }


            if(barcoImg.getLayoutX()>=(bounds.getWidth()-realWidth)){
                rightBorder=true;
            }
            if(barcoImg.getLayoutX()<10){

                leftBorder=true;

            }
            if(barcoImg.getLayoutY()>=(bounds.getHeight()-realHeight)*0.91){

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

            if(cdj.sonar(sonarCap)!=404&&engagingCombat==false&&cdj.getVida(cdj.sonar(sonarCap))>0){
                engagingCombat=true;
                System.out.println("El barco localizado es el "+cdj.sonar(sonarCap)+"estableciendo combate");
                cdj.conflicto(ataque,cdj.sonar(sonarCap));
                System.out.println("Ataque");
                System.out.println("Vida del barco dañado "+cdj.getVida(cdj.sonar(sonarCap)));
                startEng= System.currentTimeMillis();
            }
            endEng = System.currentTimeMillis();

            if (endEng - startEng>3000){

                engagingCombat=false;
            }
        }
    }

    public void run() {

    }
}