package com.example.baehyeonbin.highthon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baehyeonbin.highthon.R;
import com.example.baehyeonbin.highthon.beans.Comment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017-11-05.
 */

public class List_Adapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<Comment> items = new ArrayList<Comment>();
    private Context context;

    public List_Adapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.items = commentList;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null){
            inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item,viewGroup,false);
        }

        Comment comment = items.get(i);

        ImageView imageView = view.findViewById(R.id.commantimage);
        Glide.with(context).load("http://n0rr.iptime.org:3333/" + comment.getUsername() + "/profile.jpg").into(imageView);

        TextView people = view.findViewById(R.id.people);
        people.setText(comment.getUsername());

        TextView date = view.findViewById(R.id.date);
        date.setText(comment.getCreatedAt());

        TextView commant = view.findViewById(R.id.commant);
        commant.setText(comment.getContent());
        return view;
    }

    public void addItem(String commant,String people,String date){
        Comment comment = new Comment(commant);
        comment.setCreatedAt(date);
        comment.setUsername(people);
        items.add(comment);
    }

}
