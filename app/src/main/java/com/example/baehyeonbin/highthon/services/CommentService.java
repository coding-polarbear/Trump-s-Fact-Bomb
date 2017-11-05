package com.example.baehyeonbin.highthon.services;

import com.example.baehyeonbin.highthon.beans.CommentListGet;
import com.example.baehyeonbin.highthon.beans.Success;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public interface CommentService {
    @POST("/comments/{idx}")
    Call<Success> createComments(@Path("idx") int postIdx);

    @GET("/comments/{idx}")
    Call<CommentListGet> getComments(@Path("idx") int postIdx);
}
