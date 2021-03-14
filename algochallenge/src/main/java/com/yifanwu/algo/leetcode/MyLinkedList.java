package com.yifanwu.algo.leetcode;

import java.util.Objects;

/**
 * 707. Design Linked List
 * https://leetcode.com/problems/design-linked-list/
 * @author Yifan.Wu on 3/7/2021
 */
public class MyLinkedList {

    private Node root;
//    private Node tail;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.root = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (Objects.isNull(root) || index < 0) {
            return -1;
        }
        int i = 0;
        Node node = root;
        int result;
        while (Objects.nonNull(node)) {
            result = node.getVal();
            if (i == index) {
                return result;
            }
            i++;
            node = node.getNext();
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if (Objects.isNull(root)) {
            root = new Node(val);
        } else {
            Node temp = root;
            root = new Node(val);
            root.setNext(temp);
            temp.setPrev(root);
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (Objects.isNull(root)) {
            root = new Node(val);
        } else {
            Node node = root;
            while (true) {
                if (Objects.isNull(node.getNext())) { // at tail node
                    Node temp = new Node(val);
                    node.setNext(temp);
                    temp.setPrev(node);
                    return;
                } else {
                    node = node.getNext();
                }
            }
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            if (Objects.isNull(node)) {
                return;
            } else {
                node = node.getNext();
            }
        }
        if (Objects.isNull(node)) {
            addAtTail(val);
            return;
        } else {
            Node currNode = new Node(val).setPrev(node.getPrev()).setNext(node);
            currNode.getPrev().setNext(currNode);
            node.setPrev(currNode);
        }

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (Objects.isNull(root) || index < 0) {
            return;
        }
        if (index == 0) {
            Node temp = root;
            root = root.getNext();
            temp.setNext(null);
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            if (Objects.isNull(node)) {
                return;
            } else {
                node = node.getNext();
            }
        }
        if (Objects.nonNull(node)) {
            node.getPrev().setNext(node.getNext());
            if (Objects.nonNull(node.getNext())) {
                node.getNext().setPrev(node.getPrev());
            }
            node.setPrev(null).setNext(null);
        }
    }

    private static class Node {
        private final int val;
        private Node prev, next;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public Node getPrev() {
            return prev;
        }

        public Node setPrev(Node prev) {
            this.prev = prev;
            return this;
        }

        public Node getNext() {
            return next;
        }

        public Node setNext(Node next) {
            this.next = next;
            return this;
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtIndex(0, 10);
        myLinkedList.addAtIndex(0, 20);
    }
}
