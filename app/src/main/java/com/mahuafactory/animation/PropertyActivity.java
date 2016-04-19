package com.mahuafactory.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);


        mBtn = (Button) findViewById(R.id.btn);
        mTextView = (TextView) findViewById(R.id.text_view);

        mBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn: {
                Keyframe frame0 = Keyframe.ofFloat(0f, 1);
                Keyframe frame1 = Keyframe.ofFloat(0.5f, 2);
                frame1.setInterpolator(new AnticipateOvershootInterpolator());
                Keyframe frame3 = Keyframe.ofFloat(1,3);
                PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("scaleX", frame0, frame1, frame3);
                PropertyValuesHolder frameHolder2 = PropertyValuesHolder.ofKeyframe("scaleY", frame0, frame1, frame3);
                Animator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, frameHolder,frameHolder2);
                animator.setDuration(5000);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();
            }
            break;
        }
    }
}
