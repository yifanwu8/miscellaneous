package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yifan.Wu on 3/24/2021
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea,
                            getArea(grid, i, j, visited));
                }
            }
        }
        return maxArea;
    }

    private int getArea(int[][] grid, int sourceRow, int sourceColumn, boolean[][] visited) {
        visited[sourceRow][sourceColumn] = true;
        Queue<RowColumn> queue = new ArrayDeque<>();
        queue.add(new RowColumn(sourceRow, sourceColumn));
        int area = 0;
        while (!queue.isEmpty()) {
            RowColumn rowColumn = queue.poll();
            area++;
            int up = rowColumn.row - 1;
            int down = rowColumn.row + 1;
            int left = rowColumn.column - 1;
            int right = rowColumn.column + 1;
            
            if ((up) >= 0 && grid[up][rowColumn.column] == 1
                    && !visited[up][rowColumn.column]) {  //
                visited[up][rowColumn.column] = true;
                queue.add(new RowColumn(up, rowColumn.column));
            }
            if ((down) < grid.length && grid[down][rowColumn.column] == 1
                    && !visited[down][rowColumn.column]) {  //
                visited[down][rowColumn.column] = true;
                queue.add(new RowColumn(down, rowColumn.column));
            }
            if (left >= 0 && grid[rowColumn.row][left] == 1
                    && !visited[rowColumn.row][left]) {  //
                visited[rowColumn.row][left] = true;
                queue.add(new RowColumn(rowColumn.row, left));
            }
            if ((right) < grid[0].length && grid[rowColumn.row][right] == 1
                    && !visited[rowColumn.row][right]) {  //
                visited[rowColumn.row][right] = true;
                queue.add(new RowColumn(rowColumn.row, right));
            }
        }
        return area;
    }
    
    private static class RowColumn {
        private final int row;
        private final int column;

        public RowColumn(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
