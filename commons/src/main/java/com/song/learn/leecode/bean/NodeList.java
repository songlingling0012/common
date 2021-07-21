package com.song.learn.leecode.bean;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/2 15:51
 */
public class NodeList {


    public int val;
    public NodeList left;
    public NodeList right;

    public NodeList() {}

    public NodeList(int _val) {
        val = _val;
    }

    public NodeList(int _val,NodeList _left,NodeList _right) {
        val = _val;
        left = _left;
        right = _right;
    }


}
