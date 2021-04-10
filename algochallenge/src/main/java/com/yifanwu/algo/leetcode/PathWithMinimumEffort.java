package com.yifanwu.algo.leetcode;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * diksjra spt
 * @author Yifan.Wu on 3/29/2021
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        // defense from invalid/corner cases
        if (heights.length == 0 || heights[0].length == 0) return 0;

        PriorityQueue<DistanceOfCell> pq = new PriorityQueue<>();
        int[][] distances = new int[heights.length][heights[0].length];
        for (int row = 0; row < heights.length; row++) {
            for (int col = 0; col < heights[0].length; col++) {
                distances[row][col]= Integer.MAX_VALUE;
            }
        }
        distances[0][0] = 0;

        pq.add(new DistanceOfCell(0, 0, 0));
        while (!pq.isEmpty()) {
            DistanceOfCell currentShortesCell = pq.poll();
            // stop if we find the last cell
            if (currentShortesCell.row == heights.length - 1 && currentShortesCell.col == heights[0].length - 1) {
                break;
            }
            int up = currentShortesCell.row - 1;
            int down = currentShortesCell.row + 1;
            int left = currentShortesCell.col - 1;
            int right = currentShortesCell.col + 1;
            // refactor
            if (up >= 0) {
                int curRow = up;
                int curCol = currentShortesCell.col;
                int distance = Math.max(currentShortesCell.distance,
                        Math.abs(heights[currentShortesCell.row][currentShortesCell.col] - heights[curRow][curCol]));
                if (distance < distances[curRow][curCol]) {
                    distances[curRow][curCol] = distance;
                    pq.remove(new DistanceOfCell(curRow, curCol, 0));
                    pq.add(new DistanceOfCell(curRow, curCol, distance));
                }
            }
            if (down < heights.length) {
                int curRow = down;
                int curCol = currentShortesCell.col;
                int distance = Math.max(currentShortesCell.distance,
                        Math.abs(heights[currentShortesCell.row][currentShortesCell.col] - heights[curRow][curCol]));
                if (distance < distances[curRow][curCol]) {
                    distances[curRow][curCol] = distance;
                    pq.remove(new DistanceOfCell(curRow, curCol, 0));
                    pq.add(new DistanceOfCell(curRow, curCol, distance));
                }
            }
            if (left >= 0) {
                int curRow = currentShortesCell.row;
                int curCol = left;
                int distance = Math.max(currentShortesCell.distance,
                        Math.abs(heights[currentShortesCell.row][currentShortesCell.col] - heights[curRow][curCol]));
                if (distance < distances[curRow][curCol]) {
                    distances[curRow][curCol] = distance;
                    pq.remove(new DistanceOfCell(curRow, curCol, 0));
                    pq.add(new DistanceOfCell(curRow, curCol, distance));
                }
            }
            if (right < heights[0].length) {
                int curRow = currentShortesCell.row;
                int curCol = right;
                int distance = Math.max(currentShortesCell.distance,
                        Math.abs(heights[currentShortesCell.row][currentShortesCell.col] - heights[curRow][curCol]));
                if (distance < distances[curRow][curCol]) {
                    distances[curRow][curCol] = distance;
                    pq.remove(new DistanceOfCell(curRow, curCol, 0));
                    pq.add(new DistanceOfCell(curRow, curCol, distance));
                }
            }
        }
        return distances[heights.length - 1][heights[0].length - 1];
    }

    private static class DistanceOfCell implements Comparable<DistanceOfCell> {
        private final int row;
        private final int col;
        private final int distance;

        public DistanceOfCell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        @Override
        public int compareTo(DistanceOfCell o) {
            return Integer.compare(distance, o.distance);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DistanceOfCell)) return false;
            DistanceOfCell that = (DistanceOfCell) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void main(String[] args) {
        PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();
        int res = pathWithMinimumEffort.minimumEffortPath(new int[][]
                {{1,2,2}
                ,{3,8,2}
                ,{5,3,5}});
    }
}
