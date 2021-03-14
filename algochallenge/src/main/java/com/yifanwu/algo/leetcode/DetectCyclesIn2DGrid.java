package com.yifanwu.algo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * DFS
 * @author Yifan.Wu on 3/12/2021
 */
public class DetectCyclesIn2DGrid {

    public boolean containsCycle(char[][] grid) {
        if (grid.length <= 0) return false;
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Integer> depthMap = new HashMap<>();
        boolean res = false;
        int sourceDepth = 0;

        for (int row = 0; row < grid.length; row++ ) {
            for (int column = 0; column < grid[row].length; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                if (!visited.contains(coordinate)) { // if not visited
                    depthMap.clear();
                    res = dfs(grid, visited, depthMap, coordinate, sourceDepth);
                    if (res) {
                        return res;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, Set<Coordinate> visited, Map<Coordinate, Integer> depthMap,
                        Coordinate currCoordinate, int currDepth) {
        if (visited.contains(currCoordinate)) { // visited before
            return depthMap.containsKey(currCoordinate) && currDepth - depthMap.get(currCoordinate) >= 3;
        }
        boolean res = false;

        visited.add(currCoordinate);
        depthMap.put(currCoordinate, currDepth);
        int up = currCoordinate.getRow() - 1;
        int down = currCoordinate.getRow() + 1;
        int left = currCoordinate.getColumn() - 1;
        int right = currCoordinate.getColumn() + 1;

        if (isPassage(grid, grid[currCoordinate.getRow()][currCoordinate.getColumn()], up, currCoordinate.getColumn())) {
            res = dfs(grid, visited, depthMap, new Coordinate(up, currCoordinate.getColumn()), currDepth + 1);
            if (res) {
                return true;
            }
        }
        if (isPassage(grid, grid[currCoordinate.getRow()][currCoordinate.getColumn()], down, currCoordinate.getColumn())) {
            res = dfs(grid, visited, depthMap, new Coordinate(down, currCoordinate.getColumn()), currDepth + 1);
            if (res) {
                return true;
            }
        }
        if (isPassage(grid, grid[currCoordinate.getRow()][currCoordinate.getColumn()], currCoordinate.getRow(), left)) {
            res = dfs(grid, visited, depthMap, new Coordinate(currCoordinate.getRow(), left), currDepth + 1);
            if (res) {
                return true;
            }
        }
        if (isPassage(grid, grid[currCoordinate.getRow()][currCoordinate.getColumn()], currCoordinate.getRow(), right)) {
            res = dfs(grid, visited, depthMap, new Coordinate(currCoordinate.getRow(), right), currDepth + 1);
            if (res) {
                return true;
            }
        }
        return false;
    }

    private boolean isPassage(char[][] grid, char value, int row, int column) {
        return (row >= 0 && row < grid.length) && (column >= 0 && column < grid[row].length) && value == grid[row][column];
    }

    private static class Coordinate {
        private final int row, column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinate)) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }

}
