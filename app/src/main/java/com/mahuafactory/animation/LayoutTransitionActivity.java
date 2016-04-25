package com.mahuafactory.animation;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutTransitionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAdd;
    private Button mBtnDelete;
    private LinearLayout mLinear;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);


        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mLinear = (LinearLayout) findViewById(R.id.linear);
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);

        LayoutTransition layoutTransition = new LayoutTransition();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLinear, "rotationX", 0, 360);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animator);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat("translationX", 0, 500,0);
        Animator animator1 = ObjectAnimator.ofPropertyValuesHolder(mLinear, holder1,holder2,holder3,holder4,holder5);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, animator1);
        mLinear.setLayoutTransition(layoutTransition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: {
                i++;
                Button button = new Button(this);
                button.setText("button " + i);
                mLinear.addView(button, 0);
            }
            break;
            case R.id.btn_delete: {
                i--;
                mLinear.removeViewAt(0);
            }
            break;
        }
    }
}
