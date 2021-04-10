package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/5/2021
 */
public class NumberOfClosedIslands {

    public int closedIsland(int[][] grid) {
        // defense from invalid and corner cases
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0 && !visited[row][col]) {
                    if (isClosedIslandDfs(grid, visited, row, col)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean isClosedIslandDfs(int[][] grid, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        // four direction DFS
        int up = row - 1;
        int down = row + 1;
        int left = col -1;
        int right = col + 1;
        boolean result = true;
        if (up >= 0 && !visited[up][col] && grid[up][col] == 0) {
            result = isClosedIslandDfs(grid, visited, up, col);
        }
        if (down < grid.length && !visited[down][col] && grid[down][col] == 0) {
            result = isClosedIslandDfs(grid, visited, down, col) && result;
        }
        if (left >= 0 && !visited[row][left] && grid[row][left] == 0) {
            result = isClosedIslandDfs(grid, visited, row, left) && result;
        }
        if (right < grid[0].length && !visited[row][right] && grid[row][right] == 0) {
            result = isClosedIslandDfs(grid, visited, row, right) && result;
        }
        if (up < 0 || down >= grid.length || left < 0 || right >= grid[0].length) {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        NumberOfClosedIslands numberOfClosedIslands = new NumberOfClosedIslands();
        int res = numberOfClosedIslands.closedIsland(new int[][] {
                {0,0,1,1,0,1,0,0,1,0},
                {1,1,0,1,1,0,1,1,1,0},
                {1,0,1,1,1,0,0,1,1,0},
                {0,1,1,0,0,0,0,1,0,1},
                {0,0,0,0,0,0,1,1,1,0},
                {0,1,0,1,0,1,0,1,1,1},
                {1,0,1,0,1,1,0,0,0,1},
                {1,1,1,1,1,1,0,0,0,0},
                {1,1,1,0,0,1,0,1,0,1},
                {1,1,1,0,1,1,0,1,1,0}
        });
    }

}
