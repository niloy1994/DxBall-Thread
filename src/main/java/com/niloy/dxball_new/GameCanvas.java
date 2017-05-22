package com.niloy.dxball_new;

/**
 * Created by user on 5/9/2017.
 */

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class GameCanvas extends View {

    static int  Score=0,life=3;
    Paint paint= new Paint();
    Boolean first=true,onTouch=false;
    int speed=15,barX,width,height;
    int level=1;
    Context context;
    GameThread gameThread=new GameThread(this);
    Ball b;
    Bar ba;
    GameObject ball=new Ball(0, 0);
    GameObject bar= new Bar(1,2);

    ArrayList<GameObject> gameObjectsList = new ArrayList<GameObject>();

    public GameCanvas(Context context) {

        super(context);

        this.context=context;
        gameObjectsList.add(ball);
        gameObjectsList.add(bar);
        gameThread.setRunning(true);
        gameThread.start();

    }


    public void stage() {

        int brickX=(width/7);
        int brickY=(height/14);

        gameObjectsList.add(new Bricks(brickX*2,brickX*3,brickY*2,brickY*3,1));
        gameObjectsList.add(new Bricks(brickX*3,brickX*4,brickY*2,brickY*3,1));
        gameObjectsList.add(new Bricks(brickX*4,brickX*5,brickY*2,brickY*3,1));


        gameObjectsList.add(new Bricks(brickX,brickX*2,brickY*3,brickY*4,1));
        gameObjectsList.add(new Bricks(brickX*2,brickX*3,brickY*3,brickY*4,2));
        gameObjectsList.add(new Bricks(brickX*3,brickX*4,brickY*3,brickY*4,3));
        gameObjectsList.add(new Bricks(brickX*4,brickX*5,brickY*3,brickY*4,2));
        gameObjectsList.add(new Bricks(brickX*5,brickX*6,brickY*3,brickY*4,1));

        gameObjectsList.add(new Bricks(brickX*2,brickX*3,brickY*4,brickY*5,1));
        gameObjectsList.add(new Bricks(brickX*3,brickX*4,brickY*4,brickY*5,1));
        gameObjectsList.add(new Bricks(brickX*4,brickX*5,brickY*4,brickY*5,1));




        b=(Ball)gameObjectsList.get(0);
        ba= (Bar)gameObjectsList.get(1);
        onTouch=true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(onTouch){

            if (event.getAction()==event.ACTION_MOVE)
            {
                if(event.getX() <= width/2-100)
                {
                    if(ba.x<=20 )
                    {

                        speed=0;
                    }
                    else
                    {
                        speed=-15;
                    }
                }
                else if(event.getX() >= width/2+100)
                {
                    if(ba.x1>=width-20){
                        speed=0;
                    }
                    else
                    {
                        speed=15;
                    }

                }
                else
                {
                    speed=0;
                }

                ba.x+=speed;
                ba.x1+=speed;

            }
        }
        return true;
    }


    Collision collision= new Collision();
    @Override
    protected void onDraw(Canvas canvas) {


        if(first)
        {
            canvas.drawColor(Color.WHITE);
            width=canvas.getWidth();
            height=canvas.getHeight();

            stage();
            ball.x=width/2;
            ball.y=height/2;
            first=false;
        }
        else
        {
            if(gameObjectsList.size()>2)
            {
                if(life!=0)
                {
                    paint.setColor(Color.BLUE);
                    paint.setTextSize(40);
                    canvas.drawText("Score: "+String.valueOf(Score), width-180, 35, paint);
                    paint.setColor(Color.RED);
                    canvas.drawText("Life: "+String.valueOf(life), 20, 35, paint);
                    for(int i=0;i<gameObjectsList.size();i++)
                    {
                        gameObjectsList.get(i).onDraw(canvas);

                    }

                    b.update();

                    this.collision.setCanvas(canvas);
                    collision.ballWithBar(gameObjectsList);
                    collision.ballWithBrick(gameObjectsList);

                }
                else
                {
                    paint.setColor(Color.RED);
                    paint.setTextSize(80);
                    gameThread.setRunning(false);
                    canvas.drawText("GAME OVER", width/2-200, height/2, paint);
                }


            }
            else
            {
                try
                {

                    gameThread.setRunning(false);
                    gameThread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameThread.setRunning(true);
                if(level<2)
                {
                    stage();

                    ball.x=width/2;
                    ball.y=90;
                }
                else{
                    paint.setColor(Color.RED);
                    paint.setTextSize(80);
                    canvas.drawText("Congratulation!", width/2-60, height/2, paint);
                }
            }
        }


        invalidate();


    }
}
