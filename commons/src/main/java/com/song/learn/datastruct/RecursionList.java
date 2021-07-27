package com.song.learn.datastruct;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/27 10:56
 */
public class RecursionList {


    /**
     * 全排列---递归实现
     * @param data
     * @param n
     * @param k
     */
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " , ");
            }
            System.out.println("=======");
        }

        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;

            printPermutations(data, n, k - 1);

            tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;
        }
    }


    public static void main(String[] args) {

        int[] data = {1, 2, 3, 4};

        printPermutations(data,2,4);


    }

}
