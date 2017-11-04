package com.example.baehyeonbin.highthon.utills;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class DrawableFileUtill {
    public static File getDrawableResource(int id, String name, Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),id);  //drawable 리소스에서 비트맵 만들기
        File storage = context.getCacheDir(); //임시파일 저장위치
        String fileName = name + ".png";    //확장자명 : png
        File tempFile = new File(storage, fileName);
        try {
            tempFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}