package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/28/2021
 */
public class MinCostToMergeStones {
    public int mergeStones(int[] stones, int K) {
        // defense from invalid and corner cases
        int[][] dp = new int[K + 1][stones.length];
        // initialize dp to MAX
        for (int row = 2; row <= K; row++) {
            for (int col = 0; col < stones.length; col++) {
                dp[row][col] = Integer.MAX_VALUE;
            }
        }
        return helper(stones, 0, 1, dp, K);
    }

    private int helper(int[] stones, int index, int group, int[][] dp, int K) {
        if (index >= stones.length) return -1;
        if (dp[group][index] != Integer.MAX_VALUE) return dp[group][index];
        if (group == stones.length - index) {
            dp[group][index] = 0;
            return 0;
        }
        if (group == 1 && stones.length - index <= K) {
            int res = stones.length - index < K ? -1 : sum(stones, index, stones.length - 1);
            dp[group][index] = res;
            return res;
        }
        int res;
        if (group >= stones.length - index && group != 1) {
            res = stones.length - index == group ? 0 : -1;
            dp[group][index] = res;
            return res;
        }

        int newGroup = (group == 1) ? K - 1 : group - 1;

        int value = Integer.MAX_VALUE;
        int t1 = helper(stones, index + K, newGroup, dp, K);
        if (t1 != -1) {
            value = t1 + sum(stones, index, index + K - 1);
        }

        int t2 = helper(stones, index + 1, newGroup, dp, K);
        if (t2 != -1) {
            value = Math.min(value, t2);
        }
        if (value != Integer.MAX_VALUE) {
            res = value + sum(stones, index, stones.length - 1);
        } else {
            res = -1;
        }
        dp[group][index] = res;
        return res;
    }

    private int sum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        MinCostToMergeStones minCostToMergeStones = new MinCostToMergeStones();
        int res = minCostToMergeStones.mergeStones(new int[]{3,5,1,2,6}, 3);
    }
}
