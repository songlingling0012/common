package com.song.learn.leecode;

import com.song.learn.leecode.bean.ListNode;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/19 16:38
 */
public class LinkedLists {

    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {

        ListNode res = new ListNode(0);
        res.next = head;

        ListNode tmp = res;

        while (tmp.next != null && tmp.next != null) {
            ListNode start = tmp.next;
            ListNode end = tmp.next.next;

            tmp.next = end;

            start.next = end.next;
            end.next = start;
            tmp = start;

        }
        return res.next;
    }


    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode res = new ListNode(0);
        res.next = head;

        ListNode tmpNode = res;

        int count = 0;
        while (count < n) {
            tmpNode = tmpNode.next;
            count++;
        }

        ListNode preNode = res;
        ListNode delNode = preNode.next;

        while (tmpNode.next != null) {
            preNode = preNode.next;
            delNode = delNode.next;
            tmpNode = tmpNode.next;
            count++;
        }

        if(count==n){
            return null;
        }
        System.out.println(preNode.val+":=====prenode");
        System.out.println(tmpNode.val+"=====tmpNode");
        preNode.next = delNode;
        return res.next;
    }


}
