package com.yifanwu.algo.leetcode;

import java.util.Random;

/**
 * @author Yifan.Wu on 4/15/2021
 */
public class RandomPickWithWeight {
    private int[] sum;
    private final Random r = new Random();

    public RandomPickWithWeight(int[] w) {
        // w is not empty
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int val = r.nextInt(sum[sum.length - 1]) + 1;
        return binarySearch(val, 0, sum.length - 1);
    }

    private int binarySearch(int v, int lo, int hi) { // binary search the value that is equal or larger than v
        if (lo > hi) return lo;
        int mid = lo + (hi - lo) / 2;
        if (sum[mid] == v) return mid;
        if (v < sum[mid]) {
            return binarySearch(v, lo, mid - 1);
        } else {
            return binarySearch(v, mid + 1, hi);
        }
    }
}
