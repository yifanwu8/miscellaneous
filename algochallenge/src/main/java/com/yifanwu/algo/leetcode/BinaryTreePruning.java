package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/25/2021
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if (canBePruned(root)) {
            return null;
        } else {
            return root;
        }
    }

    private boolean canBePruned(TreeNode root) {
        if (root == null) return true;
        if (canBePruned(root.left)) {
            root.left = null;
        }
        if (canBePruned(root.right)) {
            root.right = null;
        }
        return root.val == 0 && root.left == null && root.right == null;
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
