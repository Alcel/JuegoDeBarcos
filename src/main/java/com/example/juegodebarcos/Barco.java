package com.example.juegodebarcos;


import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.effect.ColorAdjust;
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
    double vida;
    boolean engagingCombat;
    int ataque;
    ControlDeJuego cdj;
    double deltaX=2;
    double deltaY=2;
    double rotacion=45;
    long startEng;
    long endEng;

    float precision = 1;
    int velocidad;

    int equipo;
    long starBorde;





    public Barco(ControlDeJuego cdj,AnchorPane tablero,int x,int y,double tamX,double tamY,int id, int sonar, double vida, int atq,float precision,int velocidad,int equipo, boolean engagingCombat){
        Image barco = new Image(getClass().getResourceAsStream("/images/barcodeguerra.png"));
        barcoImg=new ImageView(barco);
        barcoImg.setFitHeight(tamX);
        barcoImg.setFitWidth(tamY);
        posX=x;
        posY=y;
        barcoImg.setLayoutX(posX);
        this.vida = vida;
        this.engagingCombat=engagingCombat;
        this.precision=precision;
        this.velocidad=velocidad;
        this.equipo=equipo;


        barcoImg.setLayoutY(posY);
        tablero.getChildren().add(barcoImg);

        ColorAdjust colorAdjust = new ColorAdjust();


        this.tablero = tablero;
        this.tamX=tamX;
        this.tamY=tamY;
        this.cdj=cdj;
        this.id=id;
        sonarCap=sonar;
        this.ataque=atq;
        cdj.vidas.put(id,vida);
        if (equipo==0){
            deltaX*=-1;
            deltaY*=-1;
            colorAdjust.setContrast(0.1);
            colorAdjust.setHue(-0.8);
            colorAdjust.setBrightness(0.1);
            colorAdjust.setSaturation(0.2);

        }
        else{
            colorAdjust.setContrast(0.1);
            colorAdjust.setHue(-7);
            colorAdjust.setBrightness(0.1);
            colorAdjust.setSaturation(0.2);

        }

        this.barcoImg.setEffect(colorAdjust);
    }





    public void movimiento(int sonarCap, int ataque){

        barcoImg.setLayoutX(barcoImg.getLayoutX()+deltaX);
        barcoImg.setLayoutY(barcoImg.getLayoutY()+deltaY);
        Bounds bounds = tablero.getBoundsInLocal();
        int numA=(int) ((Math.random() * 7 + 1));
        double aspectRatio = barcoImg.getImage().getWidth() / barcoImg.getImage().getHeight();
        double realWidth = Math.min(barcoImg.getFitWidth(), barcoImg.getFitHeight() * aspectRatio);
        double realHeight = Math.min(barcoImg.getFitHeight(), barcoImg.getFitWidth() * aspectRatio);

        cdj.setBac(id);


        cdj.posicion(barcoImg.getLayoutX(),barcoImg.getLayoutY());
        long ahoraBorde = System.currentTimeMillis();



        boolean rightBorder = false;
        boolean leftBorder = false;
        boolean bottomBorder = false;
        boolean topBorder = false;

        if (!cdj.vidas.isEmpty()) {
            if (cdj.getVida(id) <= 0) {
                cdj.mapa.get(id).stop();
            } else {
                if (barcoImg.getRotate() == 0) {
                    barcoImg.setRotate(rotacion);
                }


                if (barcoImg.getLayoutX() >= (bounds.getWidth() - realWidth)*0.98) {
                    rightBorder = true;

                }
                if (barcoImg.getLayoutX() < 10) {
                    leftBorder = true;

                }
                if (barcoImg.getLayoutY() >= (bounds.getHeight() - realHeight) * 0.88&&(ahoraBorde-starBorde)>1200) {
                    System.out.println("Abajo");
                    starBorde = System.currentTimeMillis();
                    bottomBorder = true;

                }
                if (barcoImg.getLayoutY() < 10) {

                    topBorder = true;
                }

                if (rightBorder || leftBorder) {
                    deltaX *= -1;

                }
                if (bottomBorder || topBorder) {
                    deltaY *= -1;

                }
                if ((rightBorder || leftBorder) && numA == 2) {
                    deltaY *= -1;


                }
                if ((bottomBorder || topBorder) && numA == 2) {
                    deltaX *= -1;

                }
                if ((rightBorder || leftBorder || bottomBorder || topBorder) && numA != 2) {
                    rotacion += 90;


                }
                if (rotacion == 405) {
                    rotacion = 45;
                }
                barcoImg.setRotate(rotacion);


                if (cdj.sonar(sonarCap) != 404 && cdj.barcos[id].engagingCombat == false && cdj.getVida(cdj.sonar(sonarCap)) > 0) {
                    cdj.barcos[id].engagingCombat = true;
                    System.out.println("El barco localizado es el " + cdj.sonar(sonarCap) + "estableciendo combate");
                    cdj.conflicto(ataque, cdj.sonar(sonarCap),precision,equipo);
                    System.out.println("Ataque");
                    System.out.println("Vida del barco dañado " + cdj.getVida(cdj.sonar(sonarCap)));
                    startEng = System.currentTimeMillis();
                }
                endEng = System.currentTimeMillis();

                if (endEng - startEng > 2500) {
                    cdj.barcos[id].engagingCombat = false;
                }
            }
        }

    }

    public void run() {

    }
}