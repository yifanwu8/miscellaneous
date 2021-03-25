package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yifan.Wu on 3/16/2021
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += 9 - i;
        }
        List<List<Integer>> res = new ArrayList<>();
        if (n > total) {
            return res;
        }
        helper(res, k, n, 1, new LinkedList<>());
        return res;
    }

    private void  helper(List<List<Integer>> res, int k, int n, int startPoint, LinkedList<Integer> list) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (k <= 0) {
            return;
        }
        if (n <= 0) {
            return;
        }
        if (startPoint >= 10) {
            return;
        }
        // 1. take start point
        list.addLast(startPoint);
        helper(res, k - 1, n - startPoint, startPoint + 1, list);
        list.removeLast();

        // 2. not take start point
        helper(res, k, n, startPoint + 1, list);
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        combinationSum3.combinationSum3(3, 9);
    }
}
