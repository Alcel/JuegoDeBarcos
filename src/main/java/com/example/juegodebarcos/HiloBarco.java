package com.example.juegodebarcos;

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
       // int numC=b.tablero.getColumnCount();
      //  int numF=b.tablero.getRowCount();
        boolean xA = true;
        boolean yA= true;
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

        }

    }
}
