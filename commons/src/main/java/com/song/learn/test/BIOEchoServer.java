package com.song.learn.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Song_
 * @Description: BIO-BlockingIO-阻塞IO---Easy服务端网络应用
 * @Date: 2021/3/5 17:36
 */
public class BIOEchoServer {

    public static void main(String[] args) throws IOException {


        // 启动服务端，绑定7777端口。（但是不能直接用）
        ServerSocket serverSocket = new ServerSocket(7777);

        System.out.println("------ server start ------");

        boolean flag = true;

        // 服务端和客户端是一对多的关系，使用while(true)模拟多个客户端连接服务端
        while (flag) {

            // 开始接受客户端链接，保存该连接用于 读取数据
            final Socket socket = serverSocket.accept();

            System.out.println(" one client conn : " + socket);

            /**
             * 启动线程处理连接数据，如果不启动线程，主线程只能处理当前连接的这一个客户端而不能同时处理多个客户端。
             * 潜在问题：一个客户端连接分配一个线程，服务端线程会随着客户端线程无限增加至资源耗尽。
             */
            /*new Thread(() -> {
                try {
                    // 读取数据
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;

                    while ((msg = reader.readLine()) != null) {
                        System.out.println("receive msg : " + msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();*/

        }

    }
}
