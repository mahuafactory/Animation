package com.mahuafactory.animation;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnView;
    private Button mBtnProperty;
    private Button mBtnLayoutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBtnView = (Button) findViewById(R.id.btn_view_animation);
        mBtnProperty = (Button) findViewById(R.id.btn_property_animation);
        mBtnLayoutAnimation = (Button) findViewById(R.id.btn_layout_animation);

        mBtnView.setOnClickListener(this);
        mBtnProperty.setOnClickListener(this);
        mBtnLayoutAnimation.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_animation: {
                Intent intent = new Intent(this, ViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_property_animation: {
                Intent intent = new Intent(this, PropertyActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_layout_animation: {
                Intent intent = new Intent(this, LayoutAnimationActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
