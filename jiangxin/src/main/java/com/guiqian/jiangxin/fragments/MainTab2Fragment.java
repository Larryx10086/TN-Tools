package com.guiqian.jiangxin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guiqian.jiangxin.R;
import com.guiqian.jiangxin.activities.SearchActivity;

public class MainTab2Fragment extends Fragment {

    private static final String TAG = MainTab2Fragment.class.getSimpleName();
    private static final String PARAM = "param";
    private String mParam;
    private Toolbar mToolbar;
    private TextView mTXSearch;
    private TabLayout mMainTab2SubTitles;
    private ViewPager mMainTab2SubTitlesVP;
    private String[] mTab2SubTitles = {
            "文章","帖子","问题集"
    };

    public static MainTab2Fragment newInstance(String param) {
        MainTab2Fragment fragment = new MainTab2Fragment();
        Bundle args = new Bundle();
        args.putString(PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_zihui, container,false);
        findViews(view);
        initToolbar();
        init(view);
        return view;
    }

    private void findViews(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.tb2_toolbar);
        mTXSearch = view.findViewById(R.id.main_tab2_search);
        mTXSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    protected void initToolbar() {
//        mToolbar.setTitle(R.string.app_name);
//        mToolbar.setNavigationIcon(R.mipmap.ic_slide_icon);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    private void init(View view) {
        mMainTab2SubTitles = (TabLayout) view.findViewById(R.id.main_tab2_subtitle_tablayout);
        mMainTab2SubTitlesVP = (ViewPager) view.findViewById(R.id.main_tab2_sub_container);
        mMainTab2SubTitles.setupWithViewPager(mMainTab2SubTitlesVP);
        mMainTab2SubTitles.setTabMode(TabLayout.MODE_SCROLLABLE);
        mMainTab2SubTitlesVP.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = MainTab2SubArticleFragment.newInstance("ArticleFragment");
                        break;
                    case 1:
                        fragment = MainTab2SubTopicFragment.newInstance("TopicFragment");
                        break;
                    case 2:
                        fragment = MainTab2SubQuestionFragment.newInstance("QuestionFragment");
                        break;
                }
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTab2SubTitles[position];
            }

            @Override
            public int getCount() {
                return mTab2SubTitles.length;
            }
        });
    }


    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
        Log.d(TAG,"setMenuVisibility");
    }

}