package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/14/2021
 */
public class TwoSum2InputArraySorted {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length <= 1) {
            throw new IllegalArgumentException();
        }

        int i = 0, j = numbers.length - 1;
        while (i < j) { // terminate when i == j cannot use one element twice
            if (target == numbers[i] + numbers[j]) {
                return new int[] {i + 1, j + 1};
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        throw new IllegalStateException("No anwser found");
    }
}
