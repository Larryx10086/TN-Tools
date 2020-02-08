package com.guiqian.jiangxin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guiqian.jiangxin.fragments.MainTab1Fragment;
import com.guiqian.jiangxin.fragments.MainTab2Fragment;
import com.guiqian.jiangxin.fragments.MainTab3Fragment;
import com.guiqian.jiangxin.fragments.MainTab4Fragment;
import com.guiqian.jiangxin.osutils.StatusBarUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout mMainTabContainer;
    private RadioGroup mMainTabsRadioGroup;
    private RadioButton mMainTab1_ZixunRb;
    private RadioButton mMainTab2_ZihuiRb;
    private RadioButton mMainTab3_ActivityRb;
    private RadioButton mMainTab4_MineRb;
    private static final String savedTab = "savedTab";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarMode(this,true, R.color.colorPrimary);
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState == null) {
            //默认将第一个RadioButton设为选中
            mMainTab1_ZixunRb.performClick();
        } else {
            RadioButton radioButton = (RadioButton) findViewById(savedInstanceState.getInt(savedTab));
            radioButton.performClick();
        }

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(savedTab, mMainTabsRadioGroup.getCheckedRadioButtonId());
        RadioButton radioButton = (RadioButton) findViewById(mMainTabsRadioGroup.getCheckedRadioButtonId());
        //删除下面这行，不然容易发生重影
//        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_drawer_zixun) {
            // Handle the camera action
        } else if (id == R.id.nav_drawer_profession) {

        } else if (id == R.id.nav_drawer_article) {

        } else if (id == R.id.nav_drawer_answer) {

        }else if (id == R.id.nav_drawer_topic) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void initView() {
        mMainTabContainer = (FrameLayout) findViewById(R.id.fragment_container); //tab上方的区域
        mMainTabsRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_tabs_radios);  //底部的四个tab
        mMainTab1_ZixunRb = (RadioButton) findViewById(R.id.tab1_rb_zixun);
        mMainTab2_ZihuiRb = (RadioButton) findViewById(R.id.tab2_rb_zihui);
        mMainTab3_ActivityRb = (RadioButton) findViewById(R.id.tab3_rb_activity);
        mMainTab4_MineRb = (RadioButton) findViewById(R.id.tab4_rb_mine);

        //监听事件：为底部的RadioGroup绑定状态改变的监听事件
        mMainTabsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.tab1_rb_zixun:
                        index = 0;
                        break;
                    case R.id.tab2_rb_zihui:
                        index = 1;
                        break;
                    case R.id.tab3_rb_activity:
                        index = 2;
                        break;
                    case R.id.tab4_rb_mine:
                        index = 3;
                        break;
                }
                //通过fragments这个adapter还有index来替换帧布局中的内容
                Fragment fragment = (Fragment) fragments.instantiateItem(mMainTabContainer, index);
                //一开始将帧布局中 的内容设置为第一个
                fragments.setPrimaryItem(mMainTabContainer, 0, fragment);
                fragments.finishUpdate(mMainTabContainer);

            }
        });
    }

    //用adapter来管理四个Fragment界面的变化,这里用的Fragment都是v4包里面的
    FragmentPagerAdapter fragments = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return 4;//一共有四个Fragment
        }

        //进行Fragment的初始化
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0://资讯
                    fragment = MainTab1Fragment.newInstance("FirstFragment");
                    break;
                case 1://资汇
                    fragment = MainTab2Fragment.newInstance("SecondFragment");
                    break;
                case 2://活动
                    fragment = MainTab3Fragment.newInstance("ThirdFragment");
                    break;
                case 3://我的
                    fragment = MainTab4Fragment.newInstance("ForthFragment");
                    break;

            }

            return fragment;
        }
    };

}
