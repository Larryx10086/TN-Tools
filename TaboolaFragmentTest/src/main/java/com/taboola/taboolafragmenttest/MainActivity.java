package com.taboola.taboolafragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.taboola.android.Taboola;
import com.taboola.android.TaboolaWidget;
import com.taboola.android.api.TaboolaOnClickListener;
import com.taboola.android.globalNotifications.GlobalNotificationReceiver;
import com.taboola.android.js.TaboolaJs;
import com.taboola.android.listeners.ScrollToTopListener;
import com.taboola.android.listeners.TaboolaEventListener;
import com.taboola.android.utils.Properties;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ViewGroup container;
    private View feedList;
    private View webView;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new FeedFragment())
                .commit();
        container = findViewById(R.id.fragment_container);
    }

    public void onFeedListCreate(View view){
        this.feedList = view;
    }

    public void onWebviewCreate(View view){
        this.webView = view;
    }

    public void hide(){
        container.removeAllViews();
    }

    public void showFeedList(){
        container.addView(feedList);
        TaboolaWidget tb = feedList.findViewById(R.id.taboola_widget_below_article);
        TaboolaJs.getInstance().registerWebView(tb);
        HashMap<String, String> extraPropr = new HashMap<>(1);
        extraPropr.put(Properties.ALLOW_NON_ORGANIC_OVERRIDE_PROP, "true");
        TaboolaJs.getInstance().setExtraProperties(extraPropr);
        TaboolaJs.getInstance().setOnClickListener(tb, new TaboolaOnClickListener() {
            @Override
            public boolean onItemClick(String placementName, String itemId, String url, boolean isOrganic) {
                Log.d("TABOOLA-DEBUG", "url = " + url);
                getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container,
                        InAppBrowser.getInstance(url)).commitAllowingStateLoss();
                return false;
            }
        });

    }
    public void showWebview(){
        container.addView(webView);
    }

}
