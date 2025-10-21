package com.example.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle {
    private float mX;
    private float mY;
    private float mRadius;
    private Paint mPaint;

    public Circle(float x, float y, float radius) {
        this.mX = x;
        this.mY = y;
        this.mRadius = radius;
        mPaint = new Paint();
    }

    public void draw(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 250, 0, 0));
        canvas.drawCircle(mY, mY, mRadius, mPaint);
    }
}
