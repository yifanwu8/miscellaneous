package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author Yifan.Wu on 4/13/2021
 */
public class CarFleet2 {
    public double[] getCollisionTimes(int[][] cars) {
        // defense from invalid and corner cases

        // stack time always decreasing
        Deque<CarTime> deq = new ArrayDeque<>();  // position, speed, answer, index
        double[] res = new double[cars.length];
        for (int i = cars.length - 1; i >= 0; i--) {
            double time = Double.MAX_VALUE;
            while (!deq.isEmpty()) {
                CarTime prev = deq.peekFirst();
                time = Double.MAX_VALUE;
                if (prev.spd < cars[i][1]) { // will catch up
                    time = 1.0d * (prev.pos - cars[i][0]) / (cars[i][1] - prev.spd);
                }
                if (time >= prev.time) { // previous car can be safely removed, not going to impact anymore
                    deq.removeFirst();
                    res[prev.ind] = (prev.time == Double.MAX_VALUE) ? -1 : prev.time;
                } else {
                    break;
                }
            }
            deq.addFirst(new CarTime(cars[i][0], cars[i][1], i, time));
        }

        for (CarTime car : deq) {
            res[car.ind] = (car.time == Double.MAX_VALUE) ? -1 : car.time;
        }
        return res;
    }

    private static class CarTime {
        private final int pos;
        private final int spd;
        private final int ind;
        private final double time;

        public CarTime(int pos, int spd, int ind, double time) {
            this.pos = pos;
            this.spd = spd;
            this.ind = ind;
            this.time = time;
        }
    }
}
