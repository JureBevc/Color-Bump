package com.jure.colorbump.obj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.jure.colorbump.util.Vec;

public class Wall {

    public Vec pos, size;

    public Wall(Vec pos, Vec size) {
        this.pos = pos;
        this.size = size;
    }

    Paint p = new Paint();

    public void render(Canvas canvas) {
        p.setColor(Color.parseColor("#37392E"));
        canvas.drawRect(pos.x, pos.y, pos.x + size.x, pos.y + size.y, p);
    }
}
