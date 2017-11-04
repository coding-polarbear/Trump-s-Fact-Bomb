package com.example.baehyeonbin.highthon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.Success;
import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.services.UserService;
import com.example.baehyeonbin.highthon.utills.RetrofitUtil;
import com.example.baehyeonbin.highthon.utills.SnackbarUtill;
import com.example.baehyeonbin.highthon.utills.ToastUtill;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    private File file = null;
    private View view;
    private EditText id;
    private EditText password1;
    private EditText password2;
    private Button signIn;
    private de.hdodenhof.circleimageview.CircleImageView profile;
    private User user;
    private UserService userService;
    private Call<Success> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        setListener();
    }

    private void init() {
        id = findViewById(R.id.id);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        signIn = findViewById(R.id.signIn);
        profile = findViewById(R.id.profile);
        view = getWindow().getDecorView().getRootView();
    }

    private void setListener() {
        signIn.setOnClickListener(v -> {
            if(password1.getText().toString().equals(password2.getText().toString())) {
                user = new User(id.getText().toString(), password1.getText().toString());
                register();
            } else {
                SnackbarUtill.makeSnackBar(view, "패스워드를 확인해주세요!", Snackbar.LENGTH_SHORT);
            }
        });
    }


    public void getImage(View view) {
        image();
    }

    public void image() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Glide.with(this).load(uri).into(profile);
                String filePath = getRealPathFromURIPath(uri, SignUpActivity.this);
                file = new File(filePath);
            } else {
                EasyPermissions.requestPermissions(this,"파일을 읽으려면 권한이 필요합니다",READ_REQUEST_CODE, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null) {
            image();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        SnackbarUtill.makeSnackBar(view, "권한이 없습니다", Snackbar.LENGTH_SHORT);
    }

    private void register() {
        userService = RetrofitUtil.retrofit.create(UserService.class);
        call = userService.register(user, RetrofitUtil.createMultipartBody(file, "profile"));
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                Result status = response.body().getStatus();
                if(status.getSuccess()) {
                    ToastUtill.makeToast(getApplicationContext(),"회원가입이 완료되었습니다!", Toast.LENGTH_LONG);
                    finish();
                } else {
                    SnackbarUtill.makeSnackBar(view,status.getMessage(), Snackbar.LENGTH_SHORT );
                }
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                SnackbarUtill.makeSnackBar(view, "알 수 없는 오류가 발생하였습니다!", Snackbar.LENGTH_SHORT);
            }
        });
    }
}
