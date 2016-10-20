package com.jure.colorbump.ui;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jure.colorbump.util.Vec;

public class Button {

    public Vec pos, size;
    public ButtonAction action;
    private Bitmap image;

    public Button(Vec pos, Vec size, ButtonAction action, Bitmap image) {
        this.pos = pos;
        this.size = size;
        this.action = action;
        this.image = Bitmap.createScaledBitmap(image, (int) size.x, (int) size.y, false);
    }

    Paint p = new Paint();

    public void render(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, p);
    }
}
