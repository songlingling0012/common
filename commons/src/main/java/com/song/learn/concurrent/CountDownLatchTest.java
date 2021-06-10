package com.song.learn.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Song_
 * @Description: CountDownLatch测试类
 *              CountDownLatch:阻塞调用await()的线程，而执行countDown()的线程在执行完计数器计数之后并不会阻塞，而是继续往下执行
 *              且只能使用计数一次
 * @Date: 2021/6/7 17:09
 */
public class CountDownLatchTest {

    private static final CountDownLatch START = new CountDownLatch(1);
    private static final CountDownLatch END = new CountDownLatch(3);


    private static class Student implements Runnable {


        @Override
        public void run() {

            try {
                // 等待考试
                System.out.println(Thread.currentThread().getName() + " is waiting。。。");
                START.await();

                // 开始考试
                TimeUnit.SECONDS.sleep(5);

                System.out.println(Thread.currentThread().getName() + " finished ... ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 交卷
                END.countDown();
                System.out.println(Thread.currentThread().getName() + " is over。。。 ");
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        new Thread(new Student(),"A").start();
        new Thread(new Student(),"B").start();
        new Thread(new Student(),"C").start();

        TimeUnit.SECONDS.sleep(5);

        System.out.println("start ===");
        // 老师宣布考试
        START.countDown();

        // 等待学生交卷
        END.await();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(" end ===");

    }


}
