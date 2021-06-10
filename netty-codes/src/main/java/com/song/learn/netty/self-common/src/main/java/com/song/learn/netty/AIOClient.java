package com.song.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/3/19 16:46
 */
public class AIOClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",9000)).get();

        socketChannel.write(ByteBuffer.wrap("HelloServer!".getBytes()));

        ByteBuffer buffer = ByteBuffer.allocate(512);

        Integer len = socketChannel.read(buffer).get();

        if(len != -1){
            System.out.println("客户端收到消息："+new String(buffer.array(),0,len));
        }

    }

}
