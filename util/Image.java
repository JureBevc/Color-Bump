package com.jure.colorbump.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jure.colorbump.R;

public class Image {

    public static Bitmap restart, playButton;
    public Image(Context context){
        restart = BitmapFactory.decodeResource(context.getResources(), R.drawable.restart);
        playButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.playButton);
    }
}
