package com.example.baehyeonbin.highthon.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baehyeonbin.highthon.R;
import com.example.baehyeonbin.highthon.WriteActivity;

import static android.app.Activity.RESULT_OK;


/**
 * Created by pc on 2017-11-05.
 */

public class My_Request_Fragment extends Fragment {

    FloatingActionButton fab;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.communitymain, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WriteActivity.class);
                startActivityForResult(intent,5555);
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((resultCode == RESULT_OK){
            if(requestCode== 5555){

            }
        }
    }
}
