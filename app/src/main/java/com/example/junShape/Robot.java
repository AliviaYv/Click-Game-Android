package com.example.junShape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Robot {
    private double x, y, scale;

    // 各部分组件
    private Circle head, headOutline, leftEye, rightEye;
    private Circle antennaDotLeft, antennaDotRight;
    private StraightLine antennaLeft, antennaRight;
    private Rectangle body;
    private StraightLine armLeft, armRight, legLeft, legRight;

    public Robot(float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        // 头部
        head = new Circle(x, y, 30 * scale, Color.WHITE);
        headOutline = new Circle(x, y, 32 * scale, Color.BLACK);

        // 眼睛
        leftEye = new Circle(x - 10 * scale, y, 3 * scale, Color.BLACK);
        rightEye = new Circle(x + 10 * scale, y, 3 * scale, Color.BLUE);

        // 天线
        antennaLeft = new StraightLine(x - 10 * scale, y - 32 * scale, x - 10 * scale, y - 50 * scale, Color.RED, 2);
        antennaRight = new StraightLine(x + 10 * scale, y - 32 * scale, x + 10 * scale, y - 50 * scale, Color.RED, 2);
        antennaDotLeft = new Circle(x - 10 * scale, y - 52 * scale, 2 * scale, Color.BLACK);
        antennaDotRight = new Circle(x + 10 * scale, y - 52 * scale, 2 * scale, Color.BLUE);

        // 身体
        body = new Rectangle(x - 15 * scale, y + 30 * scale, 30 * scale, 40 * scale, Color.BLUE);

        // 手臂
        armLeft = new StraightLine(x - 15 * scale, y + 35 * scale, x - 40 * scale, y + 15 * scale, Color.GREEN, 3);
        armRight = new StraightLine(x + 15 * scale, y + 35 * scale, x + 40 * scale, y + 15 * scale, Color.GREEN, 3);

        // 腿
        legLeft = new StraightLine(x - 10 * scale, y + 70 * scale, x - 10 * scale, y + 95 * scale, Color.RED, 4);
        legRight = new StraightLine(x + 10 * scale, y + 70 * scale, x + 10 * scale, y + 95 * scale, Color.RED, 4);
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }


    public void draw(Canvas canvas, Paint paint) {
        // 绘制顺序：身体 → 腿 → 手 → 头 → 眼 → 天线
        body.draw(canvas, paint);
        legLeft.draw(canvas, paint);
        legRight.draw(canvas, paint);
        armLeft.draw(canvas, paint);
        armRight.draw(canvas, paint);
        headOutline.draw(canvas, paint);
        head.draw(canvas, paint);
        leftEye.draw(canvas, paint);
        rightEye.draw(canvas, paint);
        antennaLeft.draw(canvas, paint);
        antennaRight.draw(canvas, paint);
        antennaDotLeft.draw(canvas, paint);
        antennaDotRight.draw(canvas, paint);
    }
}
