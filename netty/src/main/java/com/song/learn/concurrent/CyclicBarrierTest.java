package com.song.learn.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Song_
 * @Description: CyclicBarrier测试类
 *                  CyclicBarrier:阻塞调用await()的线程，计数也发生于该方法中，计数会阻塞当前线程直到计数变为0或阻塞被中断为止
 *                  可重复使用计数，即：当前计数器=0，且所有参与阻塞的线程都被唤醒之后，计数器立刻又恢复到最初设置的计数值
 * @Date: 2021/6/7 17:21
 */
public class CyclicBarrierTest   {



    public static class Tourist implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public Tourist(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            for( int i=0;i<2;i++){
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("["+i+"]"+Thread.currentThread().getName()+"check in ...");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is over...");
            }

        }
    }


    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("let's go ..."));

        Tourist tourist = new Tourist(barrier);

        new Thread(tourist,"A").start();
        new Thread(tourist,"B").start();
        new Thread(tourist,"C").start();


    }


}
