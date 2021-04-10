package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/31/2021
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int bitmask;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            bitmask = 1 << i;
            int count = 0;
            for (int num : nums) {
                if ((bitmask & num) == bitmask) {
                    count++;
                }
            }
            if (count % 3 != 0) {
                res |= bitmask;
            }
        }
        return res;
    }
}
