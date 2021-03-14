package com.yifanwu.algo.leetcode;

import java.util.Objects;

/**
 * @author Yifan.Wu on 3/7/2021
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
//        defense
        if (Objects.isNull(nums) || nums.length <= 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }

        }
        return i + 1;
    }
}
