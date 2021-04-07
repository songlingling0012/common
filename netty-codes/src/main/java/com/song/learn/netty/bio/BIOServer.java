package com.song.learn.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description :
 * Create by : Songlingling
 * Create time : 2021-03-18 22:25:04
 */
public class BIOServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(9000);

        while (true){
            System.out.println("客户端等待被连接.....");
            // 阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接......");

            // handler(clientSocket);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }


    }


    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备好读取客户端数据 ...");

        // 接受客户端的数据，阻塞方法，没有数据就阻塞
        int read = clientSocket.getInputStream().read(bytes);

        System.out.println("读数据结束... ");

        if(read!=-1){
            System.out.println("接收到客户端数据："+new String(bytes,0,read));
        }

        clientSocket.getOutputStream().write("hello client ".getBytes());
        clientSocket.getOutputStream().flush();

    }





}
