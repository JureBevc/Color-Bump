package com.jure.colorbump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.jure.colorbump.ui.UIManager;
import com.jure.colorbump.util.LevelManager;
import com.jure.colorbump.util.ListManager;

public class Game {


    LevelManager levelManager;
    UIManager uiManager;

    public Game(Context context) {
        levelManager = new LevelManager(context);
        levelManager.loadLevel(0);
        uiManager = new UIManager(levelManager);
    }

    public void update() {

        if (!levelManager.loading)
            ListManager.update();

        uiManager.update();


        levelManager.checkLevel();
    }


    Paint p = new Paint();

    public void render(Canvas canvas) {
        p.setColor(Color.parseColor("#F3E9DC"));
        canvas.drawRect(0, 0, 1280, 720, p);
        if (!levelManager.loading)
            ListManager.render(canvas);
        uiManager.render(canvas);
    }


}
