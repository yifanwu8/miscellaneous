package com.yifanwu.algo.leetcode;

import java.util.Arrays;

/**
 * @author Yifan.Wu on 3/25/2021
 */
public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    public int maxWidthOfVerticalArea(int[][] points) {
        // defense from invalid/corner cases
        if (points.length <= 1) return 0;
        int[] xCor = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xCor[i] = points[i][0];
        }
        quickSort(xCor);
        int max = 0;
        for (int i = 1; i < xCor.length; i++) {
            max = Math.max(max, xCor[i] - xCor[i - 1]);
        }
        return max;
    }

    private void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int pivot = arr[start];
        int j = end;
        for (int i = start + 1; i <= j; ) {
            if (arr[j] > pivot) {
                j--;
            } else { // arr[j] <= pivot
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, start, j);
        quickSort(arr, start, j - 1);
        quickSort(arr, j + 1, end);
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
