package com.jure.colorbump.obj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.jure.colorbump.ui.Cotrols;
import com.jure.colorbump.util.Vec;

public class Ball {

    public Vec pos;
    public float r = 50;
    public boolean isControlled = false;
    public boolean tagged = false;
    public Ball(Vec pos) {
        this.pos = pos;
    }

    private float decay = 0.999f;
    public Vec vel = new Vec(0, 0);

    public void update() {

        if (isControlled) {
            Cotrols.updateControls(this);
            tagged = true;
            color = "#3D5A80";
        }
        pos = pos.add(vel);
        vel = vel.mul(decay);
    }

    Paint p = new Paint();

    public String color = "#AC3931";

    public void render(Canvas c) {
        //Drawing the cricle
        p.setColor(Color.parseColor(color));
        c.drawCircle(pos.x, pos.y, r, p);

        // Drawing the edge
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.parseColor("#37392E"));
        p.setStrokeWidth(5);
        c.drawCircle(pos.x, pos.y, r, p);
        p.setStyle(Paint.Style.FILL_AND_STROKE);

        //Drawing ui for control
        if (isControlled)
            Cotrols.renderControls(this, c);


    }

    public void tagBall(){
        color = "#3D5A80";
        tagged = true;
    }

}
