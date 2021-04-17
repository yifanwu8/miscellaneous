package com.yifanwu.algo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Yifan.Wu on 4/12/2021
 */
public class MinDiffBetweenLargestSmallestValThreeMoves {
    public int minDifference(int[] nums) {
        // defense from invalid and corner cases
        if (nums.length <= 4) return 0;
        // max pq
        PriorityQueue<Integer> min4MaxQ = new PriorityQueue<>(Comparator.reverseOrder());
        // min pq
        PriorityQueue<Integer> max4MinQ = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            min4MaxQ.add(nums[i]);
            max4MinQ.add(nums[i]);
        }
        for (int i = 4; i < nums.length; i++) {
            min4(min4MaxQ, nums[i]);
            max4(max4MinQ, nums[i]);
        }

        int[] max4Arr = new int[4];
        int[] min4Arr = new int[4];
        for (int i = 3; i >= 0; i--) {
            min4Arr[i] = min4MaxQ.poll();
            max4Arr[i] = max4MinQ.poll();
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            res = Math.min(res, max4Arr[i] - min4Arr[3 - i]);
        }
        return res;
    }

    // refactor
    private void min4(PriorityQueue<Integer> minQ, int val) {
        if (val < minQ.peek()) {
            minQ.poll();
            minQ.add(val);
        }
    }

    private void max4(PriorityQueue<Integer> maxQ, int val) {
        if (val > maxQ.peek()) {
            maxQ.poll();
            maxQ.add(val);
        }
    }

    public static void main(String[] args) {
        MinDiffBetweenLargestSmallestValThreeMoves mov = new MinDiffBetweenLargestSmallestValThreeMoves();
        mov.minDifference(new int[]{1,5,0,10,14});
    }
}
