package com.song.learn.leecode;

import java.util.Arrays;

/**
 * @Author: Song_
 * @Description: 背包问题
 * @Date: 2021/6/7 15:07
 */
public class PackageList {


    static int count = 0;

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public static void backtrack(int[] nums, int target, int index, int sum) {

        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    /**
     * 用循环实现-递归
     */
    public static void backtrackByLoop(int[] nums, int target, int index, int sum) {

        while (index < nums.length) {

        }

    }


    /**
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     * <p>
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     * <p>
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {

        int[] arr = new int[8];

        int length = stones.length;

        if (stones.length < 1) {
            return 0;
        }
        Arrays.sort(stones);

        int pre = stones[0];

        for (int i = 1; i < stones.length; i++) {

            int cur = stones[i];

            if ( pre == cur ) {
                // 两块石头都粉碎
                pre = 0;
            }else{
                pre = Math.abs(cur - pre );
            }

        }

        return pre;


    }


}
