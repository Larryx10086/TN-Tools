package com.taboola.tn.api20tester.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.taboola.tn.api20tester.R;
import com.taboola.tn.api20tester.model.Placement;
import com.taboola.tn.api20tester.model.TNArticleBean;
import com.taboola.tn.api20tester.thread.ArticleRequestThread;
import com.taboola.tn.api20tester.thread.SendEventsThread;
import com.taboola.tn.api20tester.utils.Constant;
import com.taboola.tn.api20tester.utils.HttpCallBack;
import com.taboola.tn.api20tester.utils.HttpUtil;
import com.taboola.tn.api20tester.utils.JsonParseTool;
import com.taboola.tn.api20tester.utils.OnItemClickLitener;
import com.taboola.tn.api20tester.utils.ScrollViewListener;
import com.taboola.tn.api20tester.utils.WindowManagerUtil;
import com.taboola.tn.api20tester.widget.MyScrollView;
import com.taboola.tn.api20tester.widget.TNItemView;

import java.util.ArrayList;
import java.util.List;

public class ArticleExampleActivity extends AppCompatActivity implements HttpCallBack , TNItemView.OnClickListener {
    private static final String TAG = ArticleExampleActivity.class.getSimpleName();

    private TNItemView mItemMidArticle,mItemBottom1,mItemBottom2,mItemBottom3,mItemBottom4;
    private FeedHandler handler;
    private TNArticleBean tnArticleBean;
    private List<Placement> placements;
    private MyScrollView mScrollView;
    private HttpUtil mHttpUtil;
    private SendEventsThread mSendEventsThread;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.layout_article_example_activity);
        mScrollView = findViewById(R.id.tn_article_demo_scrollview);
        mItemMidArticle = findViewById(R.id.tn_item_in_mid_article);
        mItemMidArticle.setOnClickListener(this);
        mItemBottom1 = findViewById(R.id.tn_item_in_bottom_1);
        mItemBottom1.setOnClickListener(this);
        mItemBottom2 = findViewById(R.id.tn_item_in_bottom_2);
        mItemBottom2.setOnClickListener(this);
        mItemBottom3 = findViewById(R.id.tn_item_in_bottom_3);
        mItemBottom3.setOnClickListener(this);
        mItemBottom4 = findViewById(R.id.tn_item_in_bottom_4);
        mItemBottom4.setOnClickListener(this);

        mHttpUtil = new HttpUtil();
        mHttpUtil.setHttpCallBack(this);
        tnArticleBean = new TNArticleBean();
        placements = new ArrayList<>();
        handler = new FeedHandler();

        new ArticleRequestThread(this,handler).start();

        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d(TAG,"VISIBLE = " + WindowManagerUtil.isViewVisible(mScrollView,mItemMidArticle));
//                Log.d(TAG,"mItemMidArticle Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemMidArticle));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemMidArticle) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(0).getEvents().getVisible()).start();
//                }
//                Log.d(TAG,"mItemBottom1 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom1));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom1) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(1).getEvents().getVisible()).start();
//                }
//                Log.d(TAG,"mItemBottom2 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom2));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom2) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(2).getEvents().getVisible()).start();
//                }
//                Log.d(TAG,"mItemBottom3 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom3));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom3) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(3).getEvents().getVisible()).start();
//                }
//                Log.d(TAG,"mItemBottom4 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom4));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom4) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(3).getEvents().getVisible()).start();
//                }
            }

        });
        mScrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView myScrollView, int x, int y, int oldx, int oldy) {
//                Log.d(TAG,"Scroll changed");
                Log.d(TAG,"mItemMidArticle Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemMidArticle));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemMidArticle) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(0).getEvents().getVisible()).start();
//                }
                Log.d(TAG,"mItemBottom1 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom1));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom1) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(1).getEvents().getVisible()).start();
//                }
                Log.d(TAG,"mItemBottom2 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom2));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom2) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(2).getEvents().getVisible()).start();
//                }
                Log.d(TAG,"mItemBottom3 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom3));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom3) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(3).getEvents().getVisible()).start();
//                }
                Log.d(TAG,"mItemBottom4 Visible = " + WindowManagerUtil.isViewVisible(mScrollView,mItemBottom4));
//                if (WindowManagerUtil.isViewVisible(mScrollView,mItemBottom4) == true){
//                    new SendEventsThread(ArticleExampleActivity.this,tnArticleBean.getPlacements().get(3).getEvents().getVisible()).start();
//                }
            }

            @Override
            public void onScrolledToBottom() {
                Log.d(TAG,"BTTOMED");
            }

            @Override
            public void onScrolledToTop() {
                Log.d(TAG,"TOPPED");
            }
        });

    }

    @Override
    public void onSuccess(String resposeBody) {
        Log.d(TAG,"Sending Visible = " + resposeBody);
    }

    @Override
    public void onFailure(String exceptionMsg) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tn_item_in_mid_article:
                Log.d(TAG,"CLICKED");
                String article_position1_url = tnArticleBean.getPlacements().get(0).getArticles().get(0).getUrl();
                Uri uri1 = Uri.parse(article_position1_url);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.tn_item_in_bottom_1:
                String article_position2_url = tnArticleBean.getPlacements().get(1).getArticles().get(0).getUrl();
                Uri uri2 = Uri.parse(article_position2_url);
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
                break;
            case R.id.tn_item_in_bottom_2:
                String article_position3_url = tnArticleBean.getPlacements().get(2).getArticles().get(0).getUrl();
                Uri uri3 = Uri.parse(article_position3_url);
                Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(intent3);
                break;
            case R.id.tn_item_in_bottom_3:
                String article_position4_url = tnArticleBean.getPlacements().get(3).getArticles().get(0).getUrl();
                Uri uri4 = Uri.parse(article_position4_url);
                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(intent4);
                break;
            case R.id.tn_item_in_bottom_4:
                String article_position5_url = tnArticleBean.getPlacements().get(4).getArticles().get(0).getUrl();
                Uri uri5 = Uri.parse(article_position5_url);
                Intent intent5 = new Intent(Intent.ACTION_VIEW, uri5);
                startActivity(intent5);
                break;

        }
    }

    public class FeedHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            String msgStr = (String) msg.obj;
            Log.d(TAG,"msgStr = " + msgStr);
            switch (msg.what) {
                case Constant.SUCCESS:
                    Log.d(TAG,"SUCCESS");
                    tnArticleBean = new JsonParseTool().parseTaboolaNewsJson(msgStr);
                    for (int i=0; i<tnArticleBean.getPlacements().size();i++){
                        if (i==0){
                            mItemMidArticle.loadArticleDetails(
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getThumbnails().get(0).getUrl(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getName(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getDescription(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getBranding()
                            );
                        }
                        if (i==1){
                            mItemBottom1.loadArticleDetails(
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getThumbnails().get(0).getUrl(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getName(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getDescription(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getBranding()
                            );
                        }
                        if (i==2){
                            mItemBottom2.loadArticleDetails(
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getThumbnails().get(0).getUrl(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getName(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getDescription(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getBranding()
                            );
                        }
                        if (i==3){
                            mItemBottom3.loadArticleDetails(
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getThumbnails().get(0).getUrl(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getName(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getDescription(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getBranding()
                            );
                        }
                        if (i==4) {
                            mItemBottom4.loadArticleDetails(
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getThumbnails().get(0).getUrl(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getName(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getDescription(),
                                    tnArticleBean.getPlacements().get(i).getArticles().get(0).getBranding()
                            );
                        }


                    }

                    break;

                case Constant.FAILURE:
                    break;

            }
        }
    }

}
