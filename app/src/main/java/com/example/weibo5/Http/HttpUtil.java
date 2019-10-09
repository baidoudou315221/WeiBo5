package com.example.weibo5.Http;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

//    public static String sendHttpRequest(String address) throws IOException {
//        OkHttpClient client = new OkHttpClient();
////        Request request = new Request.Builder().url(address).build();
////        Response response = client.newCall(request).execute();
////        String responseData = response.body().string();
////        return responseData;
//
//
//    }
}
