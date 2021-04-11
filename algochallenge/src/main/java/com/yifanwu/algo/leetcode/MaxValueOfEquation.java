package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Yifan.Wu on 4/11/2021
 */
public class MaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        // defense from invalid and corner cases

        int max = Integer.MIN_VALUE;
        // Y decreasing and X increasing inside
        Deque<YMinusX> deque = new ArrayDeque<>();
        for (int[] point : points) {
            while (!deque.isEmpty() && point[0] - deque.peekFirst().x > k) { // remove those out of k with sliding window
                deque.removeFirst();
            }
            if (!deque.isEmpty()) {
                max = Math.max(max, deque.peekFirst().yMinusX + point[0] + point[1]);
            }
            int curYminusX = point[1] - point[0];
            while (!deque.isEmpty() && curYminusX >= deque.peekLast().yMinusX) {
                // remove rightmost as long as YminusX  is smaller or equal than current point
                deque.removeLast();
            }
            deque.addLast(new YMinusX(curYminusX, point[0]));
        }
        return max;
    }

    private static class YMinusX {
        private final int yMinusX;
        private final int x;

        public YMinusX(int yMinusX, int x) {
            this.yMinusX = yMinusX;
            this.x = x;
        }
    }

}
