package com.song.learn.leecode.bean;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/6/8 17:02
 */
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
