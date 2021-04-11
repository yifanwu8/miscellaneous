package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yifan.Wu on 4/11/2021
 */
public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Boolean> seen = new HashMap<>();
        List<TreeNode> nodeList = new ArrayList<>();
        serializeTree(root, seen, nodeList);
        return nodeList;
    }

    private String serializeTree(TreeNode root, Map<String, Boolean> seen, List<TreeNode> nodeList) {
        if (root == null) return "#";
        // postorder
        StringBuilder sb = new StringBuilder(Integer.toString(root.val));
        sb.append(',').append(serializeTree(root.left, seen, nodeList));
        sb.append(',').append(serializeTree(root.right, seen, nodeList));
        String res = sb.toString();
        if (seen.containsKey(res)) {
            if (!seen.get(res)) {
                nodeList.add(root);
                seen.put(res, true);
            }
        } else {
            seen.put(res, false);
        }
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

}
