package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/**
 * merge sort with counter from right sub-array
 * @author Yifan.Wu on 4/15/2021
 */
public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        // defense from invalid and corner cases

        ValInd[] arr = new ValInd[nums.length];
        ValInd[] aux = new ValInd[nums.length];
        List<Integer> res = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new ValInd(nums[i], i);
            res.add(0);
        }
        mergeSort(arr, aux, 0, nums.length - 1, res);
        return res;
    }

    private void mergeSort(ValInd[] arr, ValInd[] aux, int lo, int hi, List<Integer> counts) {
        if (lo == hi) return;
        // at least 2 elements
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo, mid, counts);
        mergeSort(arr, aux, mid + 1, hi, counts);

        merge(arr, aux, lo, mid, hi, counts);
    }

    private void merge(ValInd[] arr, ValInd[] aux, int lo, int mid, int hi, List<Integer> counts) {
        for (int i = lo; i <= hi; i++) {  // copy to aux array; can save time only copy lo-mid
            aux[i] = arr[i];
        }
        int fromRight = 0;
        for (int p = lo, left = lo, right = mid + 1; p <= hi; p++) {
            if (left > mid) {
                arr[p] = aux[right];
                right++;
                fromRight++;
            } else if (right > hi) {
                int ind = aux[left].ind;
                counts.set(ind, counts.get(ind) + fromRight); // increment counts at index by fromRight
                arr[p] = aux[left];
                left++;
            } else { // guarantee either i or j or both inbound
                int eq = aux[left].compareTo(aux[right]);
                if (eq <= 0) {
                    int ind = aux[left].ind;
                    counts.set(ind, counts.get(ind) + fromRight);
                    arr[p] = aux[left];
                    left++;
                } else {
                    arr[p] = aux[right];
                    right++;
                    fromRight++;
                }
            }
        }
    }

    private static class ValInd implements Comparable<ValInd> {
        private final int val;  // descriptive name in real code
        private final int ind;

        public ValInd(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }

        @Override
        public int compareTo(ValInd o) {
            int r = Integer.compare(this.val, o.val);
            return r != 0 ? r : Integer.compare(this.ind, o.ind);
        }
    }
}
