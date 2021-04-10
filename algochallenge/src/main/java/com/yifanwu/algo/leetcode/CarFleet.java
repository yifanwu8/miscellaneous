package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yifan.Wu on 4/6/2021
 */
public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        // defense from invalid and corner cases
        if (position == null || position.length == 0)  return 0;
        List<PositionAndTime> arr = new ArrayList<>(position.length);
        for (int i = 0; i < position.length; i++) {
            PositionAndTime pT =
                    new PositionAndTime(position[i], (1.0d * target - position[i]) / speed[i]); // descriptive name in real code
            arr.add(pT);
        }
        arr.sort(Comparator.comparingInt(o -> o.position));
        // 2 pointers scan
        int right = arr.size() - 1;
        int left = right;
        int count = 1;
        while (left >= 0) {
            if (Double.compare(arr.get(left).remTime, arr.get(right).remTime) <= 0) { // left would catch up right
                left--;
            } else { // left not catch up right
                count++;
                right = left;
            }
        }
        return count;
    }

    private static class PositionAndTime {
        private final int position;
        private final double remTime;

        public PositionAndTime(int position, double remTime) {
            this.position = position;
            this.remTime = remTime;
        }
    }
}
