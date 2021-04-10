package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/31/2021
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
