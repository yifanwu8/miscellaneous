package com.yifanwu.algo.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * @author Yifan.Wu on 3/11/2021
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <=1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() { // sort on index-0 element
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> list = new LinkedList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (prev[1] >= intervals[i][0]) { // i overlap with prev
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else { // no overlap
                list.add(prev);
                prev = intervals[i];
            }
        }
        list.add(prev);
        int[][] arr = new int[list.size()][];
        return list.toArray(arr);
    }
}
