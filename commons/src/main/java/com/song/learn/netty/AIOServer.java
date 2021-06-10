package com.song.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/3/19 16:32
 */
public class AIOServer {


    public static void main(String[] args) throws IOException, InterruptedException {

        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));

        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                try {
                    System.out.println("s----" + Thread.currentThread().getName());

                    // 此处接受客户端连接，否则客户端连接不上服务器
                    serverSocketChannel.accept(attachment, this);

                    System.out.println(socketChannel.getRemoteAddress());

                    ByteBuffer buffer = ByteBuffer.allocate(1023);

                    socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer1) {
                            System.out.println("t-----"+Thread.currentThread().getName());
                            buffer.flip();
                            System.out.println(new String(buffer.array(),0,result));
                            // 向客户端发送数据
                            socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }

        });

        System.out.println("p----"+Thread.currentThread().getName());

        Thread.sleep(Integer.MAX_VALUE);

    }


}
