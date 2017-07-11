package com.yifanwu.algo.leetcode;

import java.util.Arrays;

/**
 *  Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *The replacement must be in-place, do not allocate extra memory.
 *Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *1,2,3 → 1,3,2
 *3,2,1 → 1,2,3
 *1,1,5 → 1,5,1
 * Created by Yifan.Wu on 7/11/2017.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // corner case and validate input
        if (nums.length <= 1) {
            return;
        }

        int ind = getIndexOfReverseOrder(nums);
        if (ind == 0) { // complete reverse order
            reverse(nums, 0);
            return;
        }
        swapNextBiggerNum(nums, ind - 1);
        reverse(nums, ind);
    }

    private void swapNextBiggerNum(int[] nums, int pos) {
        int next = pos + 1;
        for (int i = pos + 1; i < nums.length; i++) {
            if (nums[i] > nums[pos] && nums[i] < nums[next]) {
                next = i;
            }
        }
        int temp = nums[next];
        nums[next] = nums[pos];
        nums[pos] = temp;
    }

    private int getIndexOfReverseOrder(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                return i;
            }
        }
        return 0;
    }

    private void reverse(int[] nums, int start) {
        Arrays.sort(nums, start, nums.length);
    }
}
