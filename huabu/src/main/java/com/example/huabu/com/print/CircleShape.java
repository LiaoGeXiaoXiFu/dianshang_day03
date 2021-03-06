package com.example.huabu.com.print;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 撩个小媳妇 on 2018/5/10.
 */

public class CircleShape extends PaintShape {


    public CircleShape(float mStartX, float mStartY, Paint mPaint) {
        super(mStartX, mStartY, mPaint);
    }

    public CircleShape(Paint paint) {
        super(paint);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float a = mEndX - mStartX;
        float b = mEndY - mStartY;
        float c = (float) Math.sqrt(Math.pow(Math.abs(a), 2) + Math.pow(Math.abs(b), 2));

        float centreX = Math.abs((mEndX - mStartX)) / 2 + Math.min(mStartX, mEndX);
        float centreY = Math.abs((mEndY - mStartY)) / 2 + Math.min(mStartY, mEndY);
//        float centreX = Math.pow(radius, 2) - Math.pow()
//        float centreY = Math.abs((mEndY - mStartY)) / 2 +  Math.min(mStartY, mEndY);
        canvas.drawCircle(centreX, centreY, c / 2, mPaint);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(PaintConfig.Shape.Circle).append(FIELD_SEPARATOR);
        builder.append(getFieldString());
        return builder.toString();
    }
}
