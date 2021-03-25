package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * topological sort
 * https://leetcode.com/problems/course-schedule-ii/
 * @author Yifan.Wu on 3/15/2021
 */
public class CourceSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[0];

        List<List<Integer>> directedEdges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            directedEdges.add(new LinkedList<>());
        }
        for (int[] prereq : prerequisites) {
            directedEdges.get(prereq[1]).add(prereq[0]);
        }

        boolean[] visited = new boolean[numCourses]; // initialize visited for every source
        Set<Integer> onThePath = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                visited[i] = true;
                onThePath.add(i);
                try {
                    dfs(i, directedEdges, visited, stack, onThePath);
                } catch (IllegalStateException e) {
                    return new int[0];
                }
                    onThePath.remove(i);
            }
        }
        int[] res = new int[numCourses];
        for (int i =0; i < numCourses ; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private void dfs(int vertex, List<List<Integer>> directedEdges, boolean[] visited, Stack<Integer> stack, Set<Integer> onThePath) {
        List<Integer> edges = directedEdges.get(vertex);
        for (int nextVertex : edges) {
            if (onThePath.contains(nextVertex)) {
                throw new IllegalStateException();
            }
            if (!visited[nextVertex]) {
                visited[nextVertex] = true;
                onThePath.add(nextVertex);
                dfs(nextVertex, directedEdges, visited, stack, onThePath);
                onThePath.remove(nextVertex);
            }
        }
        stack.push(vertex);
    }

    public static void main(String[] args) {
        CourceSchedule2 courceSchedule2 = new CourceSchedule2();
        courceSchedule2.findOrder(4, new int[][]{{1,0}, {2,0},{3,1},{3,2}});
    }
}
