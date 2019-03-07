package com.example.user.breakthrough;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Enemy {
    public float corX = (float) (Math.random()*2560);
    public float corY = (float) (Math.random()*1422);
    private float velocity = 0;
    public int rad = 50;

    public void move(){
        if(corX < 0){
            corX = 2560;
            corY = (float) (Math.random()*1422);
        }
        corX -= velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void onDraw(Canvas canvas){
        Log.d("cor", corX + " " + corY);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawCircle(corX, corY,40, paint);
    }
}
