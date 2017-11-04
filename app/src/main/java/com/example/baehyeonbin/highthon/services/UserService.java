package com.example.baehyeonbin.highthon.services;

import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.beans.UserGet;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public interface UserService {
    @POST("/user/login")
    Call<UserGet> login(@Part("data") User user);
}
