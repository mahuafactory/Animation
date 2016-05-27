package com.mahuafactory.animation.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

/**
 * Created by wt on 2016/5/25.
 */
public class MyButton extends Button {
    private static final String TAG = "MyButton";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e(TAG, "onSizeChanged---w---"+w+"---h---"+h);
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "end onSizeChanged---w---"+w+"---h---"+h);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "end onMeasure");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout--left--" + left + "--top--" + top + "--right--" + right + "--bottom--" + bottom);
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "end onLayout--left--" + left + "--top--" + top + "--right--" + right + "--bottom--" + bottom);
    }

}
