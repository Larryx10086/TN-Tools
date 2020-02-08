package com.celltick.apac.news.threads;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.celltick.apac.news.R;
import com.celltick.apac.news.app.StarNewsApp;
import com.celltick.apac.news.datepicker.DateFormatUtils;
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

public class NBACurrentScheduleRequestThread extends Thread{
	private static final String TAG = NBACurrentScheduleRequestThread.class.getSimpleName();

	private Handler mHandler;
	private String mSMString = "";
	private String URL;

	private String start_date;
	private String end_date;
	private String cricket_type;


	public NBACurrentScheduleRequestThread(Handler handler) {
		this.mHandler = handler;
	}
	public NBACurrentScheduleRequestThread(Handler handler, String start_date, String end_date, String cricket_type) {
		this.mHandler = handler;
		this.start_date = start_date;
		this.end_date = end_date;
		this.cricket_type = cricket_type;
	}


	@Override
	public void run() {
		if (!(cricket_type == null)){
			URL = getCricketCurrentScheduleURL()
					+ "&competitions="+cricket_type
					+ "&startDate=" + DateFormatUtils.y_M_d2M_d_y(start_date)
					+ "&endDate=" + DateFormatUtils.y_M_d2M_d_y(end_date);
		}else {
			URL = getCricketCurrentScheduleURL();
		}

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
					sendMSG(Constant.NBA_ERR_RETURN_NO_CONNECTION,StarNewsApp.getContext().getString(R.string.err_return_no_connection));
				} else if (errString.contains("timed out") || errString.contains("timeout")){
					sendMSG(Constant.NBA_ERR_RETURN_TIMEOUT, StarNewsApp.getContext().getString(R.string.err_return_timeout));
				} else {
					sendMSG(Constant.NBA_ERR_RETURN_UNKNOWN, StarNewsApp.getContext().getString(R.string.err_return_unknown));
				}
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.d(TAG,"onResponse");
				String jsonData = response.body().string();
				mSMString = jsonData;
				sendMSG(Constant.NBA_SUCCESS,mSMString);
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


	private String getCricketCurrentScheduleURL(){
		return ApiUrl.getCricketCurrentScheduleURL()
				+"&userId="+SharedPreferencesUtils.getInstance().getString(Constant.UUID);
	}

}
