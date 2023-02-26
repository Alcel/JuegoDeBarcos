package com.example.juegodebarcos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private AnchorPane scene;
    private Media media = new Media(getClass().getResource("/audio/eu.mp3").toString());
    private final MediaPlayer mp = new MediaPlayer(media);

    //@FXML
    /*public void onStartButtonClick(ActionEvent actionEvent) {
        barco();
    }*/

    /*public void barco(){
        Barco lancha = new Barco(tablero1);
        HiloBarco h1 = new HiloBarco(lancha,0,1);
        h1.start();
    }*/



    public void initialize(URL url, ResourceBundle resourceBundle) {
        String vg= "/images/sea.jpg";
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
        int tamAr= 8;
        float tamMod = 0.7f; //Modifica el tamaño de todos los barcos
        Barco []barcos= new Barco[tamAr];

        ImageView bg = new ImageView(new Image(getClass().getResourceAsStream(vg),1280,720,false,true));

        scene.getChildren().add(0,bg);
        ControlDeJuego cdj = new ControlDeJuego(tamAr);
        Barco lanchaA = new Barco(cdj,scene,800,100,30*tamMod,70*tamMod,0,200,10,30,0.8f,9 ,0,false);
        Barco acorazadoA = new Barco(cdj,scene,800,200,50*tamMod,125*tamMod,1,20,120,80,0.8f,15,0,false );
        Barco destructorA = new Barco(cdj,scene,800,300,40*tamMod,100*tamMod,2,12 ,80,80,0.8f,13,0,false );
        Barco submarinoA = new Barco(cdj,scene,800,400,35*tamMod,87.5*tamMod,3,20,30,80,0.8f,11,0 ,false);

        Barco lanchaB = new Barco(cdj,scene,100,100,30*tamMod,70*tamMod,4,200,10,30,0.8f,9,1 ,false);
        Barco acorazadoB = new Barco(cdj,scene,100,300,50*tamMod,125*tamMod,5,20,120,80,0.8f,15,1,false );
        Barco destructorB = new Barco(cdj,scene,100,200,40*tamMod,100*tamMod,6,12 ,80,80,0.8f,13,1,false );
        Barco submarinoB =new Barco(cdj,scene,100,400,35*tamMod,87.5*tamMod,7,20,30,80,0.8f,11,1,false );

        barcos[0]=lanchaA;
        barcos[1]=acorazadoA;
        barcos[2]=destructorA;
        barcos[3]=submarinoA;
        barcos[4]=lanchaB;
        barcos[5]=acorazadoB;
        barcos[6]=destructorB;
        barcos[7]=submarinoB;


        cdj.setBarcos(barcos);


        for (Barco b:barcos
             ) {
            b.start();

        }
        cdj.timeline();

        try{
            for (Barco b:barcos
            ) {
                b.join();

            }

        }catch(InterruptedException e){
            System.err.println("Error");
        }
    }
}