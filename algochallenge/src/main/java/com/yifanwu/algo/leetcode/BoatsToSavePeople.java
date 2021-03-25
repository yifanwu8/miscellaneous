package com.yifanwu.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Yifan.Wu on 3/20/2021
 */
public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int boat = 0;
        int i = 0;
        for (int j = people.length - 1; i <= j; j--, boat++) {
            if (i != j && (people[i] + people[j] <= limit)) {
                i++;
            }
        }
        return boat;
    }
}
