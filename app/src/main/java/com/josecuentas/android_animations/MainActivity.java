package com.josecuentas.android_animations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Animation2Activity.class));
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });



    }
}
