package com.celltick.apac.news.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.celltick.apac.news.MainActivity;
import com.celltick.apac.news.R;
import com.celltick.apac.news.model.CategoryBean;
import com.celltick.apac.news.util.SharedPreferencesUtils;
import com.celltick.apac.news.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larryx on 5/27/2018.
 */

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private List<CategoryBean> categoryList;
    private String mCategoryListStr;
    private ArrayList<String> mCategories;
    private Spinner mAppTypeSpnnier;
    private Spinner mAppOriginSpnnier;
    private Spinner mSourceTypeSpnnier;
    private Spinner mSourceTypeRecCount;
    private Button mButtonGo;
    private EditText mPublisherName, mApiKey, mAppName,mRealip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //隐藏标题栏以及状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        SharedPreferencesUtils.getInstance().putString("session","init");

        mPublisherName = (EditText) findViewById(R.id.publisher_name);
        mApiKey = (EditText) findViewById(R.id.api_key);
        mAppName = (EditText) findViewById(R.id.app_name);
        mRealip = (EditText) findViewById(R.id.real_ip);

        mAppTypeSpnnier = (Spinner)findViewById(R.id.app_type);
        mAppOriginSpnnier = (Spinner)findViewById(R.id.app_origin);
        mSourceTypeSpnnier = (Spinner)findViewById(R.id.source_type);
        mSourceTypeRecCount = (Spinner)findViewById(R.id.rec_count);
        mButtonGo = (Button) findViewById(R.id.btn_go);

        ArrayAdapter<String> mAppTypeSpnnierAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item);
        String level1[] = getResources().getStringArray(R.array.apptype);//资源文件
        for (int i = 0; i < level1.length; i++) {
            mAppTypeSpnnierAdapter.add(level1[i]);
        }
        mAppTypeSpnnierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAppTypeSpnnier.setAdapter(mAppTypeSpnnierAdapter);

        ArrayAdapter<String> mAppOriginSpnnierAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item);
        String level2[] = getResources().getStringArray(R.array.apporigin);//资源文件
        for (int i = 0; i < level2.length; i++) {
            mAppOriginSpnnierAdapter.add(level2[i]);
        }
        mAppOriginSpnnierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAppOriginSpnnier.setAdapter(mAppOriginSpnnierAdapter);

        ArrayAdapter<String> mSourceTypeSpnnierAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item);
        String level3[] = getResources().getStringArray(R.array.sourcetype);//资源文件
        for (int i = 0; i < level3.length; i++) {
            mSourceTypeSpnnierAdapter.add(level3[i]);
        }
        mSourceTypeSpnnierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSourceTypeSpnnier.setAdapter(mSourceTypeSpnnierAdapter);

        ArrayAdapter<String> mRecCountSpnnierAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item);
        String level4[] = getResources().getStringArray(R.array.recCount);//资源文件
        for (int i = 0; i < level4.length; i++) {
            mRecCountSpnnierAdapter.add(level4[i]);
        }
        mRecCountSpnnierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSourceTypeRecCount.setAdapter(mRecCountSpnnierAdapter);

        mButtonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String publisherName = mPublisherName.getText().toString();
                String apiKey = mApiKey.getText().toString();
                String appName = mAppName.getText().toString();
                String realIp = mRealip.getText().toString();
                String appType = mAppTypeSpnnier.getSelectedItem().toString();
                String appOrigin = mAppOriginSpnnier.getSelectedItem().toString();
                String sourceType = mSourceTypeSpnnier.getSelectedItem().toString();
                String recCount = mSourceTypeRecCount.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                bundle.putString("publisherName", publisherName);
                bundle.putString("apiKey",apiKey);
                bundle.putString("appName",appName);
                bundle.putString("realIp",realIp);
                bundle.putString("appType",appType);
                bundle.putString("appOrigin",appOrigin);
                bundle.putString("sourceType",sourceType);
                bundle.putString("recCount",recCount);

                intent.putExtra("configurations",bundle);

                startActivity(intent);
//                finish();

            }
        });

    }
}
