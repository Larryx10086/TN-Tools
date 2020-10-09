package com.example.sdk_callback_verify;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taboola.android.PublisherInfo;
import com.taboola.android.Taboola;
import com.taboola.android.TaboolaWidget;
import com.taboola.android.globalNotifications.GlobalNotificationReceiver;
import com.taboola.android.listeners.TaboolaUpdateContentListener;
import com.taboola.android.utils.SdkDetailsHelper;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements GlobalNotificationReceiver.OnGlobalNotificationsListener{
    private TaboolaWidget taboolaWidget;
    private SwipeRefreshLayout swipeRefreshLayout;
    GlobalNotificationReceiver mGlobalNotificationReceiver = new GlobalNotificationReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlobalNotificationReceiver.registerNotificationsListener(this);
        mGlobalNotificationReceiver.registerReceiver(this);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = this.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Main", "onRefresh");
                taboolaWidget.updateContent(new TaboolaUpdateContentListener() {
                    @Override
                    public void onUpdateContentCompleted() {
                        Log.d("Main", "onUpdateContentCompleted");
                        dismissSwipeFresh();
                    }
                });
            }
        });

        taboolaWidget = new TaboolaWidget(this);
        FrameLayout frameLayout = (FrameLayout) this.findViewById(R.id.parent_layout);
        frameLayout.addView(taboolaWidget);
        buildBelowArticleWidget(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGlobalNotificationReceiver.unregisterNotificationsListener();
        mGlobalNotificationReceiver.unregisterReceiver(this);
    }

    private void buildBelowArticleWidget(Context context) {

        taboolaWidget
                .setPublisher("sdk-tester")
                .setPageType("article")
                .setPageUrl("https://blog.taboola.com")
                .setPlacement("Feed without video")
                .setMode("thumbs-feed-01")
                .setTargetType("mix")
                .setInterceptScroll(true);

        //used for enable horizontal scroll
        HashMap<String, String> optionalPageCommands = new HashMap<>();
        optionalPageCommands.put("enableHorizontalScroll", "true");
        optionalPageCommands.put("useOnlineTemplate", "true");
        optionalPageCommands.put("autoCollapseOnError", "false");
        optionalPageCommands.put("detailedErrorCodes", "true");
        taboolaWidget.setExtraProperties(optionalPageCommands);
        taboolaWidget.getLayoutParams().height = SdkDetailsHelper.getDisplayHeight(context);
        taboolaWidget.fetchContent();
    }

    private void dismissSwipeFresh(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void taboolaDidReceiveAd(TaboolaWidget taboolaWidget) {
        Log.d("Main", "taboolaDidReceiveAd");
    }

    @Override
    public void taboolaViewResized(TaboolaWidget taboolaWidget, int i) {
        Log.d("Main", "taboolaViewResized");
    }

    @Override
    public void taboolaItemDidClick(TaboolaWidget taboolaWidget) {
        Log.d("Main", "taboolaItemDidClick");
    }

    @Override
    public void taboolaDidFailAd(TaboolaWidget taboolaWidget, String s) {
        Log.d("Main", "taboolaDidFailAd");
    }
}
