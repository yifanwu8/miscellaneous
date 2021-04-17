package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Post Oder DFS
 * @author Yifan.Wu on 4/16/2021
 */
public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // defense from invalid and corner cases

        Set<Integer> toDel = new HashSet<>();
        for (int i : to_delete) {
            toDel.add(i);
        }
        List<TreeNode> res = new ArrayList<>();
        if (root != null && !isDelete(root, toDel, res)) res.add(root);
        return res;
    }

    private boolean isDelete(TreeNode root, Set<Integer> toDel, List<TreeNode> list) {
        if (root == null) return false;

        if (isDelete(root.left, toDel, list)) root.left = null;
        if (isDelete(root.right, toDel, list)) root.right = null;

        if (toDel.contains(root.val)) {
            if (root.left != null) list.add(root.left);
            if (root.right != null) list.add(root.right);
            return true;
        }
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
