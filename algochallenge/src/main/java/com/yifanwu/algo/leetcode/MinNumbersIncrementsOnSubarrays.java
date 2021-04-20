package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/18/2021
 */
public class MinNumbersIncrementsOnSubarrays {
    public int minNumberOperations(int[] target) {
        // defense from invalid and corner
        if (target.length == 0) return 0;
        if (target.length == 1) return target[0];
        int cost = 0;
        int min = 0;
        int max = target[0];
        int prev = target[0];
        for (int i = 1; i < target.length; i++) {
            int num = target[i];
            if (num > prev) {
                cost += max;
                min = prev;
                max = num - min;
            }
            prev = num;
        }
        cost += max;
        return cost;
    }
}
