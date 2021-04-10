package com.yifanwu.algo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 3/30/2021
 */
public class TimeMap {
    private final Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    /** Initialize your data structure here. */
    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        // defense from invalid and corner cases
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<Integer, String>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        // defense from invalid and corner cases
        TreeMap<Integer, String> navMap = map.get(key);
        if (navMap == null) return "";
        Map.Entry<Integer, String> floorEntry = navMap.floorEntry(timestamp);
        if (floorEntry == null) return "";
        return floorEntry.getValue();
    }

}
