package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/
 * BFS
 * @author Yifan.Wu on 3/12/2021
 */
public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }

        Queue<TreeNode> currentQueue = new ArrayDeque<>();
        Queue<TreeNode> nextQ = new ArrayDeque<>();
        TreeNode curNode = root;
        int res = root.val;

        while (true){
            if (Objects.nonNull(curNode.left)) nextQ.add(curNode.left);
            if (Objects.nonNull(curNode.right)) nextQ.add(curNode.right);

            curNode = currentQueue.poll();
            if (Objects.isNull(curNode)) {
                if (nextQ.size() == 0) {
                    return res;
                } else {
                    res = nextQ.peek().val;
                    currentQueue = nextQ;
                    curNode = currentQueue.poll();
                    nextQ = new ArrayDeque<>();
                }
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
