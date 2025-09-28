package com.example.gridclick;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {
    int horizontalPixels;
    int verticalPixels;
    int gridWidth = 40; // 列数（横向格子数）
    int gridHeight;     // 行数（纵向格子数，计算得出）
    int blockSize;
    Bitmap blankBitmap;
    Canvas bufferCanvas;

    // 存储每个格子的填充颜色，0 表示空（未填充）
    int[][] cellColors;

    // 默认点击后填充颜色（你可以在代码里改）
    private int fillColor = Color.parseColor("#3F51B5");

    // 网格和填充画笔
    private final Paint gridPaint = new Paint();
    private final Paint fillPaint = new Paint();

    public MyImageView(Context context) {
        super(context);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        gridPaint.setColor(Color.BLACK);
        gridPaint.setStrokeWidth(2);
        gridPaint.setStyle(Paint.Style.STROKE);

        fillPaint.setStyle(Paint.Style.FILL);
        // fillPaint 的颜色在绘制时由 cellColors 决定
        setClickable(true);
    }

    // 当 View 大小确定时初始化
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        horizontalPixels = w;
        verticalPixels = h;

        // 计算每个格子的像素大小，向下取整
        blockSize = Math.max(1, horizontalPixels / gridWidth);
        gridHeight = Math.max(1, verticalPixels / blockSize);

        // 创建缓存 bitmap 和 canvas
        blankBitmap = Bitmap.createBitmap(horizontalPixels, verticalPixels, Bitmap.Config.ARGB_8888);
        bufferCanvas = new Canvas(blankBitmap);

        // 初始化格子颜色数组（row x col）
        cellColors = new int[gridHeight][gridWidth];
        for (int r = 0; r < gridHeight; r++) {
            for (int c = 0; c < gridWidth; c++) {
                cellColors[r][c] = 0; // 0 表示未填充
            }
        }

        Log.d("MyImageView", "onSizeChanged: width=" + w + " height=" + h +
                " blockSize=" + blockSize + " gridHeight=" + gridHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (blankBitmap == null || bufferCanvas == null) return;

        // 清空缓存画布为白底
        bufferCanvas.drawColor(Color.WHITE);

        // 先画已填充的格子（这样网格线会在格子上方画出边界）
        for (int r = 0; r < gridHeight; r++) {
            for (int c = 0; c < gridWidth; c++) {
                int color = cellColors[r][c];
                if (color != 0) {
                    fillPaint.setColor(color);
                    int left = c * blockSize;
                    int top = r * blockSize;
                    int right = left + blockSize;
                    int bottom = top + blockSize;
                    // 防止越界（最后一列/行可能略超出）
                    if (right > horizontalPixels) right = horizontalPixels;
                    if (bottom > verticalPixels) bottom = verticalPixels;
                    bufferCanvas.drawRect(left, top, right, bottom, fillPaint);
                    bufferCanvas.drawRect(left + blockSize, top, right + blockSize, bottom, fillPaint);
                    bufferCanvas.drawRect(left, top + blockSize, right, bottom + blockSize, fillPaint);
                    bufferCanvas.drawRect(left + blockSize, top + blockSize, right + blockSize, bottom + blockSize, fillPaint);

                }
            }
        }

        // 画网格线（竖线）
        for (int i = 0; i <= gridWidth; i++) {
            int x = i * blockSize;
            // 最后一条线如果超出画布也用 horizontalPixels
            if (x > horizontalPixels) x = horizontalPixels;
            bufferCanvas.drawLine(x, 0, x, verticalPixels, gridPaint);
        }
        // 横线
        for (int j = 0; j <= gridHeight; j++) {
            int y = j * blockSize;
            if (y > verticalPixels) y = verticalPixels;
            bufferCanvas.drawLine(0, y, horizontalPixels, y, gridPaint);
        }

        // 把缓存 bitmap 绘制到屏幕 canvas
        canvas.drawBitmap(blankBitmap, 0, 0, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // 只处理 ACTION_DOWN，避免重复触发多次（也可以处理 ACTION_UP）
        if (e.getAction() != MotionEvent.ACTION_DOWN) return true;

        float x = e.getX();
        float y = e.getY();

        // 计算格子索引（列 col, 行 row）
        int col = (int) (x / blockSize);
        int row = (int) (y / blockSize);

        // 边界检查（防止触摸到右/下边缘导致索引越界）
        if (col < 0 || col >= gridWidth || row < 0 || row >= gridHeight) {
            Log.d("MyImageView", "Touched outside grid: col=" + col + " row=" + row);
            return true;
        }

        // 填充该格子（可以改为切换颜色/清除等）
        cellColors[row][col] = fillColor;

        Log.d("MyImageView", String.format("Fill cell row=%d col=%d", row, col));

        invalidate(); // 触发重绘
        return true;  // 消费事件
    }

    // 可选：提供外部方法去设置填充颜色 / 清空格子
    public void setFillColor(int color) {
        this.fillColor = color;
    }

    public void clearAllCells() {
        if (cellColors == null) return;
        for (int r = 0; r < gridHeight; r++) {
            for (int c = 0; c < gridWidth; c++) {
                cellColors[r][c] = 0;
            }
        }
        invalidate();
    }
}
