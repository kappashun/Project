package com.example.user.breakthrough;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameField extends View {

    Hero hero = new Hero();
    Enemy enemy = new Enemy();
    ArrayList<Bullet> bullets = new ArrayList<>();

    public GameField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameField(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        enemy.onDraw(canvas);
        hero.onDraw(canvas);
        invalidate();
    }

    public void update(){
        if(hero.corX > 600){
            hero.corX = 600;
            enemy.corX -= hero.vecX;
            enemy.corY -= hero.vecY;
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).corX -= hero.vecX;
                bullets.get(i).corY -= hero.vecY;
            }
        }
        hero.move();
        enemy.move();
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).corX > 2560){
                bullets.remove(i);
            }
            bullets.get(i).move();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bullets.add(new Bullet());
        bullets.get(bullets.size() - 1).onDraw();
        bullets.get(bullets.size() - 1).setVec(event);
        Log.d("vec", event.getX() + " " + event.getY());
        return super.onTouchEvent(event);
    }

    public void Intersec(){
        for(int i = 0; i < bullets.size(); i++){
            if((bullets.get(i).rad + enemy.rad) < Math.sqrt((bullets.get(i).corX - enemy.corX)*
                    (bullets.get(i).corX - enemy.corX) + (bullets.get(i).corY - enemy.corY)*
                    (bullets.get(i).corY - enemy.corY))){
                bullets.remove(i);
                enemy.corX = 2560;
                enemy.corY = (float) (Math.random()*1422);
            }
        }
    }
}
