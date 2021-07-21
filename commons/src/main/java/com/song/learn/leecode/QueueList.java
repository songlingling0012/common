package com.song.learn.leecode;

import com.song.learn.leecode.bean.ListNode;

import java.util.ArrayDeque;

/**
 * @Author: Song_
 * @Description: 循环队列
 * @Date: 2021/7/20 17:47
 */
public class QueueList {

    /**
     * 使用数组实现循环队列
     */
    private String[] items;

    private int n = 0;

    /**
     * head = 队头下标；tail = 队尾下标
     */
    private int head = 0;
    private int tail = 0;


    public QueueList(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {

        if ((tail + 1) % n == head) {
            // 队列已满，入队失败
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }


    /**
     * 出队
     * @return
     */
    public String dequeue() {
        if (head == tail) {
            // 队列为空，出队失败
            return null;
        }
        String out = items[head];
        head = (head + 1) % n;
        return out;

    }


    /**
     * 876. 链表的中间结点
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {


        if(head==null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast!=null){
            if(fast.next==null){
                return slow.next;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        Integer peek = stack.peek();

        System.out.println(peek);


    }


}
