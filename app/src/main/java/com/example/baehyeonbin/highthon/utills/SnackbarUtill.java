package com.example.baehyeonbin.highthon.utills;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class SnackbarUtill {
    public static void makeSnackBar(View view, String text, int length) {
        Snackbar.make(view, text, length).show();
    }
}
