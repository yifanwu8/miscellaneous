package com.yifanwu.algo.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 4/13/2021
 */
public class MyCalendarThree {
    private final TreeMap<Integer, Integer> map = new TreeMap<>(); // key: time;  value: if start: ++1 if end --1

    public MyCalendarThree() {
    }

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int ct = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> tt : map.entrySet()) {
            ct += tt.getValue();
            max = Math.max(max, ct);
        }
        return max;
    }

    public static void main(String[] args) {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        int res = myCalendarThree.book(10,20);
        res = myCalendarThree.book(50,60);
        res = myCalendarThree.book(10,40);
        res = myCalendarThree.book(5,15);

    }
}
