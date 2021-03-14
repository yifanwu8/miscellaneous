package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * https://leetcode.com/problems/balance-a-binary-search-tree/
 * @author Yifan.Wu on 3/9/2021
 */
public class BalanceBinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {
        if (Objects.isNull(root)) {
            return null;
        }

        List<TreeNode> sortedTreeNodes = new ArrayList<>();
        inorderTreeTravesal(root, sortedTreeNodes);

        return buildBstFromList(sortedTreeNodes, 0, sortedTreeNodes.size() - 1);

    }

    // lo and hi are inclusive
    private TreeNode buildBstFromList(List<TreeNode> list, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int mid = ((hi - lo) / 2) + lo;
        TreeNode root = list.get(mid);
        root.left = buildBstFromList(list, lo, mid - 1);
        root.right = buildBstFromList(list, mid + 1, hi);

        return root;
    }

    private void inorderTreeTravesal(TreeNode treeNode, List<TreeNode> list) {
        if (Objects.isNull(treeNode)) {
            return;
        }
        inorderTreeTravesal(treeNode.left, list);
        list.add(treeNode);
        inorderTreeTravesal(treeNode.right, list);
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
