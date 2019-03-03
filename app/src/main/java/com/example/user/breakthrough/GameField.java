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
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GameField extends View {

    Hero hero = new Hero();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Block> blocks = new ArrayList<>();
    public int k = bullets.size();                             // Сколько пуль осталось

    public GameField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameField(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).onDraw(canvas);
        }
        for(int i = 0; i < 7; i++){
            bullets.get(i).onDraw(canvas);
        }
        for(int i = 0; i < blocks.size(); i++){
            blocks.get(i).onDraw(canvas);
        }
        hero.onDraw(canvas);
        invalidate();
    }

    public void update(){
        if(hero.corX > 600){                          // После 600 камера крепится на перса
            hero.corX = 600;
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).corX -= hero.vecX;
                enemies.get(i).corY -= hero.vecY;
            }
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).corX -= hero.vecX;
                bullets.get(i).corY -= hero.vecY;
            }
        }
        for(int i = 0; i < blocks.size(); i++){                   // Если перс врезается в блок
            if((hero.corX + hero.rad > blocks.get(i).left &&
                    hero.corX + hero.rad < blocks.get(i).right) ||
                    (hero.corX - hero.rad > blocks.get(i).left &&
                            hero.corX - hero.rad < blocks.get(i).right)){
                hero.vecX = 0;
            }
            if((hero.corY + hero.rad > blocks.get(i).top &&
                    hero.corY + hero.rad > blocks.get(i).bottom) ||
                    (hero.corY - hero.rad > blocks.get(i).top &&
                            hero.corY - hero.rad > blocks.get(i).bottom)){
                hero.vecY = 0;
            }
        }
        hero.move();
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).corX > 2560){
                enemies.remove(i);
            }
            enemies.get(i).move();
        }
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).corX > 2560){
                bullets.remove(i);
            }
            bullets.get(i).move();
            if(intersec(bullets.get(i))){
                bullets.remove(i);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {                // Выстрел(тык по экрану)
        if(k == 0){
            return false;
        }
        bullets.get(k - 1).setVec(event);
        k--;
        Log.d("vec", event.getX() + " " + event.getY());
        return super.onTouchEvent(event);
    }

    public void Reload() {                     // Перезарядка(на кнопку)
        bullets.clear();
        for (int i = 0; i < 7; i++){
            bullets.add(new Bullet());
        }
        k = bullets.size();
    }

    public void Dash() throws InterruptedException {       // Перекат(на кнопку)
        hero.vecX *= 2;
        hero.vecY *= 2;
        Thread.sleep(1000);
        hero.vecX /= 2;
        hero.vecY /= 2;
    }

    public boolean intersec(Bullet bullet){
        for(int i = 0; i < blocks.size(); i++){             // Если пуля ударилась в блок
            if(bullet.corX > blocks.get(i).left &&
                    bullet.corX < blocks.get(i).right &&
                    bullet.corY > blocks.get(i).top &&
                    bullet.corY < blocks.get(i).bottom){
                bullet.corX = 0;
                bullet.corY = 0;
                bullet.velocity = 0;
            }
        }
        for(int i = 0; i < enemies.size(); i++){            // Если пуля попала во врага
            if((bullet.rad + enemies.get(i).rad) < Math.sqrt((bullet.corX - enemies.get(i).corX)*
                    (bullet.corX - enemies.get(i).corX) + (bullet.corY - enemies.get(i).corY)*
                    (bullet.corY - enemies.get(i).corY))){
                bullet.corX = 0;
                bullet.corY = 0;
                bullet.velocity = 0;
                enemies.get(i).corX = 2560;
                enemies.get(i).corY = (float) (Math.random()*1422);
                return true;
            }
        }
        return false;
    }
}
