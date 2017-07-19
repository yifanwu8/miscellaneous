package com.yifanwu.algo.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *  Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.

 Notes:

 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 * Created by Yifan.Wu on 7/19/2017.
 */
public class MyStack {

    private Integer top;
    private Deque<Integer> deque;

    /** Initialize your data structure here. */
    public MyStack() {
        deque = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (top == null) {
            top = x;
        } else {
            deque.addLast(top);
            top = x;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int res = top;
        if (deque.size() == 0) {
            top = null;
        } else {
            for (int i=deque.size(); i > 1; i--) {
                deque.addLast(deque.removeFirst());
            }
            top = deque.removeFirst();
        }
        return res;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return top == null;
    }
}
