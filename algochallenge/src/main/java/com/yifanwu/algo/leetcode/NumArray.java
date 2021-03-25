package com.yifanwu.algo.leetcode;

/**
 * segment tree as Binary tree node
 * https://leetcode.com/problems/range-sum-query-mutable/
 * @author Yifan.Wu on 3/23/2021
 */
public class NumArray {
    private SegmentTreeNode root;

    public NumArray(int[] nums) {
        root = new SegmentTreeNode(nums, 0, nums.length - 1);
    }

    public void update(int index, int val) {
        root.update(index, val);
    }

    public int sumRange(int left, int right) {
        return root.getRangeSum(left, right);
    }

    private static class SegmentTreeNode {  // value can be mutated but not insert or delete
        private int sum;
        private final int start;
        private final int mid;  // mid is in the left child
        private final int end;

        private final SegmentTreeNode left;
        private final SegmentTreeNode right;

        public SegmentTreeNode(int[] nums, int first, int last) {
            start = first;
            end = last;
            mid = (last - first) / 2 + first;

            if (last == first) {
                left = null;
                right = null;
                sum = nums[first];
            } else {
                left = new SegmentTreeNode(nums, start, mid);
                right = new SegmentTreeNode(nums, mid + 1, end);
                sum = left.sum + right.sum;
            }
        }
        public int getRangeSum(int first, int last) {
            if (first > last || first > end || last < start) return 0;
            if (first <= start && last >= end) return sum;
            int res = 0;
            if (first <= mid) {
                res += left.getRangeSum(Math.max(first, start), Math.min(last, mid));
            }
            if (last > mid) {
                res += right.getRangeSum(Math.max(mid + 1, first), Math.min(last, end));
            }
            return res;
        }
        public void update(int index, int val) {
            if (start == end) { // leaf
                if (start == index) {
                    sum = val;
                    return;
                }
            }
            if (index <= mid && index >= start) {
                left.update(index, val);
                sum = left.sum + right.sum;
                return;
            }
            if (index > mid && index <= end) {
                right.update(index, val);
                sum = left.sum + right.sum;
                return;
            }
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[] {0,9,5,7,3});
        numArray.sumRange(4,4);
        numArray.sumRange(2,4);
    }
}
