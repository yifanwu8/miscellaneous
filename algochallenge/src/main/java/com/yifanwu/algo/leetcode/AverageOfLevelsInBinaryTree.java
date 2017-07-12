package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  *Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 *Example 1:
 *Input:
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *Output: [3, 14.5, 11]
 *Explanation:
 *The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 *Note:
 *    The range of node's value is in the range of 32-bit signed integer.
 * Created by Yifan.Wu on 7/12/2017.
 */
public class AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        // corner case and validate input

        List<Double> doubles = new ArrayList<>();

        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        currentLevel.addLast(root);

        while (currentLevel.size() > 0) {
            int count = currentLevel.size();
            long sum = 0;
            for (TreeNode node : currentLevel) {
                sum += node.val;
                if (node.left != null) {
                    nextLevel.addLast(node.left);
                }
                if (node.right != null) {
                    nextLevel.addLast(node.right);
                }
            }
            doubles.add(((double)sum) / count);

            currentLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }
        return doubles;
    }
}
