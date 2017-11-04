package com.example.baehyeonbin.highthon.services;

import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.beans.UserGet;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public interface UserService {
    @POST("/sign")
    @Multipart
    Call<UserGet> login(@Part("data") User user);

    @POST("/user")
    @Multipart
    Call<Result> register(@Part("data") User user, @Part("profile") MultipartBody.Part profile);
}
