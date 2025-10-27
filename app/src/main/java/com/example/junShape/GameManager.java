package com.example.junShape;

import com.example.junShape.Circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameManager extends SurfaceView implements Runnable {
    private int mScreenX;
    private int mScreenY;
    private SurfaceHolder mOurHolder; // object holds the drawing surface.
    private Canvas mCanvas;
    private Paint mPaint;
    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused = true;
    public GameManager(Context context, int x, int y) {
        super(context);

        mScreenX = x;
        mScreenY = y;

        mOurHolder = getHolder(); // getHolder() is a method of SurfaceView
//        mPaint = new Paint();
    }

    private void update() {
        Log.i("debug: ", "update");
    }
    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            // Fill the screen with a solid color
            mCanvas.drawColor(Color.argb(255, 255, 255, 0));
            // Choose a color to paint with

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);

            Robot robot_1 = new Robot(200, 300, 5);
            robot_1.draw(mCanvas, paint);
            Robot robot_2 = new Robot(500, 300, 5);
            robot_2.draw(mCanvas, paint);

            Robot robot_3 = new Robot(200, 1000, 2);
            robot_3.draw(mCanvas, paint);
            Robot robot_4 = new Robot(500, 1000, 4);
            robot_4.draw(mCanvas, paint);

            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void run() {
        while(mPlaying) {
//            long frameStartTime = System.currentTimeMillis();
            draw();
//            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
        }
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join(); // Stop the thread
        } catch (InterruptedException e) {
            Log.e("Error: ", "Joining thread");
        }
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }
}
