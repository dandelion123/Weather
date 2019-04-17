package com.mytest.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    /**
     * 发送数据请求
     * @param address 请求地址
     * @param listener 回调监听
     */
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null)
                        response.append(line);
                    if (listener != null) listener.onFinish(response.toString());
                } catch (MalformedURLException e) {
                    if (listener != null) listener.onError(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    if (listener != null) listener.onError(e);
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }
            }
        }).start();
    }

}
