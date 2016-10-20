package com.jure.colorbump.util;

import android.content.Context;
import android.util.Log;

import com.jure.colorbump.R;
import com.jure.colorbump.obj.Ball;
import com.jure.colorbump.obj.Wall;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class LevelManager {

    private Context context;

    public static int currentLevel = 0;
    public static int[] levelInts = {R.raw.level1, R.raw.level2, R.raw.level3, R.raw.level4, R.raw.level5, R.raw.level6, R.raw.level7};

    public boolean loading = false;
    public LevelManager(Context context) {
        this.context = context;


    }

    int waitTime = 120;
    public int waitTick = 0;

    public void checkLevel() {

        boolean allTagged = true;
        for (Ball b : ListManager.balls) {
            if (!b.tagged) {
                allTagged = false;
                break;
            }
        }
        if (allTagged)
            waitTick++;

        if (waitTick >= waitTime) {
            if (currentLevel + 1 < levelInts.length)
                currentLevel++;
            Log.d("BALL","CHECK LEVEL CALL");

            loadLevel(currentLevel);
            waitTick = 0;
        }
    }

    public void loadLevel(int level) {
        loading = true;
        ListManager.walls.clear();
        ListManager.balls.clear();
        String[] lines = readFile(level);
        for (String s : lines) {
            String[] coo;
            switch (s.charAt(0)) {
                case 'W':
                    s = s.replace("W", "");
                    s = s.replace(" ", "");
                    coo = s.split(",");
                    ListManager.walls.add(new Wall(new Vec(Integer.parseInt(coo[0]), Integer.parseInt(coo[1])), new Vec(Integer.parseInt(coo[2]), Integer.parseInt(coo[3]))));
                    break;
                case 'B':
                    s = s.replace("B", "");
                    s = s.replace(" ", "");
                    coo = s.split(",");
                    ListManager.balls.add(new Ball(new Vec(Integer.parseInt(coo[0]), Integer.parseInt(coo[1]))));
                    break;
            }
        }

        ListManager.balls.get(0).isControlled = true;
        loading = false;
    }

    public String[] readFile(int level) {
        try {
            InputStream inputStream = context.getResources().openRawResource(levelInts[level]);
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                LinkedList<String> strings = new LinkedList<>();

                while ((str = br.readLine()) != null) {
                    strings.add(str);
                }
                inputStream.close();
                return strings.toArray(new String[strings.size()]);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return new String[0];
    }

}
