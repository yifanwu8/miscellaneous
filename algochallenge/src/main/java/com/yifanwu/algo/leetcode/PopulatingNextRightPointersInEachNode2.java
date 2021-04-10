package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Yifan.Wu on 3/30/2021
 */
public class PopulatingNextRightPointersInEachNode2 {
    public Node connect(Node root) {
        // defense from invalid and corner cases
        if (root == null) return null;
        Queue<Node> currQ = new ArrayDeque<>();
        Queue<Node> nextQ = new ArrayDeque<>();
        currQ.add(root);
        while (!currQ.isEmpty()) {
            Node node = currQ.poll();
            node.next = currQ.peek();
            if (node.left != null) nextQ.add(node.left);
            if (node.right != null) nextQ.add(node.right);

            if (currQ.isEmpty()) {
                currQ = nextQ;
                nextQ = new ArrayDeque<>();
            }
        }
        return root;
    }

    private static  class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
