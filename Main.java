package com.jure.colorbump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.jure.colorbump.util.Image;
import com.jure.colorbump.util.Input;
import com.jure.colorbump.util.LevelManager;

public class Main extends View {

    //public SurfaceHolder holder;
    public static boolean running = false;
    public static int WIDTH, HEIGHT;
    public static float scaleX, scaleY;

    public Game game;
    Image image;
    Input input;

    public static int threads = 0;

    public Main(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            display.getRealSize(size);
        else
            display.getSize(size);
        WIDTH = size.x;
        HEIGHT = size.y;
        scaleX = WIDTH / 1280.0f;
        scaleY = HEIGHT / 720.0f;
        //
        running = true;
        //holder = getHolder();
        input = new Input();
        image = new Image(context);
        game = new Game(context);

        if (threads == 0) {
            threads++;
            mainThread.start();
        }
    }

    int frames = 0;
    public Thread mainThread = new Thread() {

        public void run() {

            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();
            final double ns = 1000000000.0 / 60.0;
            double delta = 0;

            int updates = 0;
            while (running) {
                long now = System.nanoTime();

                delta += (now - lastTime) / ns;

                try {
                    Thread.sleep(2);
                    Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //if (!holder.getSurface().isValid())
                //   continue;


                lastTime = now;
                while (delta >= 1) {
                    updates++;
                    delta--;

                    update();
                }

                /*Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    //canvas.drawRGB(0, 0, 0);

                    //render(canvas);
                    //holder.unlockCanvasAndPost(canvas);
                    //frames++;
                    */
                if (System.currentTimeMillis() - timer > 1000) {
                    //Log.d("Running ", "fps: " + frames + "| ups:" + updates + "  -- " + mainThread.getId());
                    timer += 1000;
                    updates = 0;
                    frames = 0;
                }

            }

        }


    };

    public boolean onTouchEvent(MotionEvent event) {
        input.processEvent(event);
        return true;
    }

    public void update() {
        game.update();
        Input.tap = false;
    }

    Paint p = new Paint();

    public void render(Canvas canvas) {
        canvas.scale(scaleX, scaleY);
        game.render(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        frames++;
        render(canvas);
        invalidate();
    }
}
