package com.hao.myUtil.http;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by haojiahong on 17/5/17.
 */
@Slf4j
public class HttpRequestUtil {

    //连接远程主机超时限制（单位：毫秒）
    public static int HTTP_CONNECTION_TIMEOUT = 1000;
    //从主机读取数据超时（单位：毫秒）
    public static int HTTP_READ_TIMEOUT = 5 * 1000;

    public static String getResponseByUrl(String urlStr) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            log.info("search url is------>" + urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh");
            conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
            conn.setReadTimeout(HTTP_READ_TIMEOUT);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
            br.close();
            return res.toString();
        } catch (SocketTimeoutException e) {
            log.error("SearchUtil getResponseByUrl: 连接超时,连接地址：" + urlStr + '\n', e);
            return null;
        } catch (Exception e) {
            log.error("SearchUtil getResponseByUrl, urlStr: " + urlStr + '\n', e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


    public static void main(String[] args) {
        String result = HttpRequestUtil.getResponseByUrl("http://app.360cec.com/promotion/activity/goods/list?uid=10&cityId=383&actId=551&tagType=1");
        System.out.println(result);
    }

}
