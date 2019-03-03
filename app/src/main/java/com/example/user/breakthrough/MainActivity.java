package com.example.user.breakthrough;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GameField j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        j = new GameField(this);
        final TextView ammo = findViewById(R.id.ammo);
        Button reload = findViewById(R.id.reload);
        reload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j.Reload();
            }
        });
        Button dash = findViewById(R.id.dash);
        dash.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    j.Dash();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        setContentView(j);
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    j.update();
                    ammo.setText(j.k + "/ 7");
                }
            }
        }.start();
    }
}
