package com.example.baehyeonbin.highthon.services;

import com.example.baehyeonbin.highthon.beans.Comment;
import com.example.baehyeonbin.highthon.beans.CommentListGet;
import com.example.baehyeonbin.highthon.beans.Success;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public interface CommentService {
    @POST("/comments/{idx}")
    @Multipart
    Call<Success> createComments(@Path("idx") int postIdx, @Part("data")Comment comment);

    @GET("/comments/{idx}")
    Call<CommentListGet> getComments(@Path("idx") int postIdx);
}
