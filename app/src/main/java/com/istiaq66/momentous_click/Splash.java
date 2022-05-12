package com.istiaq66.momentous_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.istiaq66.momentous_click.Login.Login_Activity;

public class Splash extends AppCompatActivity {


    ImageView Logo;
    private  static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Logo = findViewById(R.id.logo);
        getSupportActionBar().hide();



        //rotate animation Code//
        RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(Animation.ABSOLUTE);
        animation.setDuration(2000);
        Logo.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login_Activity.class);
                startActivity(intent);
            }
        },SPLASH_TIME_OUT);
    }
}