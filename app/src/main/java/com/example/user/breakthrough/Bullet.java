package com.example.user.breakthrough;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class Bullet {
    public float corX = 100;
    public float corY = 500;
    private float vX = 0;
    private float vY = 0;
    public float velocity = 50;
    public int rad = 4;

    public void move(){
        corX += vX*velocity;
        corY += vY*velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void onDraw(Canvas canvas){
        Log.d("cor", corX + " " + corY);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        canvas.drawCircle(corX, corY, rad, paint);
    }

    void setVec(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        corX = 100;
        corY = 500;
        velocity = 50;
        float l = (float)Math.sqrt((x - corX)*(x - corX) + (y - corY)*(y - corY));
        vX = (x - corX)/l;
        vY = (y - corY)/l;
    }
}
