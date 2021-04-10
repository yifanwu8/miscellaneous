package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Min spanning tree
 * MST
 * @author Yifan.Wu on 4/6/2021
 */
public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        // defense from invalid and corner cases
        if (points == null || points.length <= 1) return 0;
        // at least 2 points
        // build edges (distance between 2 points) array
        List<List<IndexDistance>> edges = new ArrayList<>(points.length);
        for (int i = 0; i < points.length; i++) {
            edges.add(new ArrayList<>(points.length));
        }
        for (int from = 0; from < points.length - 1; from++) {
            for (int to = from + 1; to < points.length; to++) {
                int dist = Math.abs(points[from][0] - points[to][0]) + Math.abs(points[from][1] - points[to][1]);
                edges.get(from).add(new IndexDistance(to, dist));
                edges.get(to).add(new IndexDistance(from, dist));  // undirected weighted edge
            }
        }
        int cost = 0;
        PriorityQueue<IndexDistance> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        int totalConnected = 0;
        boolean[] connected = new boolean[points.length];
        connected[0] = true;
        totalConnected++;
        pq.addAll(edges.get(0));
        while (totalConnected < points.length) { // run until all points are connected
            IndexDistance nextPoint = pq.poll();
            if (!connected[nextPoint.index]) {  // guarantee non-null
                connected[nextPoint.index] = true;
                totalConnected++;
                cost += nextPoint.dist;
                for (IndexDistance id : edges.get(nextPoint.index)) {
                    if (!connected[id.index]) {  // only add edges to those not connected
                        pq.add(id);
                    }
                }
            }
        }
        return cost;
    }

    private static class IndexDistance {
        private final int index;
        private final int dist; // use descriptive name in real code

        public IndexDistance(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
}
