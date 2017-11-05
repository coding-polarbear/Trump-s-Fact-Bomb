package com.example.baehyeonbin.highthon;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.baehyeonbin.highthon.beans.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            User user = User.last(User.class);
            if(user != null) {
                Toast.makeText(getApplicationContext(), "자동로그인이 되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }else
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }, 3000);



    }
}





