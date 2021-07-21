package com.song.learn.leecode.bean;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/6/25 14:34
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
