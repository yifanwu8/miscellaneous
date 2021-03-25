package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 *
 * BFS
 * @author Yifan.Wu on 3/13/2021
 */
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        if (Objects.isNull(graph) || graph.length <= 1) {
            return true;
        }

        int setA = 1, setB = -1;
        int[] visitedSetId = new int[graph.length]; // 0-not visited; 1-visited set A; -1-visited set B
        Queue<Integer> queue = new ArrayDeque<>();
        for (int source = 0; source < graph.length; source++) {
            if (visitedSetId[source] == 0) { // source not visited
                queue.add(source);
                visitedSetId[source] = setA;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int i : graph[node]) {
                        if (visitedSetId[i] == 0) { // not visited
                            queue.add(i); // put on queue
                            visitedSetId[i] = (visitedSetId[node] == setA) ? setB : setA; // alternate set A/B
                        } else { // visited
                            if (visitedSetId[node] == visitedSetId[i]) { // edge connects to the same set
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
