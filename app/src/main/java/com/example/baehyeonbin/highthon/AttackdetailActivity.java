package com.example.baehyeonbin.highthon;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by pc on 2017-11-05.
 */

public class AttackdetailActivity extends AppCompatActivity{
    ListView listview;
    LinearLayout attacklayout;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attackdetail);

        listview = (ListView) findViewById(R.id.attack_item);
        attacklayout = (LinearLayout) findViewById(R.id.attack_layout);
    }
}
