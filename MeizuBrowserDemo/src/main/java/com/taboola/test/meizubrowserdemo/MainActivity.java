package com.taboola.test.meizubrowserdemo;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.utils.SdkDetailsHelper;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TaboolaWidget taboolaWidget;
    private static final String TABOOLA_VIEW_ID = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taboolaWidget = (TaboolaWidget) findViewById(R.id.taboola_widget_below_article);
        buildBelowArticleWidget(taboolaWidget);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle(" ");

    }

    private void buildBelowArticleWidget(TaboolaWidget taboolaWidget) {
        taboolaWidget
                .setPublisher("meizu-browser-russia")
                .setPageType("home")
                .setPageUrl("https://blog.taboola.com")
                .setPlacement("Below Homepage Thumbnails")
                .setMode("editorial-topnews-thumbs-s")
                .setTargetType("mix")
                .setViewId(TABOOLA_VIEW_ID)
                .setInterceptScroll(true);

        taboolaWidget.getLayoutParams().height = SdkDetailsHelper.getDisplayHeight(taboolaWidget.getContext());
        HashMap<String, String> optionalPageCommands = new HashMap<>();
        optionalPageCommands.put("useOnlineTemplate", "true");
        taboolaWidget.setExtraProperties(optionalPageCommands);
        taboolaWidget.fetchContent();
    }


}
