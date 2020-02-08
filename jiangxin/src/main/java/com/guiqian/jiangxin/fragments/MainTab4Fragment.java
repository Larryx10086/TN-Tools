package com.guiqian.jiangxin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.guiqian.jiangxin.R;

public class MainTab4Fragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MainTab4Fragment.class.getSimpleName();
    private static final String PARAM = "param";
    private String mParam;

    private TextView mMyArticle, mMyTopic,mMyQuestion, mMyVideo,
            mMyCourse,mMyNote,mMyFavor,mMyMsg,mMyFollow,mMyInteresting,mSettings;

    public static MainTab4Fragment newInstance(String param) {
        MainTab4Fragment fragment = new MainTab4Fragment();
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
        View view = inflater.inflate(R.layout.tab4_mine, container,false);
        findViews(view);
        return view;
    }

    private void findViews(View view){
        mMyArticle = view.findViewById(R.id.mine_list_my_article);
        mMyTopic = view.findViewById(R.id.mine_list_my_topic);
        mMyQuestion = view.findViewById(R.id.mine_list_my_questions);
        mMyVideo = view.findViewById(R.id.mine_list_my_video);
        mMyCourse = view.findViewById(R.id.mine_list_my_course);
        mMyNote = view.findViewById(R.id.mine_list_my_note);
        mMyFavor = view.findViewById(R.id.mine_list_my_favor);
        mMyMsg = view.findViewById(R.id.mine_list_my_msg);
        mMyFollow = view.findViewById(R.id.mine_list_my_follow);
        mMyInteresting = view.findViewById(R.id.mine_list_my_interesting);
        mSettings = view.findViewById(R.id.mine_list_settings);

        mMyArticle.setOnClickListener(this);
        mMyTopic.setOnClickListener(this);
        mMyQuestion.setOnClickListener(this);
        mMyVideo.setOnClickListener(this);
        mMyCourse.setOnClickListener(this);
        mMyNote.setOnClickListener(this);
        mMyFavor.setOnClickListener(this);
        mMyMsg.setOnClickListener(this);
        mMyFollow.setOnClickListener(this);
        mMyInteresting.setOnClickListener(this);
        mSettings.setOnClickListener(this);

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

    @Override
    public void onClick(View v){
        Toast.makeText(getActivity(),"uuuuu",Toast.LENGTH_SHORT).show();
        int id = v.getId();
        switch (id){

        }

    }

}