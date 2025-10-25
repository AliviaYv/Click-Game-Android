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
            mCanvas.drawColor(Color.argb(200, 100, 128, 0));
            // Choose a color to paint with

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);

            for (int i = 100; i < 900; i += 100) {
                com.example.junShape.Circle circle_1 = new Circle(i, i * 2, 100, Color.RED * (10 * i));
                circle_1.draw(mCanvas, paint);
            }

            for (int i = 100; i < 900; i += 100) {
                com.example.junShape.Rectangle rectangle_1 = new Rectangle(i - 50, i * 2, 100, 200, Color.BLUE * (i / 2));
                rectangle_1.draw(mCanvas, paint);
            }

            for (int i = 100; i < 900; i += 100) {
                com.example.junShape.StraightLine left_leg = new StraightLine(i - 100, i * 2, i - 100, i* 3, Color.BLUE * (i / 2), 5);
                left_leg.draw(mCanvas, paint);
            }


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
