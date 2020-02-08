package com.celltick.apac.news.threads;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.celltick.apac.news.util.ApiUrl;
import com.celltick.apac.news.util.Constant;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class FWCBottomArticleRequestThread extends Thread{
	private static final String TAG = FWCBottomArticleRequestThread.class.getSimpleName();

	private Handler mHandler;
	private String mSMString= "";
	private String mTitle;
	private int offset;
	private String mNextContentItem;

	public FWCBottomArticleRequestThread(Handler handler, int offset) {
		this.mHandler = handler;
		this.offset = offset;
	}

	public FWCBottomArticleRequestThread(String title, Handler handler, String nextContentItem) {
		this.mHandler = handler;
		this.mNextContentItem = nextContentItem;
		this.mTitle = title;
	}

	@Override
	public void run() {

		String url = ApiUrl.getFWCContentURL()+"&limit="+Constant.NUM_SM_1ST_REQUEST + "&offset=" +mNextContentItem;
//		String url = ApiUrl.getFWCContentURL()+"&limit="+Constant.NUM_SM_NORMAL_REQUEST + "&offset=" +mNextContentItem;
		Log.d(TAG,"bottom url = " +url);
		if (url != null && url != "") {
			if (isGettingJsonSucceedFromStartMagazineAPI(url)) {
				sendMSG(Constant.SUCCESS,Constant.BOTTOM_LOAD,mSMString);
			} else if (!isGettingJsonSucceedFromStartMagazineAPI(url)) {
				{
					sendMSG(Constant.ERR_RETURN,Constant.BOTTOM_LOAD,"");
				}

			}
		}


	}

	public void sendMSG(int what,int flag,String info){
		Message msg = mHandler.obtainMessage();
		msg.what = what;
		msg.arg1 = flag;
		msg.obj = info;
		mHandler.sendMessage(msg);
	}

	public boolean isGettingJsonSucceedFromStartMagazineAPI(String requestURL){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(requestURL).build();
		try {
			Response responses = client.newCall(request).execute();
			String jsonData = responses.body().string();

			if (jsonData.contains("totalItems")) {
				mSMString = jsonData;
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
