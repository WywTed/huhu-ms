/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky.proxy;

import com.huhusky.constants.HttpStatus;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ProxyCore
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 18:32
 */
public class ProxyCore {

    public String connect(HttpRequest request) {
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(request.getFullUrl());
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            int code = connection.getResponseCode();
            if (code >= HttpStatus.Bad_REQUEST) {
                is = connection.getErrorStream();
            }else{
                is = connection.getInputStream();
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            IOUtils.copy(is, os);
            byte[] inputByteArray = IOUtils.toByteArray(is);
            IOUtils.toString(inputByteArray, "UTF-8");
            InputStream isCopy = new ByteArrayInputStream(os.toByteArray());
            result = IOUtils.toString(isCopy, "UTF-8");
            System.out.println(result);
            InputStream isCopy1 = new ByteArrayInputStream(os.toByteArray());
            result = IOUtils.toString(isCopy1, "UTF-8");
            System.out.println(result);
            /*br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            // 存放数据
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
