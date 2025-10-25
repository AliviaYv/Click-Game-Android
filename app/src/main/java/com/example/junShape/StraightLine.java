package com.example.junShape;
import android.graphics.Paint;
import android.graphics.Canvas;

public class StraightLine extends Shape{
    private float endX;
    private float endY;
    private int strokeWidth;

    public StraightLine(float startX, float startY, float endX, float endY, int color, int strokeWidth) {
        super(startX, startY, color);
        this.endX = endX;
        this.endY = endY;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        canvas.drawLine(x, y, endX, endY, paint);
    }
}
