package com.niloy.dxball_new;

/**
 * Created by user on 5/10/2017.
 */

import java.util.ArrayList;

import android.graphics.Canvas;
import android.util.Log;

public class Collision {

    Bricks brick;
    Ball ball;
    Bar bar;
    Canvas canvas;

    public void setCanvas(Canvas canvas)
    {
        this.canvas=canvas;
    }

    void ballWithBar(ArrayList <GameObject> gameObjectLsit){

        ball=(Ball)gameObjectLsit.get(0);
        if(gameObjectLsit.size()>1)
        {
            bar =(Bar)gameObjectLsit.get(1);
            if(bar.x-10<=ball.x  &&  bar.x1+10>= ball.x  &&  ball.y+30>this.canvas.getHeight()-30)
            {
                ball.speedY=-5;
            }
        }
    }

    void ballWithBrick(ArrayList<GameObject> gameObjectList)
    {
        ball=(Ball)gameObjectList.get(0);

        if(gameObjectList.size()>2)
        {
            for(int i=2;i<gameObjectList.size();i++)
            {
                try{
                    brick=(Bricks) gameObjectList.get(i);

                    if(ball.x>=brick.x-25 && ball.x<= brick.x1+25 && ball.y-30<brick.y1+3 && ball.y-30>brick.y1-3){

                        GameCanvas.Score+=10;
                        brick.life--;
                        ball.speedY=5;
                    }

                    else if(ball.x+30>=brick.x-2 && ball.x+30 <= brick.x+2 && ball.y<= brick.y1+25 && ball.y>= brick.y-25 ){

                        GameCanvas.Score+=10;
                        brick.life--;
                        ball.speedX=-5;

                    }
                    else if(ball.x-30<= brick.x1+2 && ball.x-30>=brick.x1-2 && ball.y<= brick.y1+25 && ball.y >= brick.y-25 )
                    {

                        GameCanvas.Score+=10;
                        brick.life--;
                        ball.speedY=5;
                        ball.speedX=5;

                    }
                    else if(ball.x>=brick.x-25 && ball.x<= brick.x1+25 && ball.y+30>=brick.y-2 && ball.y+30<=brick.y+2)
                    {
                        GameCanvas.Score+=10;
                        brick.life--;
                        ball.speedY=-5;
                        ball.speedX=-5;

                    }




                    if(brick.life==0)
                    {
                        gameObjectList.remove(i);
                    }
                }catch (Exception e)
                {
                    e.getMessage();
                }

            }
        }

    }
}
