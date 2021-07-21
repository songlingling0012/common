package com.song.learn.leecode;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/5 15:19
 */
public class ClassicList {


    /**
     * 二分查找：
     *  1. 基本等值查找：初始化==》right = nums.length -1，搜索区间[left,right]，左闭右闭==》left=mid+1，right=mid-1；
     *  2. 左侧边界等值：初始化==》right = nums.length , 搜索区间[left,right),左闭右开==》left=mid+1，right=mid；
     *  3. 右侧边界等值：初始化==》right = nums.length，搜索区间[left,right),左闭右开==》left= mid+1,right= mie.
     */

    public static int left_boundBinarySearch(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    public static int firstEqualsBinarySearch(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    left = mid + 1;
                }

            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


    public static void main(String[] args) {

        int res = firstEqualsBinarySearch(new int[]{1,2,3,4,6, 6, 6, 7, 8, 9}, 6);

        System.out.println(res);


    }


}
