
package com.example.baehyeonbin.highthon;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baehyeonbin.highthon.beans.Post;
import com.example.baehyeonbin.highthon.beans.PostGet;
import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.Success;
import com.example.baehyeonbin.highthon.services.PostService;
import com.example.baehyeonbin.highthon.utills.RetrofitUtil;
import com.example.baehyeonbin.highthon.utills.SnackbarUtill;
import com.example.baehyeonbin.highthon.utills.ToastUtill;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class WriteActivity extends AppCompatActivity {
        private EditText title;
        private EditText content;
        private Button write;
        private PostService postService;
        private Call<Success> call;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_write);
            init();
            setListener();
        }

        private void init() {
            title = findViewById(R.id.title);
            content = findViewById(R.id.content);
            write = findViewById(R.id.write);
        }

        private void setListener() {
            write.setOnClickListener(view -> {
                write();
            });
        }

        private void write() {
            postService = RetrofitUtil.getLoginRetrofit().create(PostService.class);
            Post post = new Post(title.getText().toString());
            post.setContent(content.getText().toString());
            call = postService.write(post);
            call.enqueue(new Callback<Success>() {
                @Override
                public void onResponse(Call<Success> call, Response<Success> response) {
                    Result result = response.body().getStatus();
                    if(result.getSuccess()) {
                        ToastUtill.makeToast(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT);
                        finish();
                    } else
                        SnackbarUtill.makeSnackBar(getWindow().getDecorView().getRootView(), result.getMessage(), Snackbar.LENGTH_SHORT);
                }

                @Override
                public void onFailure(Call<Success> call, Throwable t) {
                    SnackbarUtill.makeSnackBar(getWindow().getDecorView().getRootView(), "알 수 없는 오류가 발생하였습니다.", Snackbar.LENGTH_SHORT);
                }
            });
        }
    }
