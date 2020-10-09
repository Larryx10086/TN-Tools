package com.taboola.tn.api20tester.utils;

import com.taboola.tn.api20tester.widget.MyScrollView;

public interface ScrollViewListener {

    void onScrollChanged(MyScrollView myScrollView, int x, int y , int oldx, int oldy);
    void onScrolledToBottom();
    void onScrolledToTop();
}