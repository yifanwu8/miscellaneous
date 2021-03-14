package com.yifanwu.algo.leetcode;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * quick select
 * @author Yifan.Wu on 3/9/2021
 */
public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
//        defense against invalid and corner case
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException();
        }

        int correctPosition = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;

        for (int position = partition(nums, lo, hi); ; position = partition(nums, lo, hi)) {

            if (position == correctPosition) {
                return nums[position];
            } else if (position < correctPosition) {
                lo = position + 1;
            } else {
                hi = position - 1;
            }
        }
    }

//    return partitioned value index in the array
//    guarantee the index value is at its final position in sorted array
//    lo and hi are inclusive
    private int partition(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        if (lo > hi) {
            throw new IllegalArgumentException();
        }
        int i = lo + 1;
        int j = hi;
        // stop condition is i, j cross
        while (i <= j){
            // guarantees index < i are <= pivot; index > j are >pivot
            if (nums[j] > nums[lo]) {
                j--;
            } else {
                exchange(nums, i, j);
                i++;
            }
        }
        exchange(nums, lo, j);
        return j;
    }

    private void exchange(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray cl = new KthLargestElementInAnArray();
        cl.findKthLargest(new int[] {3,2,1,5,6,4}, 2);
    }
}
