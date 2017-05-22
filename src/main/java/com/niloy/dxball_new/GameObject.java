package com.niloy.dxball_new;

import android.graphics.Canvas;

/**
 * Created by user on 5/10/2017.
 */

public abstract class GameObject {
    int x;
    int y;

    public GameObject(int x, int y){
        this.x=x;
        this.y=y;
    }

    public abstract void onDraw(Canvas canvas);

    public int getX(){
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    int speed =1;


}
