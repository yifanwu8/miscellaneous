package com.yifanwu.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yifan.Wu on 4/10/2021
 */
public class Logger {
    private final Map<String, Integer> map = new HashMap<>(); // key: message; value: next allowed time

    /** Initialize your data structure here. */
    public Logger() {

    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || map.get(message) <= timestamp) {
            map.put(message, timestamp + 10);
            return true;
        } else {
            return false;
        }
    }
}
