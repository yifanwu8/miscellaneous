package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Yifan.Wu on 4/9/2021
 */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        // defense from invalid and corner case
        if (transactions == null || transactions.length == 0) return 0;
        Map<Integer, Integer> res = new HashMap<>(); // keyed by pID value is in(-) or out(+)
        for (int[] txn : transactions) {
            int givId = txn[0];  //descriptive name in real-code
            int recId = txn[1];
            int amt = txn[2];
            if (res.containsKey(givId)) {
                res.put(givId, res.get(givId) + amt);
            } else {
                res.put(givId, amt);
            }
            if (res.containsKey(recId)) {
                res.put(recId, res.get(recId) - amt);
            } else {
                res.put(recId, -1 * amt);
            }
        }
        Deque<Integer> posList = new LinkedList<>();
        Deque<Integer> negList = new LinkedList<>();
        for (int value : res.values()) {
            if (value > 0) {
                posList.add(value);
            } else if (value < 0) {
                negList.add(value);
            } // if 0, nothing need
        }
        return backtrack(posList, negList, 0, 0);
    }

    private int backtrack(Deque<Integer> posList, Deque<Integer> negList, int count, int bal) {
        if (posList.isEmpty() && negList.isEmpty() && bal == 0) return count;

        int result = Integer.MAX_VALUE;
        int posV;
        if (bal != 0) {
            posV = bal;
        } else {
            posV = posList.removeFirst();
        }
        for (int j = 0; j < negList.size(); j++) {
            int negV = negList.removeFirst();
            int resV = posV + negV;
            int newCount = count + 1;

            if (resV > 0) {
                result = Math.min(result, backtrack(posList, negList, newCount, resV));
            } else if (resV < 0) {
                negList.addLast(resV);
                result = Math.min(result, backtrack(posList, negList, newCount, 0));
                negList.removeLast();
            } else {
                result = Math.min(result, backtrack(posList, negList, newCount, 0));
            }
            negList.addLast(negV);
        }
        if (bal == 0) {
            posList.addFirst(posV);
        }
        return result;
    }


    public static void main(String[] args) {
        OptimalAccountBalancing optimalAccountBalancing = new OptimalAccountBalancing();
        int res = optimalAccountBalancing.minTransfers(new int[][] {
                {10,11,6},
                {12,13,7},
                {14,15,2},
                {14,16,2},
                {14,17,2},
                {14,18,2}
        });
    }
}
