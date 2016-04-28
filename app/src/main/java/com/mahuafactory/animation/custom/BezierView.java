package com.mahuafactory.animation.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.mahuafactory.animation.R;

/**
 * TODO: document your custom view class.
 */
public class BezierView extends View {

    private Paint mPaint;
    private Bitmap b;
    private Matrix m;
    private Bitmap mSrcB;
    private Bitmap mDstB;
    private BitmapShader mBG;
    private static final int W = 200;
    private static final int H = 200;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setColor(Color.parseColor("#ff33b5e5"));
        b = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        m = new Matrix();

        mSrcB = makeSrc(W, H);
        mDstB = makeDst(W, H);
        Bitmap bm = Bitmap.createBitmap(new int[]{0xFFFFFFFF, 0xFFCCCCCC,
                        0xFFCCCCCC, 0xFFFFFFFF}, 2, 2,
                Bitmap.Config.RGB_565);
        mBG = new BitmapShader(bm,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
    }

    private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    };

    @Override
    protected void onDraw(Canvas canvas) {
        final float LINE_SMOOTHNESS = 0.2f;
        float points[] = {200, 500, 400, 700, 600, 300, 800, 1000};
        final int lineSize = points.length / 2;
        float prePreviousPointX = Float.NaN;
        float prePreviousPointY = Float.NaN;
        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX = Float.NaN;
        float nextPointY = Float.NaN;
        Path path = new Path();


        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
//        Path path = new Path();
//        path.moveTo(points[0], points[1]);
//        path.cubicTo(points[2], points[3], points[4], points[5], points[6], points[7]);
//        canvas.drawPath(path, paint);
//
        Path path1 = new Path();
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.GREEN);
        paint1.setStrokeWidth(15);

        for (int valueIndex = 0; valueIndex < lineSize; ++valueIndex) {
            if (Float.isNaN(currentPointX)) {
                currentPointX = points[0];
                currentPointY = points[1];
            }
            if (Float.isNaN(previousPointX)) {
                if (valueIndex > 0) {
                    int i = valueIndex - 1;
                    previousPointX = points[i * 2];
                    previousPointY = points[i * 2 + 1];
                } else {
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                if (valueIndex > 1) {
                    int j = valueIndex - 2;
                    prePreviousPointX = points[j * 2];
                    prePreviousPointY = points[j * 2 + 1];
                } else {
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // nextPoint is always new one or it is equal currentPoint.
            if (valueIndex < lineSize - 1) {
                int k = valueIndex + 1;
                nextPointX = points[k * 2];
                nextPointY = points[k * 2 + 1];
            } else {
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (valueIndex == 0) {
                // Move to start point.
                path.moveTo(currentPointX, currentPointY);
                path1.moveTo(currentPointX, currentPointY);
                canvas.drawPoint(currentPointX, currentPointY, paint1);
            } else {
                // Calculate control points.
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (LINE_SMOOTHNESS * firstDiffX);
                final float firstControlPointY = previousPointY + (LINE_SMOOTHNESS * firstDiffY);
                final float secondControlPointX = currentPointX - (LINE_SMOOTHNESS * secondDiffX);
                final float secondControlPointY = currentPointY - (LINE_SMOOTHNESS * secondDiffY);
                path.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY,
                        currentPointX, currentPointY);
                path1.lineTo(firstControlPointX, firstControlPointY);
                path1.lineTo(secondControlPointX, secondControlPointY);
                path1.lineTo(currentPointX, currentPointY);
                canvas.drawPoint(firstControlPointX, firstControlPointY, paint1);
                canvas.drawPoint(secondControlPointX, secondControlPointY, paint1);
                canvas.drawPoint(currentPointX, currentPointY, paint1);
            }



            // Shift values by one back to prevent recalculation of values that have
            // been already calculated.
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
        }

        canvas.drawPath(path, paint);

        path.reset();
        paint.setColor(Color.BLACK);
        canvas.drawPath(path1,paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        canvas.drawPoints(points, paint);


    }

    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    private static final String[] sLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };
    private static final int ROW_MAX = 4;   // number of samples per row

}
