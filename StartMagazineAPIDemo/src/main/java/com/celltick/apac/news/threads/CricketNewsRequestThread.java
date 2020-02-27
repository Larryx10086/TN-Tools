package com.celltick.apac.news.threads;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.celltick.apac.news.R;
import com.celltick.apac.news.app.StarNewsApp;
import com.celltick.apac.news.util.ApiUrl;
import com.celltick.apac.news.util.Constant;
import com.celltick.apac.news.util.SharedPreferencesUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CricketNewsRequestThread extends Thread{
	private static final String TAG = CricketNewsRequestThread.class.getSimpleName();

	private Handler mHandler;
	private String mSMString = "";
	private String URL;
	private int offset;

	public CricketNewsRequestThread(Handler handler,int offset) {
		this.mHandler = handler;
		this.offset = offset;
	}

	@Override
	public void run() {

		URL = getHeaderRequestBaseURL() +"&limit="+Constant.NUM_SM_1ST_REQUEST+"&offset="+offset+"&tag=epcwc";

		Log.d(TAG,"URL="+URL);

		requestSMAPI(URL);

	}

	public void sendMSG(int what,String info){
		Message msg = mHandler.obtainMessage();
		msg.what = what;
		msg.obj = info;
		mHandler.sendMessage(msg);
	}

	public void requestSMAPI(String requestURL){
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS);
		client.setWriteTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS);
		client.setReadTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS);
		Request request = new Request.Builder().url(requestURL).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				Log.d(TAG,"onFailure, "+e.getMessage());
				String errString = e.getMessage();

				if (errString.startsWith("Unable to resolve host \"contentapi.celltick.com\"")){
					sendMSG(Constant.ERR_RETURN_NO_CONNECTION,StarNewsApp.getContext().getString(R.string.err_return_no_connection));
				} else if (errString.contains("timed out") || errString.contains("timeout")){
					sendMSG(Constant.ERR_RETURN_TIMEOUT, StarNewsApp.getContext().getString(R.string.err_return_timeout));
				} else {
					sendMSG(Constant.ERR_RETURN_UNKNOWN, StarNewsApp.getContext().getString(R.string.err_return_unknown));
				}
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.d(TAG,"onResponse");
				String jsonData = response.body().string();
//				Log.d(TAG,"jsonData = " + jsonData);
				mSMString = jsonData;
				sendMSG(Constant.SUCCESS,mSMString);
			}
		});


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


	private String getHeaderRequestBaseURL(){
		return ApiUrl.getMagazineBaseURL()
				+"&userId="+SharedPreferencesUtils.getInstance().getString(Constant.UUID)
//				+"&countryCode="+ SharedPreferencesUtils.getInstance().getString(Constant.COUNTRY_CODE)
//				+"&language=" + SharedPreferencesUtils.getInstance().getString(Constant.LANGUAGE_CODE);
				+"&language=en";
	}

}
