package com.yifanwu.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/top-k-frequent-elements
 * @author Yifan.Wu on 3/11/2021
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= 0 || k <= 0) {
            return new int[0];
        }

        Map<Integer, Integer> table = new HashMap<>();
        for (int i : nums) {
            if (table.containsKey(i)) {
                table.put(i, table.get(i) + 1);
            } else {
                table.put(i, 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> set = table.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue =
                new PriorityQueue<>(set.size(), (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        priorityQueue.addAll(set);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.remove().getKey();
        }
        return res;
    }
}
