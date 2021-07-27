package com.song.learn.datastruct;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/23 15:22
 */
public class BinarySearchList {

    /**
     * 基本二分查找---循环实现
     *
     * @param arr
     * @param num
     * @return
     */
    public int simpleBinarySearch(int[] arr, int num) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // int mid = left + ((right - left) >> 1 );

            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else if (arr[mid] < num) {
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 基本二分查找---递归实现
     *
     * @param arr
     * @param num
     * @return
     */
    public int simpleBinarySearchRecure(int[] arr, int num) {
        return simpleBinarySearchRecureCore(arr, num, 0, arr.length - 1);
    }

    public int simpleBinarySearchRecureCore(int[] arr, int num, int startIndex, int endIndex) {

        if (startIndex > endIndex) {
            return -1;
        }
        int mid = startIndex + (endIndex - startIndex) / 2;
        if (arr[mid] == num) {
            return mid;
        } else if (arr[mid] > num) {
            return simpleBinarySearchRecureCore(arr, num, startIndex, mid - 1);
        } else if (arr[mid] < num) {
            return simpleBinarySearchRecureCore(arr, num, mid + 1, endIndex);
        }
        return -1;
    }


    /**
     * 二分查找变形1：查找第一个等于给定值的元素
     *
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchFirstEqual(int[] arr, int num) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < num) {
                left = mid + 1;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] != num) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 二分查找变形2：查找最后一个等于给定值的元素
     *
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchLastEqual(int[] arr, int num) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < num) {
                left = mid + 1;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != num) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 二分查找变形3：查找第一个大于等于给定值的元素
     *
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchFirstBiggerOrEqaul(int[] arr, int num) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < num) {
                left = mid + 1;
            } else if (arr[mid] >= num) {
                if (mid == 0 || arr[mid - 1] < num) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 二分查找变形4：查找最后一个小于等于给定值的元素
     *
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchLastSmallerOrEqaul(int[] arr, int num) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > num) {
                right = mid - 1;
            } else if (arr[mid] <= num) {
                if (mid == arr.length - 1 || arr[mid + 1] > num) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 练习题：33. 搜索旋转排序数组
     * * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // [0,mid]有序
            if (nums[0] <= nums[mid]) {

                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // [0,mid]部分无序
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


}
