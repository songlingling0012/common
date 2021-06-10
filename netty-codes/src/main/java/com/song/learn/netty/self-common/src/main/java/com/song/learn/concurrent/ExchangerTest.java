package com.song.learn.concurrent;

import java.util.concurrent.Exchanger;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/6/7 11:31
 */
public class ExchangerTest {

    public static void main(String[] args) {

        final Exchanger<Integer> exchanger = new Exchanger<>();

        for(int i=0;i<10;i++){
            final Integer num = i;
            new Thread(){
                @Override
                public void run() {

                    System.out.println("I:Thread_"+this.getName()+",我的数据是："+num);

                    try {
                        Integer exchangeNum = exchanger.exchange(num);
                        Thread.sleep(1000);
                        System.out.println("我是线程：Thread_"+this.getName()+",我的原数据是："+num+",交换后的数据是："+exchangeNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }


    }




}
