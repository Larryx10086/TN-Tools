package com.celltick.apac.news.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.celltick.apac.news.R;
import com.celltick.apac.news.activities.WebViewActivity;
import com.celltick.apac.news.adapter.FeedAdapter;
import com.celltick.apac.news.model.ArticleBean;
import com.celltick.apac.news.recyclerview_pkg.EndlessRecyclerOnScrollListener;
import com.celltick.apac.news.recyclerview_pkg.HeaderAndFooterRecyclerViewAdapter;
import com.celltick.apac.news.recyclerview_pkg.utils.RecyclerViewStateUtils;
import com.celltick.apac.news.recyclerview_pkg.weight.LoadingFooter;
import com.celltick.apac.news.threads.BottomArticleRequestThread;
import com.celltick.apac.news.threads.HeaderArticleRequestThread;
import com.celltick.apac.news.util.Constant;
import com.celltick.apac.news.util.JsonParseTool;
import com.celltick.apac.news.util.NetworkUtil;
import com.celltick.apac.news.util.OnItemClickLitener;
import com.celltick.apac.news.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FeedFragment extends Fragment {

    private static final String TAG = "FeedFragment";

    private FeedAdapter mAdapter;
    //新闻列表数据
    private List<ArticleBean> mArticleList;

    private JsonParseTool mJsonTool;
    private FeedHandler handler;
    private String mTitle;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter = null;
    private int num_endless_swipe;
    private TextView mTXNumNotice;
    private boolean isNeedClearFeed = false;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    Bundle mBundle;


    public static FeedFragment newInstance(String id) {
        FeedFragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = this.getArguments().get("title").toString();

        mBundle = getActivity().getIntent().getBundleExtra("configurations");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_feed, container, false);
        mTXNumNotice = view.findViewById(R.id.tx_num_notice);
        mArticleList = new ArrayList<>();
        mJsonTool = new JsonParseTool();
        handler = new FeedHandler();
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        mRecyclerView = view.findViewById(R.id.list);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkUtil.isNetworkAvailable()){
                    HeaderArticleRequestThread thread = new HeaderArticleRequestThread(handler,mTitle,mBundle);
                    thread.start();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        HeaderArticleRequestThread thread = new HeaderArticleRequestThread(handler,mTitle,mBundle);
        thread.start();

        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                ArticleBean articleItem = mArticleList.get(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                String template = SharedPreferencesUtils.getInstance().getString(Constant.TEMPLATE);
//                Log.d(TAG,"template = "+template);
                if(template == null || template.equals("") || template.equals("default")){
                    intent.putExtra("url", articleItem.getArticleURL());
//                    Log.d(TAG,"url = "+articleItem.getArticleURL());
                } else {
                    intent.putExtra("url", articleItem.getArticleURL()+"&theme="+template);
//                    Log.d(TAG,"url = "+articleItem.getArticleURL()+"&theme="+template);
                }

                intent.putExtra("title", articleItem.getPublisherName());
                intent.putExtra("article_title", articleItem.getTitle());
//                intent.putExtra("cp-logo", articleItem.getCpLogoURL());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

    private void initData(){

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setRefreshing(false);

        mAdapter = new FeedAdapter(getActivity(), mArticleList);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(getActivity()));
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }



    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

//            Log.d(TAG,"=======onLoadNextPage========");

            int end_fresh_num = num_endless_swipe++;
            int offset = end_fresh_num*Constant.NUM_SM_NORMAL_REQUEST+Constant.NUM_SM_1ST_REQUEST;

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if(state == LoadingFooter.State.Loading) {
                return;
            }
            RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, 0, LoadingFooter.State.Loading, null);
            if (NetworkUtil.isNetworkAvailable()) {
                BottomArticleRequestThread thread = new BottomArticleRequestThread(handler,mTitle,mBundle);
                thread.start();
            } else {
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
            }



        }

        @Override
        public void onLoadTopPage(View view) {
            super.onLoadTopPage(view);

        }


    };


    public class FeedHandler extends Handler {

        List<ArticleBean> articleList = new ArrayList<>();
        @Override
        public void handleMessage(Message msg) {
            String msgStr = (String) msg.obj;
            int flag = msg.arg1;
            switch (msg.what) {
                case Constant.SUCCESS:
                    if (flag == Constant.HEADER_LOAD) {
                        articleList = mJsonTool.parseTaboolaNewsJson(msgStr);
                        Log.d(TAG,"articleList = " + articleList.size());
                        if (articleList.size() != 0 && articleList != null) {
                            mAdapter.clearList();
                            mAdapter.addHeaderItem(articleList);
                            showUpdates(articleList.size());
                            dismissSwipeFresh();

                        }else {
                            showUpdates(articleList.size());
                            dismissSwipeFresh();
                        }

                    } else if (flag == Constant.BOTTOM_LOAD){
                        Log.d(TAG,"底部加载的数据成功：" + msgStr);
                        articleList = mJsonTool.parseTaboolaNewsJson(msgStr);
                        if (articleList.size() == 0 || articleList == null) {
                            RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.TheEnd);
                        }else {
                            mAdapter.addFooterItem(articleList);
                            RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                        }

                    }
                    break;

                case Constant.ERR_RETURN:
                    if (flag == Constant.HEADER_LOAD) {
                        dismissSwipeFresh();
                    } else if (flag == Constant.BOTTOM_LOAD){
                        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                    }
                    Toast.makeText(getActivity(), "Exception : "+msgStr, Toast.LENGTH_LONG).show();
                    break;
                case Constant.ERR_RETURN_NO_CONNECTION:
                case Constant.ERR_RETURN_TIMEOUT:
                case Constant.ERR_RETURN_UNKNOWN:
                    dismissSwipeFresh();
                    if (mRecyclerView != null){
                        if (RecyclerViewStateUtils.getFooterViewState(mRecyclerView) == LoadingFooter.State.Loading){
                            RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                        }
                    }

                    break;
                case Constant.ERR_BANNER_DISMISS:
                    dismissNumBanner();
                    break;
                case Constant.SC_HEADER_SUCCESS:
                case Constant.SC_BOTTOM_SUCCESS:
                    String SCString = (String) msg.obj;
                    List<ArticleBean> SCList = mJsonTool.parseSCJson(SCString);
                    if (SCList != null && SCList.size() != 0) {
                        for (int i=0; i<SCList.size(); i++){
                            if (articleList.size() > 5){
                                articleList.add(new Random().nextInt(articleList.size()-2),SCList.get(i));
                            }else {
                                articleList.add(SCList.get(i));
                            }

                        }
                    }
                    if (flag == Constant.HEADER_LOAD){
                        mAdapter.addHeaderItem(articleList);
                        showUpdates(articleList.size());
                    } else if (flag == Constant.BOTTOM_LOAD){
                        mAdapter.addFooterItem(articleList);
                        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                    }
                    dismissSwipeFresh();
                    break;
                case Constant.SC_HEADER_FAILED:
                case Constant.SC_BOTTOM_FAILED:
                    if (flag == Constant.HEADER_LOAD){
                        mAdapter.addHeaderItem(articleList);
                        showUpdates(articleList.size());
                    } else if (flag == Constant.BOTTOM_LOAD){
                        mAdapter.addFooterItem(articleList);
                        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                    }
                    dismissSwipeFresh();
                    break;
            }
        }
    }

    private void dismissNumBanner(){
        if (mTXNumNotice != null && mTXNumNotice.getVisibility() == View.VISIBLE){
            mTXNumNotice.setVisibility(View.GONE);
        }
    }

    private void displayNumBanner(String txt){
        if (mTXNumNotice != null && mTXNumNotice.getVisibility() == View.GONE){
            mTXNumNotice.setVisibility(View.VISIBLE);
            mTXNumNotice.setText(txt);
        }
    }

    private void dismissSwipeFresh(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void showUpdates(int articleNum){
        if (FeedFragment.this != null && FeedFragment.this.isAdded()){
            displayNumBanner(articleNum+" updated");
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.sendEmptyMessage(Constant.ERR_BANNER_DISMISS);
                }
            }).start();


        }
    }



    /**
     * <功能描述> 步骤1：创建指定的接口
     *
     * @author Administrator
     */
    public interface OnErrReturnListener {
        // 步骤2：创建接口中的相关方法
        void onErrReturned(String newItem);
        void onSucReturned(String newItem);
    }

    // 步骤3：声明回调接口的对象，接口类对象
    private OnErrReturnListener onErrReturnListener;

}
