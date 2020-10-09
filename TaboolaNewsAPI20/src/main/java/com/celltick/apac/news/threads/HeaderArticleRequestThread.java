package com.celltick.apac.news.threads;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.celltick.apac.news.R;
import com.celltick.apac.news.app.StarNewsApp;
import com.celltick.apac.news.util.ApiUrl;
import com.celltick.apac.news.util.Constant;
import com.celltick.apac.news.util.HttpUtil;
import com.celltick.apac.news.util.SharedPreferencesUtils;
import com.celltick.apac.news.util.TimeUtil;
import com.celltick.apac.news.util.UUIDGenerator;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Timeout;

public class HeaderArticleRequestThread extends Thread{
	private static final String TAG = HeaderArticleRequestThread.class.getSimpleName();

	private Handler mHandler;
	private String URL;
	private String mCategotyName;
	private String requestBodyJson;
	private Bundle mBundle;
	private String error = "error !!!";

	public HeaderArticleRequestThread(Handler handler, String categoty, Bundle configurations) {
		this.mHandler = handler;
		this.mCategotyName = categoty;
		this.mBundle = configurations;
	}

	@Override
	public void run() {

		switch (mCategotyName){
			case "Topnews":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Topnews");
				break;
			case "Trending":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Trending");
				break;
			case "Sports":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Sports");
				break;
			case "Lifestyle":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Lifestyle");
				break;
			case "Business":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Business");
				break;
			case "Entertainment":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Entertainment");
				break;
			case "Tech":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Tech");
				break;
			case "Politics":
				requestBodyJson = Constant.REQUEST_BODY_JSON.replace("Placement-Name","Editorial Politics");
				break;
		}

		URL = Constant.TN_API20_URL.replace("publisher-name",mBundle.getString("publisherName"));
		MediaType JSON = MediaType.parse(requestBodyJson);
//		String view_id = SharedPreferencesUtils.getInstance().getString("UUID")
//				+"-"+TimeUtil.getTimestamp();
		String view_id = TimeUtil.getTimestamp()+"";
		requestBodyJson = requestBodyJson.replace("api-key",mBundle.getString("apiKey"))
			.replace("app-name",mBundle.getString("appName"))
			.replace("real-ip",mBundle.getString("realIp"))
			.replace("app-type",mBundle.getString("appType"))
			.replace("app-origin",mBundle.getString("appOrigin"))
			.replace("source-type",mBundle.getString("sourceType"))
			.replace("recCount-number",mBundle.getString("recCount"))
			.replace("view-id",view_id)
			.replace("gaid", SharedPreferencesUtils.getInstance().getString("UUID"))
			.replace("session-id",SharedPreferencesUtils.getInstance().getString("session"));

		SharedPreferencesUtils.getInstance().putString("view-id",view_id);
		Log.d(TAG,"Saved view id="+view_id);
		RequestBody body = RequestBody.create(JSON, requestBodyJson);
		Log.d(TAG,"Request URL="+URL);
		Log.d(TAG,"requestBodyJson="+requestBodyJson);
		String response = sendHttpPost(URL,body);
		Log.d(TAG,"response = " + response);

		if (response != null && response != "") {
			sendMSG(Constant.SUCCESS,Constant.HEADER_LOAD,response);
		} else {
			sendMSG(Constant.ERR_RETURN,Constant.HEADER_LOAD,error);
		}
	}

	public void sendMSG(int what,int flag,String info){
		Message msg = mHandler.obtainMessage();
		msg.what = what;
		msg.arg1 = flag;
		msg.obj = info;
		mHandler.sendMessage(msg);
	}


	private String sendHttpPost(String URL, RequestBody body){
		OkHttpClient client = new OkHttpClient();
		Request request=new Request.Builder()
				.url(URL)
				.post(body)
				.build();
		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return response.body().string();
		} catch (IOException e) {
			Log.d(TAG,"ERROR = "+ e.getMessage());
			error = e.getMessage();
			return null;
		}

	}

}
