package com.example.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Circle extends Shape{
    public Circle(float x, float y, float radius) {
        super(x - radius, y - radius, x + radius, y + radius);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 250, 0, 0));

        RectF rect = getmRect();
        canvas.translate(rect.left, rect.top);
        Float x = (rect.left + rect.right) / 2;
        Float y = (rect.top + rect.bottom) / 2;
        canvas.drawCircle(x, y, 40, paint);
    }
}
