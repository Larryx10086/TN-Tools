package com.taboola.tn.api20tester.model;

public class Thumnail {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Thumnail{" +
                "url='" + url + '\'' +
                '}';
    }
}
