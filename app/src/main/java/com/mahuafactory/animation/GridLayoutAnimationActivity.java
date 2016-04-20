package com.mahuafactory.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutAnimationActivity extends AppCompatActivity {
    private GridView mGridView;
    private GridAdapter mGrideAdapter;
    private List<String> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_animation);

        mGridView = (GridView) findViewById(R.id.grid_view);
        mDatas.addAll(getData());
        GridAdapter adapter = new GridAdapter();
        mGridView.setAdapter(adapter);
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        for (int i = 1;i<35;i++){
            data.add("数据 "+i);
        }
        return data;
    }


    public void addData(){
        mDatas.addAll(mDatas);
        mGrideAdapter.notifyDataSetChanged();
    }


    public class GridAdapter extends BaseAdapter {
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView i = new TextView(GridLayoutAnimationActivity.this);
            i.setText(mDatas.get(position));
            i.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            return i;
        }

        public final int getCount() {
            return mDatas.size();
        }

        public final Object getItem(int position) {
            return null;
        }

        public final long getItemId(int position) {
            return position;
        }
    }
}
