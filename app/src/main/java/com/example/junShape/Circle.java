package com.example.junShape;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {
    private float radius;

    public Circle(float x, float y, float radius, int color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }
}
