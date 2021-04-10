package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Stack
 * @author Yifan.Wu on 4/7/2021
 */
public class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(ListNode head) {
        // defense from invalild and corner cases
        if (head == null) return new int[0];

        int size = 0;
        for (ListNode node = head; node != null; node = node.next) {
            size++;
        }
        int[] res = new int[size]; // initialize to zero
        Deque<ValInd> stack = new ArrayDeque<>();
        int ind = 0;
        ListNode node = head;
        stack.push(new ValInd(node.val, ind));
        node = node.next;
        ind++;
        while (node != null) {
            while (stack.peek() != null && stack.peek().val < node.val) {
                ValInd smaller = stack.pop();
                res[smaller.ind] = node.val;
            }
            stack.push(new ValInd(node.val, ind));
            ind++;
            node = node.next;
        }
        return res;
    }

    private static class ValInd {  // descriptive name in real code
        private final int val;
        private final int ind;

        public ValInd(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
