package com.guiqian.jiangxin.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.guiqian.jiangxin.R;
import com.guiqian.jiangxin.osutils.StatusBarUtil;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private Toolbar mToolbar;
    private SearchView mSearchView;
    private FrameLayout mSearchContainer;
    private View mSearchInputNoticeFrame, mSearchDefaultViewFrame, mSearchResultFrame,mSearchLoadingProgressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorPrimary);
        setContentView(R.layout.search_activity_layout);

        mToolbar = (Toolbar) findViewById(R.id.tb1_toolbar);
        setSupportActionBar(mToolbar);

        findViews();
        initSearchView(mSearchView);
    }

    private void findViews(){
        mSearchContainer = findViewById(R.id.search_container);
        mSearchInputNoticeFrame = mSearchContainer.findViewById(R.id.main_tab1_search_input_notice_frame);
        mSearchDefaultViewFrame = mSearchContainer.findViewById(R.id.main_tab1_search_default_view_frame);
        mSearchResultFrame = mSearchContainer.findViewById(R.id.main_tab1_search_result_frame);
        mSearchLoadingProgressFrame = mSearchContainer.findViewById(R.id.main_tab1_searching_loading_progress);
        mSearchView = findViewById(R.id.search_view);
    }

    private void initSearchView(SearchView mSearchView){
        //设置搜索框展开时是否显示提交按钮，可不显示
        mSearchView.setSubmitButtonEnabled(true);
        //让键盘的回车键设置成搜索
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //搜索框是否展开，false表示展开
//        mSearchView.setIconified(false);
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        mSearchView.setIconifiedByDefault(false);
        //获取焦点
        mSearchView.setFocusable(true);
        mSearchView.requestFocusFromTouch();
        //设置提示词
        mSearchView.setQueryHint(getResources().getString(R.string.main_tab1_search_hint));
        //设置输入框文字颜色
        EditText editText = (EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.main_search_hint_text_color));
        editText.setTextColor(ContextCompat.getColor(this, R.color.main_search_text_color));
        editText.setTextSize(getResources().getDimension(R.dimen.search_activity_frame_text_size));
        //去掉search view下划线
        mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate).setBackground(null);
        mSearchView.findViewById(android.support.v7.appcompat.R.id.submit_area).setBackground(null);

        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        //清除焦点，收软键盘
        //mSearchView.clearFocus();

        Toast.makeText(SearchActivity.this, "您选择的是："+query, Toast.LENGTH_SHORT).show();
        return true;
    }

    // 当搜索内容改变时触发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
        if(TextUtils.isEmpty(newText)) {
            mSearchInputNoticeFrame.setVisibility(View.GONE);
            mSearchResultFrame.setVisibility(View.GONE);
            mSearchLoadingProgressFrame.setVisibility(View.GONE);
            mSearchDefaultViewFrame.setVisibility(View.VISIBLE);
        } else {
            if (newText.equals("华为")){
                mSearchInputNoticeFrame.setVisibility(View.GONE);
                mSearchDefaultViewFrame.setVisibility(View.GONE);
                mSearchLoadingProgressFrame.setVisibility(View.VISIBLE);
                mSearchResultFrame.setVisibility(View.GONE);
            }else {
                mSearchInputNoticeFrame.setVisibility(View.VISIBLE);
                mSearchDefaultViewFrame.setVisibility(View.GONE);
                mSearchLoadingProgressFrame.setVisibility(View.GONE);
                mSearchResultFrame.setVisibility(View.GONE);
            }

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}