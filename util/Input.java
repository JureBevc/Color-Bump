package com.jure.colorbump.util;

import android.view.MotionEvent;

import com.jure.colorbump.Main;

public class Input {

    public static float x = 0, y = 0;
    public static boolean down = false;
    public static boolean tap = false;

    public void processEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX() / Main.scaleX;
                y = event.getY() / Main.scaleY;
                down = true;
                tap = true;
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX() / Main.scaleX;
                y = event.getY() / Main.scaleY;
                down = true;
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX() / Main.scaleX;
                y = event.getY() / Main.scaleY;
                down = false;
                break;
        }

    }
}
