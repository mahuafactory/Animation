package com.mahuafactory.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;
    private Button mBtnAddData;
    private ArrayAdapter<String> mAarrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);


        mBtnAddData = (Button) findViewById(R.id.btn_add_data);
        mBtnAddData.setOnClickListener(this);


        mListView = (ListView) findViewById(R.id.list_view);
        mAarrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getStrs());
        TranslateAnimation translateAnimation = new TranslateAnimation(-500, 0, 0, 0);
        translateAnimation.setDuration(1000);
        LayoutAnimationController controller = new LayoutAnimationController(translateAnimation);
        controller.setDelay(1);
        mListView.setLayoutAnimation(controller);
        mListView.setAdapter(mAarrayAdapter);
    }

    public List<String> getStrs() {
        List<String> list = new ArrayList<>();
        list.add("这是测试1");
        list.add("这是测试2");
        list.add("这是测试3");
        list.add("这是测试4");
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_data: {
                mAarrayAdapter.addAll(getStrs());
            }
            break;
        }
    }
}
