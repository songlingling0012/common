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


}
