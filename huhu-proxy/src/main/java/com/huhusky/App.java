package com.huhusky;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App implements Runnable
{
    ServerSocket ss ;



    public static void main( String[] args ) {
        System.out.println( "test http parse" );
        new App();
    }

    @Override
    public void run() {
        System.out.println("listening to 8080 ");
        while (true) {
            try {
                Socket s ;
                s = ss.accept();
                client(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public App() {
        try {
            ss = new ServerSocket(8080);
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void client(Socket s) {
        //todo: parse protocol
        InputStream is;
        OutputStream os;
        try {
            os = s.getOutputStream();
            is = s.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
            while ((len = is.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println("get message from client: " + sb);
            os.write("HTTP/1.1 200 ok\n".getBytes("UTF-8"));
            is.close();
            os.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void client1(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
            while ((len = inputStream.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println("get message from client: " + sb);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Hello Client,I get the message.".getBytes("UTF-8"));

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
