package com.jure.colorbump.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.jure.colorbump.obj.Ball;
import com.jure.colorbump.util.Input;
import com.jure.colorbump.util.Vec;

public class Cotrols {

    private static boolean pressed = false;

    public static void updateControls(Ball b) {
        Vec vel;

        if (Input.down && b.pos.neg().add(new Vec(Input.x, Input.y)).length() < b.r + 20)
            pressed = true;

        if (!Input.down && pressed) {

            vel = b.pos.neg().add(new Vec(Input.x, Input.y)).norm().mul(10);
            Log.d("TAG", "v = " + vel.length());
            b.vel = vel;
            b.isControlled = false;
            pressed = false;
        }

    }

    private static Paint p = new Paint();

    public static void renderControls(Ball b, Canvas canvas) {
        if (Input.down && pressed) {
            p.setColor(Color.parseColor("#AC3931"));
            p.setStrokeWidth(10);
            Vec max = b.pos.neg().add(new Vec(Input.x, Input.y)).norm().mul(200);
            //canvas.drawLine(b.pos.x, b.pos.y, b.pos.x + max.x, b.pos.y + max.y, p);
        }
    }
}
