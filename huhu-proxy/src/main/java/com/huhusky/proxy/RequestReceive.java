/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky.proxy;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * RequestReceive
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-08 13:47
 */
public class RequestReceive {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = null;
        System.out.println("启动web服务");
        while (true) {
            socket = serverSocket.accept();
            Thread thread = new Thread(new HttpServerThread(socket));
            thread.start();
        }
    }

    private class HttpServerThread implements Runnable{

        Socket socket;

        HttpServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream is = null;
            OutputStream os = null;
            BufferedReader br = null;
            try {
                is = this.socket.getInputStream();
                os = this.socket.getOutputStream();
                br = new BufferedReader(new InputStreamReader(is));
//                String info = IOUtils.toString(is, "UTF-8");
                int i = 1;
                String line = br.readLine();
                while (null != line && br.ready()) {
                    System.out.println("line " + i + ": " + line);
                    line = br.readLine();
                    i++;
                }
                String reply = "HTTP/1.1 200\n";
                reply += "Content-type:text/html\n\n";
                reply += "服务器返回的消息";
                os.write(reply.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                    is.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
