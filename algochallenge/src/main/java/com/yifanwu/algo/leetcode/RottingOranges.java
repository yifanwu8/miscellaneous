package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Yifan.Wu on 4/6/2021
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        // defense from invalid and corner cases

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Coord> curQ = new ArrayDeque<>();
        Queue<Coord> nextQ = new ArrayDeque<>();
        int total = 0;
        // scan through for rotten and fresh
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] != 0) total++;
                if (grid[row][col] == 2) curQ.add(new Coord(row, col));
            }
        }
        if (total == 0) return 0;
        int bad = 0;
        int min = -1;
        while (!curQ.isEmpty()) {
            Coord rot = curQ.poll();
            bad++;
            int up = rot.row - 1;
            int down = rot.row + 1;
            int left = rot.col - 1;
            int right = rot.col + 1;
            if (up >= 0 && grid[up][rot.col] == 1 && !visited[up][rot.col]) {
                visited[up][rot.col] = true;
                nextQ.add(new Coord(up, rot.col));
            }
            if (down < grid.length && grid[down][rot.col] == 1 && !visited[down][rot.col]) {
                visited[down][rot.col] = true;
                nextQ.add(new Coord(down, rot.col));
            }
            if (left >= 0 && grid[rot.row][left] == 1 && !visited[rot.row][left]) {
                visited[rot.row][left] = true;
                nextQ.add(new Coord(rot.row, left));
            }
            if (right < grid[0].length && grid[rot.row][right] == 1 && !visited[rot.row][right]) {
                visited[rot.row][right] = true;
                nextQ.add(new Coord(rot.row, right));
            }
            if (curQ.isEmpty()) {
                min++;
                curQ = nextQ;
                nextQ = new ArrayDeque<>();
            }
        }
        return (bad == total) ? min : -1;
    }

    private static class Coord {
        private final int row;
        private final int col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
