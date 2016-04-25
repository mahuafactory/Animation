package com.mahuafactory.animation;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAD;
    private Button mBtnAccelerate;
    private Button mBtnAnticipate;
    private Button mBtnAO;
    private Button mBtnBounce;
    private Button mBtnCycle;
    private Button mBtnDecelerate;
    private Button mBtnLinear;
    private Button mBtnOvershoot;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView) findViewById(R.id.text_view);


        mBtnAD = (Button) findViewById(R.id.btn_a_d);
        mBtnAccelerate = (Button) findViewById(R.id.btn_accelerate);
        mBtnAnticipate = (Button) findViewById(R.id.btn_anticipate);
        mBtnAO = (Button) findViewById(R.id.btn_a_o);
        mBtnBounce = (Button) findViewById(R.id.btn_bounce);
        mBtnCycle = (Button) findViewById(R.id.btn_cycle);
        mBtnDecelerate = (Button) findViewById(R.id.btn_decelerate);
        mBtnLinear = (Button) findViewById(R.id.btn_linear);
        mBtnOvershoot = (Button) findViewById(R.id.btn_overshoot);


        mBtnAD.setOnClickListener(this);
        mBtnAccelerate.setOnClickListener(this);
        mBtnAnticipate.setOnClickListener(this);
        mBtnAO.setOnClickListener(this);
        mBtnBounce.setOnClickListener(this);
        mBtnCycle.setOnClickListener(this);
        mBtnDecelerate.setOnClickListener(this);
        mBtnLinear.setOnClickListener(this);
        mBtnOvershoot.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);

        switch (v.getId()) {
            case R.id.btn_a_d: {
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
            }
            break;
            case R.id.btn_accelerate: {
                animation.setInterpolator(new AccelerateInterpolator());
            }
            break;
            case R.id.btn_anticipate: {
                animation.setInterpolator(new AnticipateInterpolator());
            }
            break;
            case R.id.btn_a_o: {
                animation.setInterpolator(new AnticipateOvershootInterpolator());
            }
            break;
            case R.id.btn_bounce: {
                animation.setInterpolator(new BounceInterpolator());
            }
            break;
            case R.id.btn_cycle: {
                animation.setInterpolator(new CycleInterpolator(1));
            }
            break;
            case R.id.btn_decelerate: {
                animation.setInterpolator(new DecelerateInterpolator());
            }
            break;
            case R.id.btn_linear: {
                animation.setInterpolator(new DecelerateInterpolator(2f));
            }
            break;
            case R.id.btn_overshoot: {
                animation.setInterpolator(new DecelerateInterpolator());
            }
            break;
        }
        mTextView.startAnimation(animation);
    }
}
