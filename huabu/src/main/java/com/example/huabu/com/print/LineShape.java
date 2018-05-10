package com.example.huabu.com.print;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 撩个小媳妇 on 2018/5/10.
 */

public class LineShape extends PaintShape {


    public LineShape(float mStartX, float mStartY, Paint mPaint) {
        super(mStartX, mStartY, mPaint);
    }

    public LineShape(Paint paint) {
        super(paint);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(mStartX, mStartY, mEndX, mEndY, mPaint);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(PaintConfig.Shape.Line).append(FIELD_SEPARATOR);
        builder.append(getFieldString());
        return builder.toString();
    }
}
