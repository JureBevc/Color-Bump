package com.jure.colorbump.ui;

import android.graphics.Canvas;

import com.jure.colorbump.util.Image;
import com.jure.colorbump.util.Input;
import com.jure.colorbump.util.LevelManager;
import com.jure.colorbump.util.Vec;

import java.util.ArrayList;

public class UIManager {

    public ArrayList<Button> buttons = new ArrayList<>();

    private LevelManager levelManager;

    public UIManager(LevelManager levelManager) {
        this.levelManager = levelManager;
        buttons.add(new Button(new Vec(0, 0), new Vec(100, 100), ButtonAction.RESET_LEVEL, Image.restart));
    }

    public void doAciton(ButtonAction action) {
        switch (action) {
            case RESET_LEVEL:
                if (levelManager.waitTick == 0)
                    levelManager.loadLevel(LevelManager.currentLevel);
                break;
        }
    }

    private int cooldown = 10;
    private int cdTick = 0;

    public void update() {
        if (cdTick < cooldown)
            cdTick++;
        if (cdTick >= cooldown) {
            for (Button b : buttons) {
                if (Input.down && Input.x > b.pos.x && Input.x < b.pos.x + b.size.x
                        && Input.y > b.pos.y && Input.y < b.pos.y + b.size.y) {
                    doAciton(b.action);
                    cdTick = 0;
                }
            }
        }
    }

    public void render(Canvas canvas) {
        for (Button b : buttons) {
            b.render(canvas);
        }
    }
}
