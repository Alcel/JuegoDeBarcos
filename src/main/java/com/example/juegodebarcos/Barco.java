package com.example.juegodebarcos;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Barco {
    GridPane tablero;
    public Barco(GridPane tablero) {
    this.tablero=tablero;
    }
    public void movimiento(int x, int y){

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
    }
}
