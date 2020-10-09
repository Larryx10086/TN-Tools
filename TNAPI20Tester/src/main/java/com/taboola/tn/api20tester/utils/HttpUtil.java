package com.taboola.tn.api20tester.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public HttpCallBack mHttpCallBack;
    public void setHttpCallBack(HttpCallBack httpCallBack){
        this.mHttpCallBack = httpCallBack;
    }

    public void sendHttpPost(String URL, RequestBody body){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            if (response.code() == 200) {
                mHttpCallBack.onSuccess(response.body().string());
            } else {
                mHttpCallBack.onFailure(response.code()+"");
            }
        } catch (IOException e) {
            mHttpCallBack.onFailure(e.getMessage());
        }

    }

    public void sendHttpGet(String URL){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(URL)
                .get()
                .build();
        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            if (response.code() == 204) {
                mHttpCallBack.onSuccess("Sent Event Success");
            } else {
                mHttpCallBack.onFailure("Sent Event Failure");
            }
        } catch (IOException e) {
            mHttpCallBack.onFailure(e.getMessage());
        }

    }

}
