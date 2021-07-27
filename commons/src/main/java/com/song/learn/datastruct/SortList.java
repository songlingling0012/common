package com.song.learn.datastruct;

import java.util.Arrays;

/**
 * @Author: Song_
 * @Description: 排序
 * @Date: 2021/7/23 14:51
 */
public class SortList {

    /**
     * 1. 冒泡排序:每一次遍历将最大值排到最后
     * 时间复杂度：O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {

        if (arr.length <= 1) {
            return new int[]{};
        }

        for (int i = 0; i < arr.length; ++i) {

            boolean sortFlag = false;

            for (int j = 0; j < arr.length - i - 1; ++j) {

                if (arr[j] > arr[j + 1]) {
                    // 交换
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    sortFlag = true;
                }
            }
            // 无数据交换，提前退出
            if (!sortFlag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 2. 插入排序：每次将待插入数值在已有序数组中找到合适的位置，并插入
     * 时间复杂度：O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {

        int n = arr.length;

        for (int i = 1; i < n; i++) {

            // 待插入的值
            int value = arr[i];
            int j = i - 1;

            // 找到有序部分的插入位置
            for (; j >= 0; j--) {
                if (value < arr[j]) {
                    // 移动数据
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 插入数据
            arr[j + 1] = value;
        }
        return arr;
    }

    /**
     * 3. 选择排序：每次从未排序部分找到最小值，插入已排序部分的末尾
     * 时间复杂度：O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] chooseSort(int[] arr) {

        int n = arr.length;

        if (n < 1) {
            return arr;
        }

        for (int i = 0; i < n; i++) {

            // 未排序部分
            int j = i + 1;
            int minIndex = i;
            for (; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
        return arr;
    }

    /**
     * 4. 归并排序：分而治之
     * 时间复杂度：O(nlogn)-平均
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {

        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        return merge2Arr(mergeSort(left), mergeSort(right));
    }

    /**
     * 将两个有序数组合并成一个有序数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] merge2Arr(int[] arr1, int[] arr2) {

        int[] res = new int[arr1.length + arr2.length];

        for (int index = 0, i = 0, j = 0; index < res.length; index++) {
            if (i >= arr1.length) {
                res[index] = arr2[j++];
            } else if (j >= arr2.length) {
                res[index] = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                res[index] = arr2[j++];
            } else {
                res[index] = arr1[i++];
            }
        }
        return res;
    }

    /**
     * 5. 快速排序：选择排序区间的任一数字作为分区点
     * 最坏时间复杂度：O(n^2)，平均时间复杂度：O(nlogn)
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return arr;
        }
        int partIndex = quickSortPartition(arr, startIndex, endIndex);
        quickSort(arr, startIndex, partIndex - 1);
        quickSort(arr, partIndex + 1, endIndex);
        return arr;
    }

    /**
     * 获取分区点：pivot元素所在数组下标
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int quickSortPartition(int[] arr, int startIndex, int endIndex) {

        // 将数组最后一个元素作为基准元素
        int pivot = arr[endIndex];

        int index = startIndex;

        for (int j = startIndex; j < endIndex; j++) {
            if (arr[j] < pivot) {
                // 比基准元素小
                swap(arr, index, j);
                index++;
            }
        }
        swap(arr, index, endIndex);

        return index;
    }


    /**
     * 6. 计数排序：借助数组统计每个数组每个数字出现的次数，数组下标表示对应的数字
     * 说明：计数排序不适用于数字范围特别大的情况
     *
     * @param arr
     * @return
     */
    public static int[] countingSort(int[] arr) {

        int n = arr.length;

        if (n <= 1) {
            return arr;
        }
        // 查找数组中的数据范围
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 申请计数数组
        int[] countArr = new int[max + 1];
        // 计数
        for (int i = 0; i < n; i++) {
            countArr[arr[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; i++) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }

        int[] sortArr = new int[n];
        for(int i = n-1;i>=0;i--){
            int index = countArr[arr[i]]-1;
            sortArr[index] = arr[i];
            countArr[arr[i]]--;
        }

        return sortArr;
    }


    /**
     * 交换数组两个值
     * @param arr
     * @param index1
     * @param index2
     */
    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {

        int[] res = countingSort(new int[]{3, 3, 5, 2, 39, 6, 2} );

        System.out.println(Arrays.toString(res));

    }


}
