package com.mahuafactory.animation.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mahuafactory.animation.R;

/**
 * TODO: document your custom view class.
 */
public class MartrixDrawView extends View {

    private Paint mPaint;
    private Bitmap b;
    private Matrix m;

    public MartrixDrawView(Context context) {
        super(context);
        init();
    }

    public MartrixDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MartrixDrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setColor(Color.parseColor("#ff33b5e5"));
        b = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        m = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(b, m, null);

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int bw = b.getWidth();
        int bh = b.getHeight();
        RectF src = new RectF(0, 0, bw, bh);
        RectF dst = new RectF(0, 0, w, h);
        m.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        float[] pts = {0, 0, 0, bh, bw, bh, bw, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        m.mapPoints(pts, 8, pts, 0, 4);
        int DX = 40;
        pts[8] += DX;
        pts[14] -= DX;
        m.setPolyToPoly(pts, 0, pts, 8, 4);
    }
}
