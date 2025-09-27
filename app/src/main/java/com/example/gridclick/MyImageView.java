package com.example.gridclick;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {
    int horizontalPixels;
    int verticalPixels;
    int gridWidth = 40; // 固定横向多少格
    int gridHeight;
    int blockSize;
    Bitmap blankBitmap;
    Canvas bufferCanvas;

    float currentX = -1f;
    float currentY = -1f;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 当 View 大小确定时初始化
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        horizontalPixels = w;
        verticalPixels = h;

        blockSize = horizontalPixels / gridWidth;
        gridHeight = verticalPixels / blockSize;

        // 创建缓存 bitmap 和 canvas
        blankBitmap = Bitmap.createBitmap(horizontalPixels, verticalPixels, Bitmap.Config.ARGB_8888);
        bufferCanvas = new Canvas(blankBitmap);

        Log.d("MyImageView", "onSizeChanged: width=" + w + " height=" + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (blankBitmap == null) return;

        // 每次重绘都先清空
        bufferCanvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);

        // 画网格
        for (int i = 0; i <= gridWidth; i++) {
            int x = i * blockSize;
            bufferCanvas.drawLine(x, 0, x, verticalPixels, paint);
        }
        for (int j = 0; j <= gridHeight; j++) {
            int y = j * blockSize;
            bufferCanvas.drawLine(0, y, horizontalPixels, y, paint);
        }

        // 点击时画圆
        if (this.currentX >= 0 && this.currentY >= 0) {
            bufferCanvas.drawCircle(this.currentX, this.currentY, 50, paint);
        }

        // 绘制到屏幕
        canvas.drawBitmap(blankBitmap, 0, 0, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(android.view.MotionEvent e) {
        this.currentX = e.getX();
        this.currentY = e.getY();

        Log.d("MyImageView", String.format("x=%.2f, y=%.2f", this.currentX, this.currentY));

        invalidate(); // 通知重绘
        return true;  // 告诉系统我消费了事件
    }
}

