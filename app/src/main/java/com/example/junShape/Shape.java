package com.example.junShape;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Shape {
    protected float x;
    protected  float y;
    protected  int color;

    public Shape(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }
}



