package com.song.learn.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Song_
 * @Description: IO多路复用：多个IO操作使用一个Selector（选择器）去询问哪些IO准备好了
 * Selector负责通知IO，他们再自己去请求内核数据
 * @Date: 2021/3/5 18:00
 */
public class NIOEchoServer {

    public static void main(String[] args) throws IOException {

        // 创建共用Selector
        Selector selector = Selector.open();

        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 绑定7777端口
        serverSocketChannel.bind(new InetSocketAddress(7777));

        // 设置为非阻塞式的
        serverSocketChannel.configureBlocking(false);

        // 将Channel注册到Selector上，并注册Accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("----- server start ------");

        while (1 > 0) {

            // 第一阶段阻塞：Selector阻塞
            selector.select();

            // 如果使用的是select(timeout) 或 selectNow()需要判断返回值是否大于0

            // 有就绪的Channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历selectKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();


            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                //如果是Accept事件
                if(selectionKey.isAcceptable()){
                    // 强制转换成ServerSocketChannel
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                }

            }



        }


    }


}
