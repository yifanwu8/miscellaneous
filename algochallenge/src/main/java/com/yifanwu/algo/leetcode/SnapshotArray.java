package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yifan.Wu on 4/17/2021
 */
public class SnapshotArray {
    private final int[] arr;
    private final List<IndVal> valRec = new ArrayList<>(); // history of value at the index before set command
    private final Map<Integer, Integer> map = new HashMap<>(); // key: id; val: ind or size to replay to
    private int count = 0;

    public SnapshotArray(int length) {
        arr = new int[length];
    }

    public void set(int index, int val) {
        if (index >= arr.length) return;
        valRec.add(new IndVal(index, arr[index]));
        arr[index] = val;
    }

    public int snap() {
        map.put(count, valRec.size());
        return count++;
    }

    public int get(int index, int snap_id) {
        if (index >= arr.length || !map.containsKey(snap_id)) {
            throw new IllegalArgumentException();
        }
        int size = map.get(snap_id); // depends on size, can make a intelligent decision
        // whether to replay the list from 0 to size or scan from size forward
        if (size == 0) return 0;
        int i = size;
        while (i < valRec.size()) { // scan forward
            IndVal indVal = valRec.get(i);
            if (indVal.ind == index) return indVal.val;
            i++;
        }
        return arr[index];
    }

    private static class IndVal {
        private final int ind;
        private final int val;

        public IndVal(int ind, int val) {
            this.ind = ind;
            this.val = val;
        }
    }
}
