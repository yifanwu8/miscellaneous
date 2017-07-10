package com.yifanwu.algo.leetcode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Created by Yifan.Wu on 7/5/2017.
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        // corner cases or input validation
        if (head == null || head.next == null) {
            return head;
        }

        // split list into 2 halves
        ListNode sec = breakAndSplit(head);

        // recursive sort
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(sec);

        // merge 2 sorted lists
        return merge(h1, h2);

    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode point = dummyHead;
        // should both non null
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                point.next = head1;
                point = point.next;
                head1 = head1.next;
            } else {
                point.next = head2;
                point = point.next;
                head2 = head2.next;
            }
        }
        if (head1 == null) {
            point.next = head2;
        } else {
            point.next = head1;
        }
        return dummyHead.next;
    }

    // return the 2nd half list head
    private ListNode breakAndSplit(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode sec = slow.next;
        slow.next = null;
        return sec;
    }
}
