package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yifan.Wu on 4/12/2021
 */
public class MaximumNumberOfVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        // defense from invalid and corner cases
        if (angle >= 360) return points.size();
        if (points.size() <= 0) return 0;

        List<Double> angles = new ArrayList<>();
        int countAtLoc = 0; // how many points at viewing location
        for (List<Integer> point : points) {
            if (point.get(0) == location.get(0) && point.get(1) == location.get(1)) {
                countAtLoc++;
            } else {
                double pangle = getAngle(location, point);
                angles.add(pangle);
                if (pangle <= angle) angles.add(pangle + 360.0d);
            }
        }
        angles.sort(Comparator.naturalOrder());
        if (angle < 0) return countAtLoc;

        int left = 0;
        int right = 0;
        int max = 0;
        while (right < angles.size()) {
            while (angles.get(right) - angles.get(left) > angle ) { // increment left pointer until within angle
                left++;
            }
            while (right < angles.size() && angles.get(right) - angles.get(left) <= angle) {
                right++;
            }
            max = Math.max(max, right - left);
        }
        return max + countAtLoc;
    }

    private double getAngle(List<Integer> p1, List<Integer> p2) {
        int dy = p2.get(1) - p1.get(1);
        int dx = p2.get(0) - p1.get(0);
        return Math.atan2(dy, dx) * (180.0d / Math.PI);
    }
}
