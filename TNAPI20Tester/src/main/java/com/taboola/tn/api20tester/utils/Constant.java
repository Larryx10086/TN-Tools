package com.taboola.tn.api20tester.utils;

public class Constant {

    public static final int SUCCESS = 1;
    public static final int FAILURE = -1;
//    public static final String PUBLISHER_NAME = "sdk-tester-demo";
//    public static final String PUBLISHER_NAME = "360-minus1-india-english";
    public static final String PUBLISHER_NAME = "sdk-tester";
//    public static final String  API_KEY = "d39df1418f5a4819c9eae2ca02595d57de98c246";
    public static final String  API_KEY = "0f09214e03ade7f3931d3c7755325d7f4aa94ec6";

    public static String TN_API20_URL = "https://api.taboola.com/2.0/json/publisher-name/recommendations.get";
    public static String WIDGET_REQUEST_BODY_JSON = "{\n" +
            "   \"placements\":[\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 1\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 2\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 3\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Thumbnails SC 4\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"app\":{\n" +
            "      \"type\":\"MOBILE\",\n" +
            "      \"apiKey\":\"d39df1418f5a4819c9eae2ca02595d57de98c246\",\n" +
            "      \"origin\":\"CLIENT\",\n" +
            "      \"name\":\"sdk-tester-demo\"\n" +
            "   },\n" +
            "   \"source\":{\n" +
            "      \"type\":\"home\",\n" +
            "      \"id\":\"Browser\",\n" +
            "      \"url\":\"http://example.com\"\n" +
            "   },\n" +
            "   \"view\":{\n" +
            "      \"id\":\"view-id\"\n" +
            "   },\n" +
            "   \"user\":{\n" +
            "      \"session\":\"init\",\n" +
            "      \"realip\":\"real-ip\",\n" +
            "      \"agent\":\"Mozilla%2F5.0+(Windows+NT+10.0%3B+Win64%3B +x64%3B+ServiceUI+13)+AppleWebKit%2F537.36+(KHTML%2C+like+ Gecko)+Chrome%2F64.0.3282.140+Safari%2F537.36+Edge%2F17.17 134\",\n" +
            "      \"device\":\"device-id\"\n" +
            "   }\n" +
            "}";

    public static String ARTICLE__REQUEST_BODY_JSON = "{\n" +
            "   \"placements\":[\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 1\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 2\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 3\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Thumbnails SC 4\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"Editorial Trending Thumbnails 5\",\n" +
            "         \"recCount\":1,\n" +
            "         \"organicType\":\"mix\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"app\":{\n" +
            "      \"type\":\"MOBILE\",\n" +
            "      \"apiKey\":\"d39df1418f5a4819c9eae2ca02595d57de98c246\",\n" +
            "      \"origin\":\"CLIENT\",\n" +
            "      \"name\":\"sdk-tester\"\n" +
            "   },\n" +
            "   \"source\":{\n" +
            "      \"type\":\"home\",\n" +
            "      \"id\":\"Browser\",\n" +
            "      \"url\":\"http://example.com\"\n" +
            "   },\n" +
            "   \"view\":{\n" +
            "      \"id\":\"view-id\"\n" +
            "   },\n" +
            "   \"user\":{\n" +
            "      \"session\":\"init\",\n" +
            "      \"realip\":\"real-ip\",\n" +
            "      \"agent\":\"Mozilla%2F5.0+(Windows+NT+10.0%3B+Win64%3B +x64%3B+ServiceUI+13)+AppleWebKit%2F537.36+(KHTML%2C+like+ Gecko)+Chrome%2F64.0.3282.140+Safari%2F537.36+Edge%2F17.17 134\",\n" +
            "      \"device\":\"device-id\"\n" +
            "   }\n" +
            "}";
}
