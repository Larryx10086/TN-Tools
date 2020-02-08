package com.taboola.taboolafragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.globalNotifications.GlobalNotificationReceiver;
import com.taboola.android.listeners.TaboolaDetectAdEventsListener;
import com.taboola.android.listeners.TaboolaEventListener;
import com.taboola.android.utils.SdkDetailsHelper;

import java.util.HashMap;

public class FeedFragment extends Fragment
        implements GlobalNotificationReceiver.OnGlobalNotificationsListener,TaboolaEventListener{

    private static final String TAG = "TABOOLA-DEBUG";
    private static final String TABOOLA_VIEW_ID = "123456";

    GlobalNotificationReceiver mGlobalNotificationReceiver = new GlobalNotificationReceiver();

    private TaboolaWidget mTaboolaWidget;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.layout_feed_fragment,container,false);
        getMainActivity().onFeedListCreate(view);
        mTaboolaWidget = view.findViewById(R.id.taboola_widget_below_article);
        buildBelowArticleWidget(mTaboolaWidget);


        return view;
    }

    private void buildBelowArticleWidget(TaboolaWidget taboolaWidget) {
        taboolaWidget
                .setPublisher("meizu-browser-russia")
                .setPageType("home")
                .setPageUrl("https://blog.taboola.com")
                .setPlacement("Below Homepage Thumbnails")
                .setMode("editorial-topnews-thumbs-s")
                .setTargetType("mix")
                .setViewId(TABOOLA_VIEW_ID)
                .setInterceptScroll(true);

        taboolaWidget.getLayoutParams().height = SdkDetailsHelper.getDisplayHeight(taboolaWidget.getContext());
        taboolaWidget.setFocusable(false);
        taboolaWidget.setTaboolaEventListener(this);
        HashMap<String, String> optionalPageCommands = new HashMap<>();
//        optionalPageCommands.put("useOnlineTemplate", "true");
        optionalPageCommands.put("keepDependencies", "true");
        optionalPageCommands.put("allowNonOrganicClickOverride", "true");
        taboolaWidget.setExtraProperties(optionalPageCommands);
        taboolaWidget.fetchContent();
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        mGlobalNotificationReceiver.registerNotificationsListener(this);
        mGlobalNotificationReceiver.registerReceiver(getActivity());
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        mGlobalNotificationReceiver.unregisterNotificationsListener();
        mGlobalNotificationReceiver.unregisterReceiver(getActivity());
    }

    @Override
    public void taboolaDidReceiveAd(TaboolaWidget taboolaWidget) {
        Log.d(TAG, "taboolaDidReceiveAd() called with: taboolaWidget = [" + taboolaWidget + "]");
    }

    @Override
    public void taboolaViewResized(TaboolaWidget taboolaWidget, int height) {
        Log.d(TAG, "taboolaViewResized() called with: taboolaWidget = [" + taboolaWidget + "], height = [" + height + "]");
    }

    @Override
    public void taboolaItemDidClick(TaboolaWidget taboolaWidget) {
        Log.d(TAG, "taboolaItemDidClick() called with: taboolaWidget = [" + taboolaWidget + "]");
    }

    @Override
    public void taboolaDidFailAd(TaboolaWidget taboolaWidget, String reason) {
        Log.d(TAG, "taboolaDidFailAd() called with: taboolaWidget = [" + taboolaWidget + "], reason = [" + reason + "]");
    }




    @Override
    public boolean taboolaViewItemClickHandler(String url, boolean isOrganic) {
        Log.d(TAG, "url = " + url);
        getMainActivity().hide();
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container,
                InAppBrowser.getInstance(url)).commitAllowingStateLoss();
//        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
//                InAppBrowser.getInstance(url)).commitAllowingStateLoss();


        //Returning false - the click's default behavior is aborted. The app should display the Taboola Recommendation content on its own (for example, using an in-app browser).
        return false;
    //Returning true - the click is a standard one and is sent to the Android OS for default behavior.
//        return true;
    }

    @Override
    public void taboolaViewResizeHandler(TaboolaWidget taboolaWidget, int height) {

    }
}

