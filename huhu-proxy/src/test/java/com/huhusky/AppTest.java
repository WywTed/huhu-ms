package com.huhusky;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1() {

        String path = "/";
        String host = "127.0.0.1";
        int port = 8080;
        try {
            Socket socket = new Socket(host, port);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            osw.write("GET " + path + " HTTP/1.1\r\n");
            osw.write("Host: " + host + " \r\n");
            //http协议必须在报文头后面再加一个换行，通知服务器发送完成，不然服务器会一直等待
            osw.write("\r\n");
            osw.flush();
//            socket.shutdownOutput();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                System.out.println("receive_msg: " + dis.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 8080;
        // 与服务端建立连接
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            String message = "你好  yiwangzhibujian";
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
