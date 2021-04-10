package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/26/2021
 */
public class NumberOfSegmentsInString {
    public int countSegments(String s) {
        // defense from invalid/corner cases
        if (s.isEmpty()) return 0;
        int count = 0;
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) != ' ') {
                count++;
                // scan to next space
                while (s.charAt(i) != ' ') {
                    i++;
                    if (i == s.length()) {
                        break;
                    }
                }
            } else {
                i++;
            }
        }
        return count;
    }
}
