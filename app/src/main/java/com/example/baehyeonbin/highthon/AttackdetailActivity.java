package com.example.baehyeonbin.highthon;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baehyeonbin.highthon.Adapter.List_Adapter;
import com.example.baehyeonbin.highthon.beans.Comment;
import com.example.baehyeonbin.highthon.beans.CommentListGet;
import com.example.baehyeonbin.highthon.beans.Post;
import com.example.baehyeonbin.highthon.beans.PostGet;
import com.example.baehyeonbin.highthon.beans.PostListGet;
import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.Success;
import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.services.CommentService;
import com.example.baehyeonbin.highthon.services.PostService;
import com.example.baehyeonbin.highthon.utills.RetrofitUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 2017-11-05.
 */

public class AttackdetailActivity extends AppCompatActivity{
    ListView listview;
    LinearLayout attacklayout;
    private CircleImageView image;
    private TextView title;
    private TextView date;
    private TextView fact;
    private TextView content;
    private TextView count;
    private EditText editComment;
    private ImageView commentBtn;

    private User user;
    private PostService postService;
    private CommentService commentService;
    private int postIdx;
    private Call<PostGet> call;
    private Call<Success> call2;
    private Call<CommentListGet> call3;
    private List<Comment> commentList;
    private Post post;
    private List_Adapter list_adapter;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attackdetail);
        init();
        loadFactData();
    }

    private void init() {
        listview = (ListView) findViewById(R.id.attack_item);
        attacklayout = (LinearLayout) findViewById(R.id.attack_layout);
        image = (CircleImageView) findViewById(R.id.image);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        fact = findViewById(R.id.fact);
        content = findViewById(R.id.content);
        count = findViewById(R.id.count);
        editComment = findViewById(R.id.editComment);
        commentBtn = findViewById(R.id.commentBtn);
        postIdx = getIntent().getExtras().getInt("postIdx");
    }

    private void loadFactData() {
        postService = RetrofitUtil.retrofit.create(PostService.class);
        call = postService.getOnePost(postIdx);
        call.enqueue(new Callback<PostGet>() {
            @Override
            public void onResponse(Call<PostGet> call, Response<PostGet> response) {
                Result status = response.body().getStatus();
                if(status.getSuccess()) {
                    post = response.body().getPost();
                    Glide.with(getApplicationContext()).load("http://n0rr.iptime.org:3333/"+post.getUsername()+"/profile.jpg").into(image);
                    title.setText(post.getUsername());
                    date.setText(post.getCreatedAt());
                    fact.setText(post.getTitle());
                    content.setText(post.getContent());
                    count.setText(post.getCommentNum() + "발의 폭격");
                    loadCommentData();
                }
            }

            @Override
            public void onFailure(Call<PostGet> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    private void loadCommentData() {
        commentService = RetrofitUtil.getLoginRetrofit().create(CommentService.class);
        call3 = commentService.getComments(postIdx);
        call3.enqueue(new Callback<CommentListGet>() {
            @Override
            public void onResponse(Call<CommentListGet> call, Response<CommentListGet> response) {
                Result status = response.body().getStatus();
                if(status.getSuccess()) {
                    commentList = response.body().getComments();
                    setListview();
                } else
                    Log.d("status", status.toString());
            }

            @Override
            public void onFailure(Call<CommentListGet> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }
    private void setListview() {
        list_adapter = new List_Adapter(getApplicationContext(), commentList);
        listview.setAdapter(list_adapter);
        ((BaseAdapter) listview.getAdapter()).notifyDataSetChanged();

    }
    public void addComment(View view) {
        String content = editComment.getText().toString();
        Log.d("content", content);
        Comment comment1 = new Comment(content);
        comment1.setPostIdx(postIdx);
        commentService = RetrofitUtil.getLoginRetrofit().create(CommentService.class);
        call2 = commentService.createComments(postIdx, comment1);
        call2.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                Result status = response.body().getStatus();
                if(status.getSuccess()) {
                    Snackbar.make(view, status.getMessage(), Snackbar.LENGTH_SHORT);
                    loadCommentData();
                    editComment.setText("");
                } else
                    Log.d("status", status.toString());
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                Snackbar.make(view, "알 수 없는 오류가 발생하였습니다!", Snackbar.LENGTH_SHORT);
            }
        });
    }
}
