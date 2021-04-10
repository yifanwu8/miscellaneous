package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yifan.Wu on 3/27/2021
 */
public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // defense from invalid and corner cases
        List<List<Integer>> res = new ArrayList<>();
        if (graph.length == 0) return res;

        LinkedList<Integer> deq = new LinkedList();
        dfs(graph, 0, deq, res);
        return res;
    }

    private void dfs(int[][] graph, int v, LinkedList<Integer> deq, List<List<Integer>> res) {
        deq.addLast(v);
        if (v == graph.length - 1) {
            List<Integer> path = new ArrayList<>(deq);
            res.add(path);
            deq.removeLast();
            return;
        }
        for (int edgeToVertex : graph[v]) {
            dfs(graph, edgeToVertex, deq, res);
        }
        deq.removeLast();
        return;
    }
}
