package com.example.baehyeonbin.highthon;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baehyeonbin.highthon.beans.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            User user = User.last(User.class);
            if(user != null)
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            else
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }, 3000);
    }
}
