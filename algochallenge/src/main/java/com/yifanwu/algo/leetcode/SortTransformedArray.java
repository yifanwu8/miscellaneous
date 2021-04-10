package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan.Wu on 4/8/2021
 */
public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        // defense from invalid and corner cases
        // corner case a=0
        int[] res = new int[nums.length];
        if (a == 0) {
            for (int i = 0; i < nums.length; i++) {
                int j;
                if (b >= 0) {
                    j = i;
                } else {
                    j = nums.length - 1 - i;
                }
                res[j] =  b * nums[i] + c;
            }
            return res;
        }

        double axis = (-1.0d) * b / (2.0d * a);
        int indAxis = 0;
        // can use binary search to save some time here but does not reduce overall time complexity
        while (Double.compare(nums[indAxis], axis) <= 0) {
            indAxis++;
        }
        // indAxis at a number larger than the axis
        int i = indAxis - 1; // smaller or equal than axis; scan to the left
        int j = indAxis;  // scan to the right
        List<Integer> sortRes = new ArrayList<>();
        while (i >= 0 || j < nums.length) {
            double diffLeft = Double.MAX_VALUE;
            if (i >= 0) {
                diffLeft = axis - nums[i];
            }
            double diffRight = Double.MAX_VALUE;
            if (j < nums.length) {
                diffRight = nums[j] - axis;
            }
            int nextInd;
            if (diffLeft <= diffRight) {
                nextInd = i--;
            } else {
                nextInd = j++;
            }
            int val = a * nums[nextInd] * nums[nextInd] + b * nums[nextInd] + c;
            sortRes.add(val);
        }
        for (int v = 0; v < nums.length; v++) {
            int w;
            if (a > 0) w = v;
            else w = nums.length - 1 - v;
            res[w] = sortRes.get(v);
        }
        return res;
    }
}
