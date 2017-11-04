package com.example.baehyeonbin.highthon.utills;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class ToastUtill {
    public static void makeToast(Context context, String message, int time) {
        Toast.makeText(context, message, time).show();
    }
}