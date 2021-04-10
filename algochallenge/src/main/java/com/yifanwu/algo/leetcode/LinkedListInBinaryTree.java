package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @author Yifan.Wu on 3/25/2021
 */
public class LinkedListInBinaryTree {
    public boolean isSubPath(ListNode head, TreeNode root) {
        // defense from invalid/corner case
        if (Objects.isNull(head)) return true;
        if (Objects.isNull(root)) return false;

//        ListNode dummyHead = new ListNode(-1);
//        dummyHead.next  = head;
//
//        List<ListNode> matchedListNode = new ArrayList<>();
//        matchedListNode.add(dummyHead);
//        return subPath(root, matchedListNode, dummyHead);

        return isPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);

    }

    private boolean isPath(ListNode head, TreeNode root) {
        if (Objects.isNull(head)) return true;
        if (Objects.isNull(root)) return false;
        if (root.val != head.val) return false;
        return isPath(head.next, root.left) || isPath(head.next, root.right);
    }
//
//    private boolean subPath(TreeNode root, List<ListNode> matchedListNode, ListNode dummyHead) {
//        List<ListNode> nextQueue = new ArrayList<>();
//        for (ListNode currNode : matchedListNode) {
//            if (root.val == currNode.next.val) {
//                if (currNode.next.next == null) return true;
//                nextQueue.add(currNode.next);
//            }
//            nextQueue.add(dummyHead);
//        }
//        boolean res = false;
//        if (root.left != null) {
//            res = res || subPath(root.left, nextQueue, dummyHead);
//        }
//        if (root.right != null) {
//            res = res || subPath(root.right, nextQueue, dummyHead);
//        }
//        return res;
//    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
