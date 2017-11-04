package com.example.baehyeonbin.highthon.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baehyeonbin.highthon.Adapter.RecyclerAdapter;
import com.example.baehyeonbin.highthon.R;
import com.example.baehyeonbin.highthon.WriteActivity;
import com.example.baehyeonbin.highthon.beans.Post;
import com.example.baehyeonbin.highthon.beans.PostListGet;
import com.example.baehyeonbin.highthon.beans.Result;
import com.example.baehyeonbin.highthon.beans.Success;
import com.example.baehyeonbin.highthon.beans.User;
import com.example.baehyeonbin.highthon.services.PostService;
import com.example.baehyeonbin.highthon.utills.RetrofitUtil;
import com.example.baehyeonbin.highthon.utills.SnackbarUtill;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by pc on 2017-11-05.
 */

public class My_Request_Fragment extends Fragment {

    FloatingActionButton fab;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList = null;
    private PostService postService = null;
    private Call<PostListGet> call = null;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.communitymain, container, false);
        init();
        loadData();
        return view;
    }

    private void init() {
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WriteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        postService = RetrofitUtil.getLoginRetrofit().create(PostService.class);
        user = User.last(User.class);
        Log.d("test", user.toString());
        call = postService.getPostWithUserName(user.getUserName());
        call.enqueue(new Callback<PostListGet>() {
            @Override
            public void onResponse(Call<PostListGet> call, Response<PostListGet> response) {
                Result result = response.body().getStatus();
                Log.d("test", result.toString());
                if(result.getSuccess()) {
                    postList = response.body().getPosts();
                    setRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<PostListGet> call, Throwable t) {
                SnackbarUtill.makeSnackBar(view, "알 수 없는 오류가 발생하였습니다.", Snackbar.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),postList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
