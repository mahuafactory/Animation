package com.mahuafactory.animation;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.mahuafactory.animation.custom.BasicDrawView;

public class BasicDrawActivity extends AppCompatActivity {
    private BasicDrawView mBasicView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_draw);

//        mImageView = (ImageView) findViewById(R.id.image_view);

//        mBasicView = (BasicDrawView) findViewById(R.id.basic_view);

        if (mImageView == null) {
            return;
        }

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                float centerX = mImageView.getWidth() / 2f;
                float centerY = mImageView.getHeight() / 2f;
                // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
                final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 180, centerX, centerY,
                        310.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(2000);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                mImageView.startAnimation(rotation);
            }
        });

        // 获取布局的中心点位置，作为旋转的中心点

    }
}
