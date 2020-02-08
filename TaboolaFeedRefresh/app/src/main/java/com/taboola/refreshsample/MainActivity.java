package com.taboola.refreshsample;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.listeners.TaboolaUpdateContentListener;
import com.taboola.android.utils.SdkDetailsHelper;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TaboolaWidget mTaboolaWidget;
    private static final String TABOOLA_VIEW_ID = "123456";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTaboolaWidget = findViewById(R.id.taboola_feed);
        initAndBuildTaboolaWidget(mTaboolaWidget);
    }


    public void initAndBuildTaboolaWidget(TaboolaWidget taboolaWidget) {

        //For Meizu
        taboolaWidget
                .setPublisher("meizu-browser-russia")
                .setPageType("home")
                .setPageUrl("https://blog.taboola.com")
                .setPlacement("Below Homepage Thumbnails")
                .setMode("editorial-topnews-thumbs-s")
                .setTargetType("mix")
                .setViewId(TABOOLA_VIEW_ID)
                .setInterceptScroll(true);

//        taboolaWidget
//                .setPublisher("sdk-tester")
//                .setPageType("article")
//                .setPageUrl("https://blog.taboola.com")
//                .setPlacement("Feed without video")
//                .setMode("thumbs-feed-01")
//                .setTargetType("mix");

        int height = SdkDetailsHelper.getDisplayHeight(taboolaWidget.getContext()) * 2;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        taboolaWidget.setLayoutParams(params);
        taboolaWidget.setInterceptScroll(true);

        taboolaWidget.fetchContent();
    }

    @Override
    public void onRefresh() {
        //getting new taboola content once refresh was triggered
//        mTaboolaWidget.refresh();
//        mSwipeRefreshLayout.setRefreshing(false);

        mTaboolaWidget.updateContent(new TaboolaUpdateContentListener() {
            @Override
            public void onUpdateContentCompleted() {
                Log.d("taboola-refresh","onUpdateContentCompleted");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
