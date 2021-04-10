package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * DFS get height
 * BFS
 * @author Yifan.Wu on 3/26/2021
 */
public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        int height = getHeight(root);
        int totalLength = (1 << height) - 1;

        List<List<String>> res = new ArrayList<>(height);
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        Queue<TreeNode> nextQueue = new ArrayDeque<>();
        List<String> row = new ArrayList<>(totalLength);
        boolean startOfCurHeight = true;
        int numOfNodes = 1;
        int nodeLength = totalLength / numOfNodes;
        int padLength = (nodeLength) / 2;
        for (int curHeight = 0; curHeight < height;) {
            TreeNode node = queue.poll();
            nextQueue.add(node.left == null ? dummy : node.left);
            nextQueue.add(node.right == null ? dummy : node.right);
            List<String> nodeStr = new ArrayList<>(nodeLength);
            for (int i = 0; i < padLength; i++) {
                nodeStr.add("");
            }
            if (node != dummy) {
                nodeStr.add(Integer.toString(node.val));
            } else {
                nodeStr.add("");
            }
            for (int i = 0; i < padLength; i++) {
                nodeStr.add("");
            }
            if (!startOfCurHeight) {
                row.add("");
            }
            row.addAll(nodeStr);
            startOfCurHeight = false;
            if (queue.isEmpty()) {
                queue = nextQueue;
                nextQueue = new ArrayDeque<>();
                curHeight++;
                numOfNodes = 1 << curHeight;
                nodeLength = totalLength / numOfNodes;
                padLength = (nodeLength) / 2;
                res.add(row);
                row = new ArrayList<>(totalLength);
                startOfCurHeight = true;
            }
        }
        return res;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
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

    public static void main(String[] args) {
        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        List<List<String>> res = printBinaryTree.printTree(root);
    }
}
