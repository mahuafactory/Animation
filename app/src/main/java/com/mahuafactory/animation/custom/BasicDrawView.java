package com.mahuafactory.animation.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.mahuafactory.animation.R;

/**
 * TODO: document your custom view class.
 */
public class BasicDrawView extends View {

    private Paint mPaint;
    private Bitmap b;
    private Matrix m;
    private Bitmap mSrcB;
    private Bitmap mDstB;
    private BitmapShader mBG;
    private static final int W = 200;
    private static final int H = 200;

    public BasicDrawView(Context context) {
        super(context);
        init();
    }

    public BasicDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasicDrawView(Context context, AttributeSet attrs, int defStyle) {
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
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);

        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);

        int baselineX = 100;
        int baselineY = 500;
        String text = "just for test";

//        canvas.drawLine(baselineX, baselineY, 1000, baselineY, linePaint);
//        canvas.drawLine(baselineX, baselineY, baselineX, 0, linePaint);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        linePaint.setColor(Color.BLUE);
        float ascent = baselineY + fontMetrics.ascent;
        float descent = baselineY + fontMetrics.descent;
        float top = baselineY + fontMetrics.top;
        float bottom = baselineY + fontMetrics.bottom;

        //绘制占据局域
        float width = paint.measureText(text);
        RectF rectF = new RectF(baselineX, top, baselineX + width, bottom);
        paint.setColor(Color.RED);
        canvas.drawRect(rectF, paint);

        //绘制文字可见矩形
        Rect minRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), minRect);
        minRect.left += baselineX;
        minRect.right += baselineX;
        minRect.top += baselineY;
        minRect.bottom += baselineY;
        paint.setColor(Color.GREEN);
        canvas.drawRect(minRect, paint);

        //绘制文字
        paint.setColor(Color.BLUE);
        canvas.drawText(text, baselineX, baselineY, paint);


//        canvas.drawLine(baselineX, ascent, 1000, ascent, linePaint);
//        canvas.drawLine(baselineX, descent, 1000, descent, linePaint);
//        canvas.drawLine(baselineX, top, 1000, top, linePaint);
//        canvas.drawLine(baselineX, bottom, 1000, bottom, linePaint);
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
