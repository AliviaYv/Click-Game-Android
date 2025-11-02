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
    private float dx = 5; // 每帧x方向速度
    private float dy = 3;

    private float dAngle = 100;
    Robot robot_1 = new Robot(500, 500, 4);
    public GameManager(Context context, int x, int y) {
        super(context);

        mScreenX = x;
        mScreenY = y;

        mOurHolder = getHolder(); // getHolder() is a method of SurfaceView
        mPaint = new Paint();
    }

    private void update() {
        robot_1.update(dx, dy, 0);
        float rx = robot_1.getX();
        float ry = robot_1.getY();

        if(dx > 0){
            robot_1.setFacingRight(true);
        } else {
            robot_1.setFacingRight(false);
        }

        // 假设机器人宽高大约 100*100 px，可根据 scale 调整
        float robotWidth = 100f;
        float robotHeight = 100f;

        // 碰到左右边界掉头
        if(rx - robotWidth/2 < 0 || rx + robotWidth/2 > mScreenX){
            dx = -dx;       // x 方向反向
            robot_1.setAngle(robot_1.getAngle() + 180); // 转身
        }

        // 碰到上下边界掉头
        if(ry - robotHeight/2 < 0 || ry + robotHeight/2 > mScreenY){
            dy = -dy;       // y 方向反向
            robot_1.setAngle(robot_1.getAngle() + 180); // 转身
        }
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
            robot_1.draw(mCanvas, paint);



            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void run(){
        while(mPlaying){
            update();
            draw();
            try{ Thread.sleep(16); } catch(Exception e){}
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

