package com.yifanwu.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Yifan.Wu on 3/20/2021
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        // sort based on cost difference
        // no need to sort ; just do a quick select the length / 2 item so left would be half going to A
        Arrays.sort(costs, Comparator.comparingInt(o -> (o[0] - o[1])));
        int minCost = 0;
        for (int i = 0; i < costs.length / 2; i++) {  // first half goes to A
            minCost += costs[i][0];
        }
        for (int i = costs.length / 2; i < costs.length; i++) {
            minCost += costs[i][1];
        }
        return minCost;
    }
}
