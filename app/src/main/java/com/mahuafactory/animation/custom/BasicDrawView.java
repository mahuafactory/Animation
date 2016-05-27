package com.mahuafactory.animation.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mahuafactory.animation.R;

/**
 * TODO: document your custom view class.
 */
public class BasicDrawView extends View {

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
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home);
        Path path = new Path();
        path.addCircle(500, 500, 400, Path.Direction.CCW);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, false);

        Path dst = new Path();
        pathMeasure.getSegment(0, 300, dst, true);

        canvas.drawPath(dst, paint);

//        canvas.drawTextOnPath("asdfasdfsdf", path, 0, 0, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        requestLayout();
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }


}
