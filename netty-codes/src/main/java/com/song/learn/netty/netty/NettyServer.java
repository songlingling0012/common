package com.song.learn.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Description : Netty框架的目的就是让让业务逻辑从网络基础应用编码中分离出来，可以专注业务开发，从而不用写一堆类似NIO网络处理操作
 * Create by : Songlingling
 * Create time : 2021-03-22 22:55:30
 */
public class NettyServer {

    public static void main(String[] args) {

        // 创建两个线程组：bossGroup、workerGroup。含有的子线程NioEventLoop的个数默认为CPU核数的两倍

        // bossGroup只是处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // workerGroup 真正和客户端业务处理
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try {

            // 创建服务器端的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式编程来配置参数
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel作为服务器的通道实现
                    // 初始化服务器端连接队列大小，服务端处理客户端连接请求是：顺序处理，所以同一时间只能处理一个请求
                    // 多客户端同时来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {  // 创建通道初始化对象，设置初始化参数
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("com.song.learn.netty server start......");

            // 绑定一个端口并且进行同步，生成一个ChannelFuture异步对象，通过isDone（）等方法可以判断异步事件的执行情况
            // 启动服务器（并绑定端口），bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();

            channelFuture.addListener(new ChannelFutureListener() {


                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("监控端口9000成功。。。。。。");
                    }else{
                        System.out.println("监控端口9000失败。。。。。。");
                    }
                }
                
            });

            // 对通道关闭进行监听，closeFuture是异步操作，监听通道关闭
            // 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}


