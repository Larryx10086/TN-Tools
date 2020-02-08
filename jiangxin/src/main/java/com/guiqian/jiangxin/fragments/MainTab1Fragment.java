package com.guiqian.jiangxin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guiqian.jiangxin.R;
import com.guiqian.jiangxin.activities.SearchActivity;

public class MainTab1Fragment extends Fragment {

    private static final String TAG = MainTab1Fragment.class.getSimpleName();
    private static final String PARAM = "param";
    private String mParam;

    private Toolbar mToolbar;
    private TextView mTXSearch;
    public static MainTab1Fragment newInstance(String param) {
        MainTab1Fragment fragment = new MainTab1Fragment();
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
        View view = inflater.inflate(R.layout.tab1_zixun, container,false);
        //在fragment中使用oncreateOptionsMenu时需要在onCrateView中添加此方法，否则不会调用
        setHasOptionsMenu(true);
        findViews(view);
        initToolbar();
        return view;
    }

    private void findViews(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.tb1_toolbar);
        mTXSearch = view.findViewById(R.id.main_tab1_search);
        mTXSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * initiate ToolBars
     */
    protected void initToolbar() {
//        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.mipmap.ic_slide_icon);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

}