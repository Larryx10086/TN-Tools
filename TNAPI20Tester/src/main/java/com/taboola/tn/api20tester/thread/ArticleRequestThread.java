package com.taboola.tn.api20tester.thread;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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

public class ArticleRequestThread extends Thread {
	private static final String TAG = ArticleRequestThread.class.getSimpleName();

	private Context mContext;
	private Handler mHandler;
	private String URL;
	private String requestBodyJson;
	private String error = "error !!!";
	private HttpUtil mHttpUtil;
	private String responseStr = "";

	public ArticleRequestThread(Context context,HttpUtil httpUtil) {
		this.mContext = context;
		this.mHttpUtil = httpUtil;
	}

	public ArticleRequestThread(Context context,Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}

	@Override
	public void run() {
		requestBodyJson = Constant.ARTICLE__REQUEST_BODY_JSON.replace("Placement-Name","Editorial Trending");

		URL = Constant.TN_API20_URL.replace("publisher-name",Constant.PUBLISHER_NAME);
		MediaType JSON = MediaType.parse(requestBodyJson);
		String view_id = TimeUtil.getTimestamp()+"";
		requestBodyJson = requestBodyJson
			.replace("real-ip", NetworkUtil.getIPAddress(mContext))
			.replace("view-id",view_id)
			.replace("device-id", UUIDGenerator.getUUID32());

		Log.d(TAG,"view id="+view_id);
		RequestBody body = RequestBody.create(JSON, requestBodyJson);
		Log.d(TAG,"Request URL="+URL);
		Log.d(TAG,"requestBodyJson="+requestBodyJson);

//		mHttpUtil.sendHttpPost(URL,body);

		sendHttpPost(URL,body);

//		try {
//			Log.d(TAG,"=======================");
//			String response = sendHttpPost(URL,body);
//			Log.d(TAG,"responseStr = " + response);
//			if (response.code() == 200) {
//				sendMSG(Constant.SUCCESS,responseStr);
//			} else {
//				sendMSG(Constant.FAILURE,error);
//			}
//		} catch(IOException e) {
//			sendMSG(Constant.FAILURE,e.getMessage());
//		}

	}

	public void sendMSG(int what,String info) {
		Message msg = mHandler.obtainMessage();
		msg.what = what;
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
			responseStr = response.body().string();
			Log.d(TAG,"responseStr1111 = " + responseStr);
			if (response.code() == 200) {
				sendMSG(Constant.SUCCESS,responseStr);
			} else {
				sendMSG(Constant.FAILURE,error);
			}
			return responseStr;
		} catch (IOException e) {
			Log.d(TAG,"ERROR = "+ e.getMessage());
			error = e.getMessage();
			sendMSG(Constant.FAILURE,error);
			return null;
		}

	}

}
