package com.example.juegodebarcos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private AnchorPane scene;

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
       ImageView bg = new ImageView(new Image(getClass().getResourceAsStream(vg),1280,720,false,true));

        scene.getChildren().add(0,bg);
        Barco lancha = new Barco(imagen,scene,60,100);
        lancha.iniciarMovimiento(200,100);

    }






}