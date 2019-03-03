package com.example.user.breakthrough;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Block {
    public float corX = 800 + (float)Math.random()*2560;
    public float corY = (float)Math.random()*1422;
    public float width = 40 + (float)Math.random()*60;
    public float length = 5000/width;                        // Фиксированная площадь блока
    public float left = corX - length;
    public float right = corX + length;
    public float top = corY + width;
    public float bottom = corY - width;
    public void onDraw(Canvas canvas){
        Log.d("cor", corX + " " + corY);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawRect(left, top, right, bottom, paint);
    }

}
