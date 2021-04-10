package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/28/2021
 */
public class NumberOfGoodWaysToSplitAString {
    public int numSplits(String s) {
        // defense from invalid/corner cases
        if (s == null || s.length() == 0) return 0;

        int[] forward = new int[s.length()];
        boolean[] forwardSeen = new boolean[26];
        for (int i = 0; i < s.length() - 1; i++) {
            if (!forwardSeen[s.charAt(i) - 'a']) {
                forwardSeen[s.charAt(i) - 'a'] = true;
                if (i == 0) {
                    forward[0] = 1;
                } else {
                    forward[i] = forward[i - 1] + 1;
                }
            } else {
                forward[i] = forward[i - 1];
            }
        }

        int count = 0;
        int letters = 0;
        boolean[] backSeen = new boolean[26];
        for (int i = s.length() - 1; i > 0; i--) {
            if (!backSeen[s.charAt(i) - 'a']) {
                backSeen[s.charAt(i) - 'a'] = true;
                letters++;
            }
            if (letters == forward[i - 1]) count++;
        }
        return count;
    }
}
