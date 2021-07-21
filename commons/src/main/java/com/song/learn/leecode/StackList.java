package com.song.learn.leecode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/21 10:41
 */
public class StackList {


    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> min;

    /**
     * initialize your data structure here.
     */
    public StackList() {
        stack = new ArrayDeque<Integer>();
        min = new ArrayDeque<Integer>();
    }

    public void push(int val) {
        stack.push(val);
        if (val < stack.peek() || min.isEmpty()) {
            min.push(val);
        } else {
            min.push(min.peek());
        }

    }

    public void pop() {
        stack.pop();
        min.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }


    /**
     * 682. 棒球比赛
     * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
     * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
     * 整数 x - 表示本回合新获得分数 x
     * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
     * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * 请你返回记录中所有得分的总和。
     *
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {

        int coreSummed = 0;

        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < ops.length; i++) {

            String op = ops[i];
            if ("+".equals(op)) {
                // 前两次之和
                String pre = stack.pop();
                String prepre = stack.peek();
                stack.push(pre);
                stack.push( String.valueOf(Integer.valueOf(pre) + Integer.valueOf(prepre)));
                coreSummed += Integer.valueOf(pre) + Integer.valueOf(prepre);

            } else if ("D".equals(op)) {
                // 前一次2倍
                String cur = String.valueOf(Integer.valueOf(stack.peek()) * 2);
                stack.push(cur);
                coreSummed += Integer.valueOf(cur);

            } else if (("C".equals(op))) {
                // 前一次无效
                coreSummed -= Integer.valueOf(stack.pop());
            } else {
                stack.push(op);
                coreSummed += Integer.valueOf(op);
            }
            System.out.println(coreSummed+"======"+op);

        }
        return coreSummed;


    }

    public static void main(String[] args) {

        calPoints(new String[]{"5","2","C","D","+"});
        System.exit(0);
        StackList stack = new StackList();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin() + "--------");

        System.out.println("======================");
        stack.pop();
        System.out.println(stack.top() + "======================");
        System.out.println(stack.getMin() + "======================");

    }
}
