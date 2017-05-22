package com.niloy.dxball_new;

/**
 * Created by user on 5/10/2017.
 */

import android.graphics.Canvas;

public class GameThread extends Thread {

    Boolean running;
    GameCanvas gameCanvas;
    GameObject ball;

    public GameThread(GameCanvas gameCanvas){
        this.gameCanvas=gameCanvas;
    }

    public void setRunning(Boolean run){
        running=run;
    }

    Canvas c = new Canvas();

    @Override
    public void run(){

        while (running){
            try {
                sleep(400);
            }
            catch (InterruptedException e){
                e.getMessage();
            }

            try {
                gameCanvas.draw(c);
            }
            catch (Exception e){
                e.getMessage();
            }

            finally {
                c=null;
            }
        }
    }
}
