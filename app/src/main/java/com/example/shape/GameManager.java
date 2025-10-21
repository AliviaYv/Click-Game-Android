package com.example.shape;

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
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));
            // Choose a color to paint with
            Circle circle_1 = new Circle(100, 200, 50);
            circle_1.draw(mCanvas);

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
