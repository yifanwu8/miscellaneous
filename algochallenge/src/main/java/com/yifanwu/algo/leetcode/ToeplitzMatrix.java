package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/1/2021
 */
public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        // defense from  invalid and corner cases
        if (matrix.length <= 0 || matrix[0].length <= 0) return false;

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col <matrix[0].length - 1; col++) {
                if (matrix[row][col] != matrix[row+1][col+1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
