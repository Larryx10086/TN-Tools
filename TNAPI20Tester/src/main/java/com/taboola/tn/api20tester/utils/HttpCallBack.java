package com.taboola.tn.api20tester.utils;

public interface HttpCallBack {
    void onSuccess(String resposeBody);
    void onFailure(String exceptionMsg);
}
