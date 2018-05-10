package com.example.yuan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 撩个小媳妇 on 2018/5/10.
 */

public class DrawView extends View {

    private int ballColor;
    private float ballRadius;
    private float ballStartX;
    private float ballStartY;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawView);
        ballColor = array.getColor(R.styleable.DrawView_BallColor, Color.RED);
        ballRadius = array.getFloat(R.styleable.DrawView_BallRadius, 10);
        ballStartX = array.getFloat(R.styleable.DrawView_BallStartX, 10);
        ballStartY = array.getFloat(R.styleable.DrawView_BallStartY, 10);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        //设置画笔颜色
        paint.setColor(ballColor);
        //画小球
        canvas.drawCircle(ballStartX, ballStartY, ballRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ballStartX = event.getX();
        ballStartY = event.getY();
        ballRadius=ballStartX % ballStartY;
        this.invalidate();
        return true;
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
