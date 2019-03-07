package com.example.user.breakthrough;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GameField j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        j = new GameField(this);
        setContentView(j);
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    j.update();
                }
            }
        }.start();
    }
}
