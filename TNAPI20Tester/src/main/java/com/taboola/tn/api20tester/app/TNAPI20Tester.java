package com.taboola.tn.api20tester.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**

 */

public class TNAPI20Tester extends Application {
    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Fresco.initialize(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Fresco.shutDown();
//        ActiveAndroid.dispose();
    }
}
