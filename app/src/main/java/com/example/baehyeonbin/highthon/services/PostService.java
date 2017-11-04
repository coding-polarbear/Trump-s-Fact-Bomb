package com.example.baehyeonbin.highthon.services;

import com.example.baehyeonbin.highthon.beans.Post;
import com.example.baehyeonbin.highthon.beans.PostGet;
import com.example.baehyeonbin.highthon.beans.PostListGet;
import com.example.baehyeonbin.highthon.beans.Success;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public interface PostService {
    @POST("/posts")
    @Multipart
    Call<Success> write(@Part("data") Post post);

    @GET("/posts")
    Call<PostListGet> getPostAll();

    @GET("/posts")
    Call<PostListGet> getPostWithUserName(@Query("username") String username);

    @GET("/posts/{idx}")
    Call<PostGet> getOnePost(@Path("idx") int postIdx);

    @PUT("/posts/{idx}/{isOpen}")
    Call<Success> changeIsOpenValue(@Path("idx") int postIdx, @Path("isOpen") boolean isOpen);


}
