package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/13/2021
 */
public class RLEIterator {

    private final int[] A; // assuming we can use array in place; all inputs are valid
    private int ind = 0;
    private boolean exhausted = false;

    public RLEIterator(int[] A) {
        this.A = A;
    }

    public int next(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        if (exhausted) return -1;
        int ct = n;
        while (ind < A.length && ct > A[ind]) {
            ct -= A[ind];
            ind += 2;
        }
        if (ind >= A.length) {
            exhausted = true;
            return -1;
        } else {
            int val = A[ind + 1];
            A[ind] = A[ind] - ct;
            return val;
        }
    }
}
