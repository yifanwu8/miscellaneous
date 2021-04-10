package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/5/2021
 */
public class CountSquareSubmatricesWithAllOnes {

    public int countSquares(int[][] matrix) {

        int[][] maxAllOneSize = new int[matrix.length][matrix[0].length];
        for (int col = 0; col < matrix[0].length; col++) { // loop through first row
            if (matrix[0][col] == 1) maxAllOneSize[0][col] = 1;
        }
        for (int row = 0; row < matrix.length; row++) { // loop through first col
            if (matrix[row][0] == 1) maxAllOneSize[row][0] = 1;
        }
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (row == 0 || col == 0) {  // if first row or first col
                    count += maxAllOneSize[row][col];
                } else {
                    if (matrix[row][col] == 1) {
                        int minMaxOne = Math.min(maxAllOneSize[row - 1][col - 1],
                                Math.min(maxAllOneSize[row - 1][col], maxAllOneSize[row][col - 1])); //min of 3 of them
                        maxAllOneSize[row][col] = minMaxOne + 1;
                        count += minMaxOne + 1;
                    }
                    // if 0
                }
            }
        }
        return count;
    }

}
