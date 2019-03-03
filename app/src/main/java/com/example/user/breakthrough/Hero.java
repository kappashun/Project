package com.example.user.breakthrough;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;

public class Hero {
    public float corX = 100;
    public float corY = 500;
    private float vX = 2;
    private float vY = 1;
    private float velocity = 10;
    private float vel = 0;
    public int rad = 50;
    public float vecX = vX*velocity;
    public float vecY = vY*velocity;

    void move(){
        if(corX < 0){
            vX = -vX;
        }
        if(corY < 0 || corY > 1422){
            vY = -vY;
        }
        corX += vecX;
        corY += vecY;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setVec(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        corX = 100;
        corY = 500;
        float l = (float)Math.sqrt((x - corX)*(x - corX) + (y - corY)*(y - corY));
        vX = (x - corX)/l;
        vY = (y - corY)/l;
    }

    void onDraw(Canvas canvas){
        Log.d("cor", corX + " " + corY);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(corX , corY, rad, paint);
    }
}
