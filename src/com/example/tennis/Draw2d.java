package com.example.tennis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 25.08.13
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public class Draw2d extends View {
    public Draw2d(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

// закрашиваем холст белым цветом
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(450, 30, 25, paint);
        canvas.restore();
    }
}
