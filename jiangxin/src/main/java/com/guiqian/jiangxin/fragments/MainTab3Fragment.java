package com.guiqian.jiangxin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guiqian.jiangxin.R;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class MainTab3Fragment extends Fragment {

    private static final String TAG = MainTab3Fragment.class.getSimpleName();
    private static final String PARAM = "param";
    private String mParam;

    public static MainTab3Fragment newInstance(String param) {
        MainTab3Fragment fragment = new MainTab3Fragment();
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
        View view = inflater.inflate(R.layout.tab3_activity, container,false);
        return view;
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