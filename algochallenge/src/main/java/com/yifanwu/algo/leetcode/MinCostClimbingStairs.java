package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/18/2021
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) return 0;

        int sMinus2 = 0, sMinus1 = 0;
        int s = 0;
        for (int i = 2; i <= cost.length; i++) {
            s = Math.min(sMinus2 + cost[i - 2], sMinus1 + cost[i - 1]);
            sMinus2 = sMinus1;
            sMinus1 = s;
        }
        return s;
    }
}
