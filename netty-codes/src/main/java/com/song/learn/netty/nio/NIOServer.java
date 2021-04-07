package com.song.learn.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description :
 * Create by : Songlingling
 * Create time : 2021-03-18 22:53:21
 */
public class NIOServer {

    // 保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<SocketChannel>();

    public static void main(String[] args) throws IOException {

        // 创建NIO ServerSocketChannel,类似于：ServerSocket
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        serverSocket.socket().bind(new InetSocketAddress(9000));

        // 设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("服务端启动成功。。。。");

        while (true) {
            // 非阻塞模式accept方法不会阻塞，否则会阻塞
            // NIO的非阻塞是由OS内部实现的，底层调用了Linux内核的accpet函数
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("连接成功。。。。");
                // 设置为非阻塞
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }

            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();

            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                // 非阻塞模式read方法不会阻塞，否则会阻塞
                int len = sc.read(byteBuffer);
                if (len > 0) {
                    System.out.println("接收到消息："+new String(byteBuffer.array()));
                }else if(len==-1){
                    // 如果客户端断开，将socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接。。。");
                }

            }
        }


    }

}
