package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yifan.Wu on 3/27/2021
 */
public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        //defense from invalid and corner cases
        if (low < 10 || high < low) return Collections.emptyList();

        int lengthLow = 1;
        for (int remainder = low / 10; remainder > 0; remainder /= 10) {
            lengthLow++;
        }
        int lengthHi = 1;
        for (int remainder = high / 10; remainder > 0; remainder /= 10) {
            lengthHi++;
        }

        List<Integer> res = new ArrayList<>();
        for (int len = lengthLow; len <= Math.min(9, lengthHi); len++) {
            // construct all know increasing digits with the length
            for (int k = 0; k + len <= 9; k++) {
                int sum = 0;
                for (int i = len + k, base = 1; i > k; i--, base *= 10) {
                    sum += base * i;
                }
                if (sum > high) return res;
                if (sum >= low) {
                    res.add(sum);
                }
            }
        }
        return res;
    }
}
