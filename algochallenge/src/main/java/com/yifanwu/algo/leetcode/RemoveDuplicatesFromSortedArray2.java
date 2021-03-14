package com.yifanwu.algo.leetcode;

import java.util.Objects;

/**
 * @author Yifan.Wu on 3/7/2021
 */
public class RemoveDuplicatesFromSortedArray2 {

    public int removeDuplicates(int[] nums) {
//        defense
        if (Objects.isNull(nums) || nums.length <= 0) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int i = 1;
        for (int j = 2; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            } else if (nums[i] != nums[i-1]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
