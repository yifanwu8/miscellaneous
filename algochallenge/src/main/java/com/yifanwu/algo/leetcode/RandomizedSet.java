package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Yifan.Wu on 4/5/2021
 */
public class RandomizedSet {
    private final List<Integer> l = new ArrayList<>(); // descriptive name in real code
    private final Map<Integer, Integer> m = new HashMap<>(); // descriptive name in real code

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (m.containsKey(val)) return false;
        // add val as key and index in array as value
        m.put(val, l.size());
        // append val to the list
        l.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) return false;
        int ind = m.get(val);
        m.remove(val);
        if (!(ind == l.size() - 1)) { // only swap if not the last one
            // set the remove index with the last one; array list randome access is O(1)
            int lastVal = l.get(l.size() - 1);
            l.set(ind, lastVal);
            // update the index for swapped one
            m.put(lastVal, ind);
        }
        // shrink list size by one; it is last one so it is O(1) fro array list
        l.remove(l.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        int randomInt = random.nextInt(l.size());
        int val = l.get(randomInt);
        return val;
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

    }
}
