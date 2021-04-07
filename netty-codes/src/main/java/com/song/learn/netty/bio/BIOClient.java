package com.song.learn.netty.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * Description :
 * Create by : Songlingling
 * Create time : 2021-03-18 22:34:44
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",9000);

        // 向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();

        System.out.println("客户端向服务端发送数据结束。。");

        byte[] bytes = new byte[1024];

        // 接受服务端传回的数据---->阻塞操作，如果连接后不做数据的读写操作会导致线程阻塞，浪费资源；如果线程很多会导致服务器线程太多压力太大
        int read = socket.getInputStream().read(bytes);

        System.out.println("客户端接受到服务端的数据："+new String(bytes));

        socket.close();


    }
}
