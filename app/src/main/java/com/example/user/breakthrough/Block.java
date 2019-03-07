package com.example.user.breakthrough;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Block {
    public float corX = 2000;
    public float corY = 700;
    public void onDraw(Canvas canvas){
        Log.d("cor", corX + " " + corY);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawRect(corX - 50, corY + 100, corX + 50, corY - 100, paint);
    }

}
