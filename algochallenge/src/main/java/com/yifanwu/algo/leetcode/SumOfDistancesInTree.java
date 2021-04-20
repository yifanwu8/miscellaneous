package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Yifan.Wu on 4/19/2021
 */
public class SumOfDistancesInTree {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        // defense from invalid and corner cases
        // check null and empty array

        Map<Integer, List<Integer>> vE = new HashMap<>(); // key: node/V; val: list of nodes/Vs rep of edges
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            List<Integer> l = vE.getOrDefault(v1, new ArrayList<>());
            l.add(v2);
            vE.put(v1, l);
            l = vE.getOrDefault(v2, new ArrayList<>());
            l.add(v1);
            vE.put(v2, l);
        }
        Map<Edge, DistNodes> edMap = new HashMap<>();
        int[] total = new int[N];
        for (int i = 0; i < N; i++) {
            total[i] = getTotalDist(i, vE, edMap);
        }
        return total;
    }

    private int getTotalDist(int n, Map<Integer, List<Integer>> vE, Map<Edge, DistNodes> edMap) {
        List<Integer> neib = vE.get(n);
        if (neib == null) return 0; // if not connected
        int dists = 0;
        for (int i : neib) {
            Edge e = new Edge(n, i);
            if (!edMap.containsKey(e)) {
                edMap.put(e, helper(i, n, vE, edMap));
            }
            DistNodes dN = edMap.get(e);
            dists += dN.dist;
        }
        return dists;
    }

    private DistNodes helper(int to, int from, Map<Integer, List<Integer>> vE, Map<Edge, DistNodes> edMap) {
        int dists = 0;
        int num = 0;
        List<Integer> neib = vE.get(to);
        for (int i : neib) {
            if (i != from) {
                Edge e = new Edge(to, i);
                if (!edMap.containsKey(e)) {
                    edMap.put(e, helper(i, to, vE, edMap));
                }
                DistNodes dN = edMap.get(e);
                dists += dN.dist;
                num += dN.numN;
            }
        }
        return new DistNodes(dists + num + 1, num + 1);
    }

    private static class Edge {
        private final int fr;
        private final int to;

        public Edge(int fr, int to) {
            this.fr = fr;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return fr == edge.fr && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fr, to);
        }
    }

    private static class DistNodes {
        private final int dist;
        private final int numN; // number of nodes

        public DistNodes(int dist, int numN) {
            this.dist = dist;
            this.numN = numN;
        }
    }

    public static void main(String[] args) {
        SumOfDistancesInTree tre = new SumOfDistancesInTree();
        tre.sumOfDistancesInTree(6,
                new int[][]{
                        {0,1},{0,2},{2,3},{2,4},{2,5}   
                });
    }
}
