package com.example.baehyeonbin.highthon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baehyeonbin.highthon.R;
import com.example.baehyeonbin.highthon.beans.Post;

import java.util.List;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Post> postList;
    private Context context;
    public RecyclerAdapter(Context context, List<Post> postList)  {
        this.context = context;
        this.postList = postList;
    }
    private View view;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_cardviewing, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = postList.get(position);
        Glide.with(context).load("http://n0rr.iptime.org:3333/" + postList.get(position).getUsername() + "/profile.jpg").into(holder.profile);
        holder.titleText.setText(post.getTitle());
        holder.username.setText(post.getUsername());
        holder.date.setText(post.getCreatedAt());
        holder.content.setText(post.getContent().substring(0,10) + "...");
        holder.count.setText(post.getCommentNum()+"발의 폭격");
        if(post.isOpen()) {
            holder.subView.setBackground(context.getDrawable(R.drawable.background_attacking));
            holder.status.setText("폭격 진행중");
        } else {
            holder.subView.setBackground(context.getDrawable(R.drawable.background_attackingstop));
            holder.status.setText("폭격 중지");
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profile;
        public TextView titleText;
        public TextView date;
        public TextView content;
        public TextView count;
        public TextView status;
        public TextView username;
        public RelativeLayout subView;
        public ViewHolder(View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.profile);
            titleText = itemView.findViewById(R.id.titleText);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            count = itemView.findViewById(R.id.count);
            status = itemView.findViewById(R.id.status);
            subView = itemView.findViewById(R.id.subView);
            username = itemView.findViewById(R.id.username);
        }
    }
}
