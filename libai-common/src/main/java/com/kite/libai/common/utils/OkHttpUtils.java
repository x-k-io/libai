package com.kite.libai.common.utils;


import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {

    private static final OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 发送GET请求
     *
     * @param url 请求URL
     * @return 响应结果
     * @throws IOException 网络异常
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
            throw new IOException("Unexpected response code: " + response);
        }
    }

    /**
     * 发送带参数的GET请求
     *
     * @param url    请求URL
     * @param params 请求参数
     * @return 响应结果
     * @throws IOException 网络异常
     */
    public static String get(String url, Map<String, String> params) throws IOException {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        String fullUrl = httpUrlBuilder.build().toString();
        return get(fullUrl);
    }

    /**
     * 发送POST请求
     *
     * @param url  请求URL
     * @param json 请求JSON数据
     * @return 响应结果
     * @throws IOException 网络异常
     */
    public static String postJson(String url, String json) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON);
        return post(url, body);
    }

    /**
     * 发送表单POST请求
     *
     * @param url    请求URL
     * @param params 表单参数
     * @return 响应结果
     * @throws IOException 网络异常
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody body = formBuilder.build();
        return post(url, body);
    }

    /**
     * 通用POST请求方法
     *
     * @param url  请求URL
     * @param body 请求体
     * @return 响应结果
     * @throws IOException 网络异常
     */
    private static String post(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
            throw new IOException("Unexpected response code: " + response);
        }
    }
}
