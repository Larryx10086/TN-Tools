package com.taboola.tn.api20tester.thread;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;

import com.taboola.tn.api20tester.utils.Constant;
import com.taboola.tn.api20tester.utils.HttpUtil;
import com.taboola.tn.api20tester.utils.NetworkUtil;
import com.taboola.tn.api20tester.utils.TimeUtil;
import com.taboola.tn.api20tester.utils.UUIDGenerator;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendEventsThread extends Thread {
	private static final String TAG = SendEventsThread.class.getSimpleName();

	private Context mContext;
	private Handler mHandler;
	private String URL;
	private String requestBodyJson;
	private String error = "error !!!";
	private HttpUtil mHttpUtil;
	private String responseStr = "";

	public SendEventsThread(Context context,String eventURL) {
		this.mContext = context;
		this.URL = eventURL;
		mHttpUtil = new HttpUtil();
	}

	public SendEventsThread(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}

	@Override
	public void run() {
		if (mHttpUtil != null) {
			mHttpUtil.sendHttpGet(URL);
		} else {
			new HttpUtil().sendHttpGet(URL);
		}

	}

}
