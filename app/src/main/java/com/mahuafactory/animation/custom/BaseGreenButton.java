package com.mahuafactory.animation.custom;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Arrays;

/**
 * Created by wt on 2016/4/28.
 */
public class BaseGreenButton extends Button {
    /**
     * RGB和Alpha的终值计算方法如下：
     Red通道终值= a[0] * srcR + a[1] * srcG + a[2] * srcB + a[3] * srcA + a[4]
     Green通道终值= a[5] * srcR + a[6] * srcG + a[7] * srcB + a[8] * srcA + a[9]
     Blue通道终值= a[10] * srcR + a[11] * srcG + a[12] * srcB + a[13] * srcA + a[14]
     Alpha通道终值= a[15] * srcR + a[16] * srcG + a[17] * srcB + a[18] * srcA + a[19]
     备注：
     srcR为原图Red通道值，srcG为原图Green通道值，srcB为原图Blue通道值，srcA为原图Alpha通道值。
     每个通道的源值和终值都在0到255的范围内。即使计算结果大于255或小于0，值都将被限制在0到255的范围内。
     */
    private final static float[] BUTTON_PRESSED = new float[] {
            0.9f, 0, 0, 0, 0,
            0, 0.9f, 0, 0, 0,
            0, 0, 0.9f, 0, 0,
            0, 0, 0, 1, 0 };


    private final static float[] BUTTON_RELEASED = new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0 };

    private final static ColorMatrixColorFilter PRESSED_FILTER = new ColorMatrixColorFilter(BUTTON_PRESSED);
    private final static ColorMatrixColorFilter RELEASED_FILTER = new ColorMatrixColorFilter(BUTTON_RELEASED);

    public BaseGreenButton(Context context) {
        this(context, null);
    }

    public BaseGreenButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseGreenButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
        int[] stateList = this.getDrawableState();
        boolean isPressed = Arrays.binarySearch(stateList,android.R.attr.state_pressed)>=0;
        if(isPressed){
            this.getBackground().setColorFilter(PRESSED_FILTER);
        }else{
            this.getBackground().setColorFilter(RELEASED_FILTER);
        }
    }

}