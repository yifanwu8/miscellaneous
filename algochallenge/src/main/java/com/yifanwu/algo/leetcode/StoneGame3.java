package com.yifanwu.algo.leetcode;

/**
 * DP
 * minmax game
 * @author Yifan.Wu on 3/23/2021
 */
public class StoneGame3 {
    public String stoneGameIII(int[] stoneValue) {
        int[] collectedValue = new int[stoneValue.length];
        for (int i = 0; i < stoneValue.length; i++) {
            collectedValue[i] = Integer.MIN_VALUE;
        }
        int res = helper(stoneValue, 0, collectedValue);
        if (res == 0) return "Tie";
        if (res > 0 ) return "Alice";
        return "Bob";
    }

    private int helper(int[] values, int index, int[] collectedValue) {
        if (index >= values.length) return 0;
        if (collectedValue[index] > Integer.MIN_VALUE) {
            return collectedValue[index];
        }
        int res = values[index] - helper(values, index + 1, collectedValue);
        if (index + 1 < values.length) {
            res = Math.max(res,
                    values[index] + values[index + 1] - helper(values, index + 2, collectedValue));
        }
        if (index + 2 < values.length) {
            res = Math.max(res,
                    values[index] + values[index + 1] + values[index + 2] - helper(values, index + 3, collectedValue));
        }
        collectedValue[index] = res;
        return res;
    }
}
