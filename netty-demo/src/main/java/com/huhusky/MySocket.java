/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky;

import java.io.*;
import java.net.Socket;

/**
 * MySocket
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 11:17
 */
public class MySocket {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8899);
            OutputStream outputStream = socket.getOutputStream();
            String message = "a";
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            System.out.println("get message from server: " + sb);

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
