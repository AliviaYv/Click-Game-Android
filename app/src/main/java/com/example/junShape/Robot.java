package com.example.junShape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Robot {
    private float x, y, scale;
    private float angle = 0;

    // 各部分组件
    private Circle head, headOutline, leftEye, rightEye;
    private Circle antennaDotLeft, antennaDotRight;
    private StraightLine antennaLeft, antennaRight;
    private Rectangle body;
    private StraightLine armLeft, armRight, legLeft, legRight;

    private boolean facingRight = true;

    public Robot(float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        head = new Circle(0, 0, 30*scale, Color.WHITE);
        headOutline = new Circle(0, 0, 32*scale, Color.BLACK);

        // 眼睛
        leftEye = new Circle(-10*scale, 0, 3*scale, Color.BLACK);
        rightEye = new Circle(10*scale, 0, 3*scale, Color.RED);

        // 天线
        antennaLeft = new StraightLine(-10*scale, -32*scale, -10*scale, -50*scale, Color.RED, 2);
        antennaRight = new StraightLine(10*scale, -32*scale, 10*scale, -50*scale, Color.RED, 2);
        antennaDotLeft = new Circle(-10*scale, -52*scale, 2*scale, Color.BLACK);
        antennaDotRight = new Circle(10*scale, -52*scale, 2*scale, Color.BLUE);

        // 身体
        body = new Rectangle(-15*scale, 30*scale, 30*scale, 40*scale, Color.BLUE);

        // 手臂
        armLeft = new StraightLine(-15*scale, 35*scale, -40*scale, 15*scale, Color.GREEN, 3);
        armRight = new StraightLine(15*scale, 35*scale, 40*scale, 15*scale, Color.MAGENTA, 3);

        // 腿
        legLeft = new StraightLine(-10*scale, 70*scale, -10*scale, 95*scale, Color.RED, 4);
        legRight = new StraightLine(10*scale, 70*scale, 10*scale, 95*scale, Color.RED, 4);}

    public void update(float dx, float dy, float dAngle){
        x += dx;
        y += dy;
        angle += dAngle;



    }

    public void setFacingRight(boolean facingRight){
        this.facingRight = facingRight;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getAngle() { return angle; }
    public void setAngle(float angle) { this.angle = angle; }


    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x, y);
        if(!facingRight){
            canvas.scale(-1, 1); // 水平翻转
        }
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
