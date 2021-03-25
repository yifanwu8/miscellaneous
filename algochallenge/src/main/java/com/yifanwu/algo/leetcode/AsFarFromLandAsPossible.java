package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BFS
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 * @author Yifan.Wu on 3/16/2021
 */
public class AsFarFromLandAsPossible {

    public int maxDistance(int[][] grid) {
        if (grid.length == 0) return -1;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        Queue<Coordinate> currentQ = new ArrayDeque<>();
        Queue<Coordinate> nextQ = new ArrayDeque<>();

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] != 0) {
                    visited[row][column] = true;
                    currentQ.add(new Coordinate(row, column));
                }
            }
        }
        int distance = 0;
        while (!currentQ.isEmpty()) {
            Coordinate coordinate = currentQ.poll();
            int up = coordinate.row - 1;
            int down = coordinate.row + 1;
            int left = coordinate.column - 1;
            int right = coordinate.column + 1;

            if (up >= 0 && !visited[up][coordinate.column]) {
                visited[up][coordinate.column] = true;
                nextQ.add(new Coordinate(up, coordinate.column));
            }
            if (down < grid.length && !visited[down][coordinate.column]) {
                visited[down][coordinate.column] = true;
                nextQ.add(new Coordinate(down, coordinate.column));
            }
            if (left >= 0 && !visited[coordinate.row][left]) {
                visited[coordinate.row][left] = true;
                nextQ.add(new Coordinate(coordinate.row, left));
            }
            if (right < grid[0].length && !visited[coordinate.row][right]) {
                visited[coordinate.row][right] = true;
                nextQ.add(new Coordinate(coordinate.row, right));
            }

            if (currentQ.isEmpty()) {
                currentQ = nextQ;
                nextQ = new ArrayDeque<>();
                distance++;
            }
        }
        if (distance <= 1) {
            return -1;
        } else {
            return distance - 1;
        }

    }

    private static class Coordinate {
        private final int row, column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
