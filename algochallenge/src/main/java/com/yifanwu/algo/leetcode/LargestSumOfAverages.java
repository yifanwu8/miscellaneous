package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/26/2021
 */
public class LargestSumOfAverages {
    public double largestSumOfAverages(int[] A, int K) {
        // defense from invalild/corner cases

        double[][] remembered = new double[K][A.length];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j<A.length; j++) {
                remembered[i][j] = Double.MIN_VALUE;
            }
        }
        return helper(A, 0, K, remembered);
    }

    // top-down recursive DP
    private double helper(int[] A, int startIndex, int groupLeft, double[][] remembered) {
        if (remembered[remembered.length - groupLeft][startIndex] > Double.MIN_VALUE) {
            return remembered[remembered.length - groupLeft][startIndex];
        }
        if (groupLeft == 1) {
            double sum = average(A, startIndex, A.length - 1);
            remembered[remembered.length - groupLeft][startIndex] = sum;
            return sum;
        }

        if (A.length - startIndex == groupLeft) {
            int sum = 0;
            for (int i = startIndex; i < A.length; i++) {
                sum += A[i];
            }
            remembered[remembered.length - groupLeft][startIndex] = sum;
            return sum;
        }
        double sum = Double.MIN_VALUE;
        for (int i = startIndex; i < A.length - (groupLeft - 1); i++) {
            sum = Math.max(sum, average(A, startIndex, i) +
                    helper(A, i + 1, groupLeft - 1, remembered));
        }
        remembered[remembered.length - groupLeft][startIndex] = sum;
        return sum;
    }

    private double average(int[] A, int startInd, int endInd) {
        int total = 0;
        for (int i = startInd; i <= endInd; i++) {
            total += A[i];
        }
        return (double) total / (endInd - startInd + 1);
    }
}
