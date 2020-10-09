package com.taboola.tn.api20tester.utils;

import android.graphics.Rect;
import android.view.View;
import android.widget.ScrollView;

public class WindowManagerUtil {
    /**
     * 判断View是否可见
     *
     * @param target   View
     * @param judgeAll 为true时,判断View全部可见才返回true
     * @return boolean
     */
    public static boolean isVisibleLocal(View target, boolean judgeAll) {
        Rect rect = new Rect();
        target.getLocalVisibleRect(rect);
        if (judgeAll) {
            return rect.top == 0;
        }else {
            return rect.top >= 0;
        }
    }

    public static boolean isViewVisible(ScrollView scrollView,View target) {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        if (target.getLocalVisibleRect(scrollBounds)) {
            return true;
        } else {
            return  false;
        }
    }

}
