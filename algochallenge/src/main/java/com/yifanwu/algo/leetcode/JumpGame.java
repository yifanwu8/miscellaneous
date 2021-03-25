package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/20/2021
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxReach < i) return false;
            int reach = i + nums[i];
            maxReach = Math.max(maxReach, reach);
            if (maxReach >= nums.length - 1) return true;
        }
        throw new IllegalStateException();
    }
}
