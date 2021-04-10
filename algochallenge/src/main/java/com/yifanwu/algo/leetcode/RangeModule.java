package com.yifanwu.algo.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 4/4/2021
 */
public class RangeModule {
    // keyed by left
    private final TreeMap<Integer, Range> bst = new TreeMap<>();

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        if (right <= left) return;
        Range floor = null;
        Map.Entry<Integer, Range> entry = bst.floorEntry(left);
        if (entry != null) {
            floor = entry.getValue();
        }
        boolean overlapFloor = false;
        Range newRange = new Range(left, right);
        if (floor != null) {
            if (floor.end >= left) { // overlaps with floor
                bst.remove(floor.start);
                newRange = new Range(floor.start, Math.max(floor.end, right));
            }
        }
        Range ceil = null;
        entry = bst.ceilingEntry(left);
        while (entry != null) {
            ceil = entry.getValue();
            if (ceil.start <= newRange.end) { // new Range overlap next
                entry = bst.higherEntry(ceil.start);
                newRange.end = Math.max(newRange.end, ceil.end);
                bst.remove(ceil.start);
            } else { // if not overlap next; we can stop
                entry = null;
            }
        }
        bst.put(newRange.start, newRange);
    }

    public boolean queryRange(int left, int right) {
        Range floor = null;
        Map.Entry<Integer, Range> entry = bst.floorEntry(left);
        if (entry != null) {
            floor = entry.getValue();
        }        if (floor == null) return false;
        return floor.end >= right;
    }

    public void removeRange(int left, int right) {
        Range floor = null;
        Map.Entry<Integer, Range> entry = bst.floorEntry(left);
        if (entry != null) {
            floor = entry.getValue();
        }
        Range headRange = null;
        Range tailRange = null;
        if (floor != null && floor.end > left) { // overlaps with floor
            if (floor.start < left) {
                headRange = new Range(floor.start, left);
            }
            if (floor.end > right) {
                tailRange = new Range(right, floor.end);
            }
            bst.remove(floor.start);
        }
        entry = bst.ceilingEntry(left);
        while (entry != null && tailRange == null) {
            Range ceil = entry.getValue();
            if (ceil.start >= right) { // no overlap next
                break;
            }
            // overlap next
            if (ceil.end <= right) { //
                entry = bst.higherEntry(ceil.start);
            } else {
                tailRange = new Range(right, ceil.end);
            }
            bst.remove(ceil.start);
        }
        if (headRange != null) bst.put(headRange.start, headRange);
        if (tailRange != null) bst.put(tailRange.start, tailRange);
    }

    private static class Range {
        private final int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        boolean res;
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(6, 8);
        rangeModule.removeRange(7, 8);
        rangeModule.removeRange(8, 9);
        rangeModule.addRange(8,9);
        rangeModule.removeRange(1,3);
        rangeModule.addRange(1,8);
        res = rangeModule.queryRange(2,4);
        res=rangeModule.queryRange(2,9);
        res=rangeModule.queryRange(4,6);

    }
}
