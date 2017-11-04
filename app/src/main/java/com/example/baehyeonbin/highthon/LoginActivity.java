package com.example.baehyeonbin.highthon;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.Token;
import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.beans.UserGet;
import com.example.baehyeonbin.highthon.services.UserService;
import com.example.baehyeonbin.highthon.utills.RetrofitUtil;
import com.example.baehyeonbin.highthon.utills.SnackbarUtill;
import com.example.baehyeonbin.highthon.utills.ToastUtill;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText id;
    private EditText password;
    private Button login;
    private TextView noUser = null;
    private Call<UserGet> call;
    private UserService userService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setListeners();
    }

    private void init() {
        noUser = findViewById(R.id.noUser);
        id = findViewById(R.id.id);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        noUser.setText(Html.fromHtml("<u>아직 회원이 아니신가요?</u>"));

    }

    private void setListeners() {
        noUser.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
        login.setOnClickListener(v -> {
            user = new User(id.getText().toString(), password.getText().toString());
            login();
        });
    }

    private void login() {
        Log.d("start", "start");
        userService = RetrofitUtil.retrofit.create(UserService.class);
        call = userService.login(user);
        call.enqueue(new Callback<UserGet>() {
            @Override
            public void onResponse(Call<UserGet> call, Response<UserGet> response) {
                Result status = response.body().getStatus();
                if(status.getSuccess()) {
                    User userResponse = response.body().getUser();
                    userResponse.save();
                    Token tokenResponse = response.body().getToken();
                    tokenResponse.save();
                    ToastUtill.makeToast(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    SnackbarUtill.makeSnackBar(getWindow().getDecorView().getRootView(), status.getMessage(), Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<UserGet> call, Throwable t) {
                SnackbarUtill.makeSnackBar(getWindow().getDecorView().getRootView(), "알 수 없는 오류가 발생하였습니다", Snackbar.LENGTH_LONG);
                Log.d("test",t.getMessage());
            }
        });
    }
}
