package com.taboola.tn.api20tester.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.taboola.tn.api20tester.R;
import com.taboola.tn.api20tester.utils.ScrollViewListener;
import com.taboola.tn.api20tester.widget.MyScrollView;

public class MultiExampleActivity extends AppCompatActivity implements ScrollViewListener {
    private static final String TAG = MultiExampleActivity.class.getSimpleName();

    private LinearLayout tnContainer;
    private View bottomProgressIndicator;
    private MyScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_multi_example_activity);

        mScrollView = findViewById(R.id.tn_multi_request_scrollview);
        mScrollView.setScrollViewListener(this);
        tnContainer = findViewById(R.id.tn_container);
        bottomProgressIndicator = LayoutInflater.from(this)
                .inflate(R.layout.layout_bottom_progress_indicator,null,false);
        bottomProgressIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onScrollChanged(MyScrollView myScrollView, int x, int y, int oldx, int oldy) {

    }

    @Override
    public void onScrolledToBottom() {
        Log.d(TAG,"BOTTOMED--");







    }

    @Override
    public void onScrolledToTop() {
        Log.d(TAG,"TOPPED--");
    }
}
