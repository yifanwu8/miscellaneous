package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Shortest path
 *
 * @author Yifan.Wu on 4/10/2021
 */
public class BellmanFord {

    public double findShortestPath(Graph g) {

        Edge[] edgeToV = new Edge[g.V];  // edge to the vertex in the shortest path from source 0
        double[] distTo = new double[g.V];
        for (int i = 1; i < g.V; i++) {
            distTo[i] = Double.MAX_VALUE;
        }
        distTo[0] = 0.0d;
        edgeToV[0] = new Edge(0, 0, 0.0d);

        // iterate V-1 times
        for (int i = 0; i < g.V - 1; i++) {
            // relax the to vertex for all edges if it is shorter
            for (Edge edge : g.edges) {
                int from = edge.from;
                int to = edge.to;
                if (distTo[to] > distTo[from] + edge.weight) { // need to relax
                    distTo[to] = distTo[from] + edge.weight;
                    edgeToV[to] = edge;
                }
            }
        }
        // go through one more time to find negative cycle if we still can relax
        for (Edge edge : g.edges) {
            int from = edge.from;
            int to = edge.to;
            if (distTo[to] > distTo[from] + edge.weight) { // need to relax
                throw new IllegalStateException("Aha, find neg cycle");
            }
        }
        return distTo[g.V - 1];
    }

    // directed weighted graph (may have cycle and negative weight)
    public static class Graph {
        private final int V;
        private final List<Edge> edges = new ArrayList<>();

        public Graph(int v) {
            V = v;
        }
        public void addEdge(int from, int to, double weight) {
            if (from >= V || to >= V) throw new IllegalArgumentException();
            edges.add(new Edge(from, to, weight));
        }
    }
    public static class Edge {
        private final int from;
        private final int to;
        private final double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1, 5.0d);
        graph.addEdge(0,4,9.0);
        graph.addEdge(0,7,8.0);
        graph.addEdge(1,2,12.0);
        graph.addEdge(1,3,15.0);
        graph.addEdge(1,7,4.0);
        graph.addEdge(2,3,3.0);
        graph.addEdge(2,6,11.0);
        graph.addEdge(3,6,9.0);
        graph.addEdge(4,5,4.0);
        graph.addEdge(4,6,20.0);
        graph.addEdge(4,7,5.0);
        graph.addEdge(5,2,1.0);
        graph.addEdge(5,6,13.0);
        graph.addEdge(7,5,6.0);
        graph.addEdge(7,2,7.0);
//        graph.addEdge();
        BellmanFord bellmanFord = new BellmanFord();
        double dist = bellmanFord.findShortestPath(graph);

    }

}
