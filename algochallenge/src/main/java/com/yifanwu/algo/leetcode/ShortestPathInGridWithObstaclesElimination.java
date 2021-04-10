package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Yifan.Wu on 4/7/2021
 */
public class ShortestPathInGridWithObstaclesElimination {

    public int shortestPath(int[][] grid, int k) {
        // defesnse from invalid and corner cases

        // remember distance and number of removal
        // distance must be in increasing order and removal in decreasing order
        List<DistRem>[][] dRArr = new List[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                dRArr[row][col] = new ArrayList<>();
            }
        }
        Queue<Coord> curQ = new ArrayDeque<>();
        Queue<Coord> nextQ = new ArrayDeque<>();
        dRArr[0][0].add(new DistRem(0, 0));
        curQ.add(new Coord(0,0));
        while (!curQ.isEmpty()) {
            Coord coord = curQ.poll();
            if (coord.row == grid.length - 1 && coord.col == grid[0].length - 1) break; // reach exit
            DistRem distRem = dRArr[coord.row][coord.col].get(dRArr[coord.row][coord.col].size() - 1);
            int nextDist = distRem.dist + 1;
            int up = coord.row - 1;
            int down = coord.row + 1;
            int left = coord.col - 1;
            int right = coord.col + 1;

            int nextRow = up;
            int nextCol = coord.col;
            // refactor
            if (nextRow >= 0) {
                List<DistRem> distRemList = dRArr[nextRow][nextCol];
                DistRem prevDistRem = null;
                if (!distRemList.isEmpty()) {
                    prevDistRem = dRArr[nextRow][nextCol].get(dRArr[nextRow][nextCol].size() - 1);
                }
                int nextRem = (grid[nextRow][nextCol] == 0) ? distRem.rem : distRem.rem + 1;
                if (nextRem <= k) {
                    if (prevDistRem == null || prevDistRem.rem > nextRem) {
                        dRArr[nextRow][nextCol].add(new DistRem(nextDist, nextRem));
                        if (prevDistRem == null || prevDistRem.dist < nextDist) { // if equal then there is one already in the nextQ
                            nextQ.add(new Coord(nextRow, nextCol));
                        }
                    }
                }
            }
            nextRow = down;
            if (nextRow < grid.length) {
                List<DistRem> distRemList = dRArr[nextRow][nextCol];
                DistRem prevDistRem = null;
                if (!distRemList.isEmpty()) {
                    prevDistRem = dRArr[nextRow][nextCol].get(dRArr[nextRow][nextCol].size() - 1);
                }
                int nextRem = (grid[nextRow][nextCol] == 0) ? distRem.rem : distRem.rem + 1;
                if (nextRem <= k) {
                    if (prevDistRem == null || prevDistRem.rem > nextRem) {
                        dRArr[nextRow][nextCol].add(new DistRem(nextDist, nextRem));
                        if (prevDistRem == null || prevDistRem.dist < nextDist) { // if equal then there is one already in the nextQ
                            nextQ.add(new Coord(nextRow, nextCol));
                        }
                    }
                }
            }
            nextRow = coord.row;
            nextCol = left;
            if (nextCol >= 0) {
                List<DistRem> distRemList = dRArr[nextRow][nextCol];
                DistRem prevDistRem = null;
                if (!distRemList.isEmpty()) {
                    prevDistRem = dRArr[nextRow][nextCol].get(dRArr[nextRow][nextCol].size() - 1);
                }
                int nextRem = (grid[nextRow][nextCol] == 0) ? distRem.rem : distRem.rem + 1;
                if (nextRem <= k) {
                    if (prevDistRem == null || prevDistRem.rem > nextRem) {
                        dRArr[nextRow][nextCol].add(new DistRem(nextDist, nextRem));
                        if (prevDistRem == null || prevDistRem.dist < nextDist) { // if equal then there is one already in the nextQ
                            nextQ.add(new Coord(nextRow, nextCol));
                        }
                    }
                }
            }
            nextCol = right;
            if (nextCol < grid[0].length) {
                List<DistRem> distRemList = dRArr[nextRow][nextCol];
                DistRem prevDistRem = null;
                if (!distRemList.isEmpty()) {
                    prevDistRem = dRArr[nextRow][nextCol].get(dRArr[nextRow][nextCol].size() - 1);
                }
                int nextRem = (grid[nextRow][nextCol] == 0) ? distRem.rem : distRem.rem + 1;
                if (nextRem <= k) {
                    if (prevDistRem == null || prevDistRem.rem > nextRem) {
                        dRArr[nextRow][nextCol].add(new DistRem(nextDist, nextRem));
                        if (prevDistRem == null || prevDistRem.dist < nextDist) { // if equal then there is one already in the nextQ
                            nextQ.add(new Coord(nextRow, nextCol));
                        }
                    }
                }
            }

            if (curQ.isEmpty()) {
                curQ = nextQ;
                nextQ = new ArrayDeque<>();
            }
        }
        if (dRArr[dRArr.length - 1][dRArr[0].length - 1].isEmpty()) return -1;
        return dRArr[dRArr.length - 1][dRArr[0].length - 1].get(0).dist;
    }

    private static class DistRem {  // descriptive name in real code
        private final int dist;
        private final int rem;

        public DistRem(int dist, int rem) {
            this.dist = dist;
            this.rem = rem;
        }
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
