package com.taboola.tn.api20tester.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.taboola.tn.api20tester.R;
import com.taboola.tn.api20tester.adapter.FeedAdapter;
import com.taboola.tn.api20tester.model.Placement;
import com.taboola.tn.api20tester.model.TNArticleBean;
import com.taboola.tn.api20tester.thread.ArticleRequestThread;
import com.taboola.tn.api20tester.utils.Constant;
import com.taboola.tn.api20tester.utils.JsonParseTool;
import com.taboola.tn.api20tester.utils.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;

public class WidgetExampleActivity extends AppCompatActivity
//        implements HttpCallBack
{
    private static final String TAG = WidgetExampleActivity.class.getSimpleName();

    private FeedAdapter mFeedAdapter;
    private ProgressBar mProgressBar;
    private TextView mErrorText;
    private RecyclerView mRecyclerView;
    private FeedHandler handler;
    private TNArticleBean tnArticleBean;
    private List<Placement> placements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.layout_wdg_example_activity);
        mProgressBar = findViewById(R.id.loading_tn_content);
        mErrorText = findViewById(R.id.request_error);
        mRecyclerView = findViewById(R.id.tn_items);
        tnArticleBean = new TNArticleBean();
        placements = new ArrayList<>();
        mFeedAdapter = new FeedAdapter(WidgetExampleActivity.this,placements);
        mRecyclerView.setAdapter(mFeedAdapter);
        mRecyclerView.addOnScrollListener(recyclerScrollListener);
        initRecyclerView(mRecyclerView);

        handler = new FeedHandler();

//        HttpUtil httpUtil = new HttpUtil();
//		httpUtil.setHttpCallBack(this);
        setStatus(true,false,false);
        new ArticleRequestThread(this,handler).start();

        mFeedAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG,"clicked on item = " + position);
                String article_url = placements.get(position).getArticles().get(0).getUrl();
                Uri uri = Uri.parse(article_url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mErrorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatus(true,false,false);
                new ArticleRequestThread(WidgetExampleActivity.this,handler).start();
            }
        });

    }

    private void initRecyclerView(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
    }

//    @Override
//    public void onSuccess(String resposeBody) {
//        Log.d(TAG,"SUCCESS");
//        Log.d(TAG,"resposeBody = " + resposeBody);
//
//    }
//
//    @Override
//    public void onFailure(String exceptionMsg) {
//        Log.d(TAG,"FAILURE = " + exceptionMsg);
//    }

    public class FeedHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            String msgStr = (String) msg.obj;
            Log.d(TAG,"msgStr = " + msgStr);
            switch (msg.what) {
                case Constant.SUCCESS:
                    Log.d(TAG,"SUCCESS");
                    setStatus(false,false,true);
                    tnArticleBean = new JsonParseTool().parseTaboolaNewsJson(msgStr);
                    mFeedAdapter.addItems(tnArticleBean.getPlacements());
                    break;

                case Constant.FAILURE:
                    setStatus(false,true,false);
                    break;

            }
        }
    }

    private void setStatus(boolean progressFlag, boolean errorTextFlag, boolean listFlag) {
        if (progressFlag == true){
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }

        if (errorTextFlag == true) {
            mErrorText.setVisibility(View.VISIBLE);
        } else {
            mErrorText.setVisibility(View.GONE);
        }

        if (listFlag == true) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener(){

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.d(TAG,"CURRENT DISPLAYING = " + newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = layoutManager.findFirstVisibleItemPosition();
            Log.d(TAG,"POSITION = " + position);
            if (layoutManager!=null) {
                int firstVisible = layoutManager.findFirstVisibleItemPosition();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                int visibleItemCount = lastVisible - firstVisible;
                if (lastVisible == 0) {
                    visibleItemCount = 0;
                }
                if (visibleItemCount != 0) {
//                    dealScrollEvent(firstVisible, lastVisible);
                }

            }

        }

    };

}
