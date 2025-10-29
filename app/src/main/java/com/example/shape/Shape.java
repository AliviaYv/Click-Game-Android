package com.example.shape;

import android.graphics.RectF;
import android.graphics.PointF;

import com.example.gridclick.R;

public class Shape {
    private RectF mRect;

    Shape() {
        mRect = new RectF();
    }

    Shape(float left, float top, float right, float bottom) {
        mRect = new RectF(left, top, right, bottom);
    }

    RectF getmRect() {
        return mRect;
    }
}
