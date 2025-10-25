package com.example.junShape;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Rectangle extends Shape{
    private float width;
    private float height;

    public Rectangle(float x, float y, float width, float height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawRect(x, y, (x + width), y + height, paint);
    }
}
