package com.jure.colorbump.util;

import android.graphics.Canvas;
import android.util.Log;

import com.jure.colorbump.obj.Ball;
import com.jure.colorbump.obj.Wall;

import java.util.ArrayList;

public class ListManager {

    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Wall> walls = new ArrayList<>();


    public static void update() {

        for (int i = 0; i < balls.size(); i++) {
            Collision.check(balls.get(i));
            balls.get(i).update();
        }
    }

    public static void render(Canvas canvas) {

        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).render(canvas);
        }
        for (int i = 0; i < balls.size(); i++) {

            balls.get(i).render(canvas);
        }
    }

}
