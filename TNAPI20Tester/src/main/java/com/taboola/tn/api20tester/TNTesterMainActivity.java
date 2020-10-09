package com.taboola.tn.api20tester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.taboola.tn.api20tester.activities.ArticleExampleActivity;
import com.taboola.tn.api20tester.activities.MultiExampleActivity;
import com.taboola.tn.api20tester.activities.WidgetExampleActivity;
import com.taboola.tn.api20tester.utils.SharedPreferencesUtils;
import com.taboola.tn.api20tester.utils.UUIDGenerator;

public class TNTesterMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnWidgetExample,mBtnArticleExample,mBtnMultiExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferencesUtils.getInstance().putString("UUID", UUIDGenerator.getUUID32());

        mBtnWidgetExample = findViewById(R.id.btn_wdg_example);
        mBtnArticleExample = findViewById(R.id.btn_artical_example);
        mBtnMultiExample = findViewById(R.id.btn_multi_example);
        mBtnWidgetExample.setOnClickListener(this);
        mBtnArticleExample.setOnClickListener(this);
        mBtnMultiExample.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_wdg_example:
                this.startActivity(new Intent(this, WidgetExampleActivity.class));
                break;
            case R.id.btn_artical_example:
                this.startActivity(new Intent(this, ArticleExampleActivity.class));
                break;
            case R.id.btn_multi_example:
                this.startActivity(new Intent(this, MultiExampleActivity.class));
                break;
        }
    }

}
