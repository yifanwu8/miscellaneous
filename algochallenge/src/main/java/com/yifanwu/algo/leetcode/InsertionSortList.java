package com.yifanwu.algo.leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 * @author Yifan.Wu on 3/11/2021
 */
public class InsertionSortList {

    public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode insertionSortList(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Stack<ListNode> stack = new Stack<>();
        for (ListNode node = dummy; Objects.nonNull(node); node = node.next) {
            stack.push(node);
        }

        for (ListNode node = stack.pop(); stack.size() > 0; ) {
            ListNode tempHead = moveBigBackwards(node);
            node = stack.pop();
            node.next = tempHead;
        }

        return dummy.next;
    }

    // return new head
    private ListNode moveBigBackwards(ListNode node) {
        ListNode before = node;
        ListNode after = node.next;
        for (; Objects.nonNull(after) && node.val > after.val; before = after, after = after.next ) {

        }
        if (node == before) { // if no change needed.
            return node;
        }
        ListNode newHead = node.next;
        before.next = node;
        node.next = after;
        return newHead;
    }
}
