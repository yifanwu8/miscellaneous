package com.yifanwu.algo.leetcode;

import java.util.List;

/**
 * @author Yifan.Wu on 4/1/2021
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // defnse from invalid and corner
        if (s == null) return false;
        int[] canBreak = new int[s.length()];

        return helper(s, 0 , wordDict, canBreak);
    }

    // canBreak 0: not determined; 1: yes; -1: no
    private boolean helper(String s, int index, List<String> wordDict, int[] canBreak) {
        if (index >= s.length()) return true;
        if (canBreak[index] != 0) return canBreak[index] > 0;
        for (int i = index; i < s.length(); i++) {
            if (wordDict.contains(s.substring(index, i + 1))) {
                boolean res = helper(s, i + 1, wordDict, canBreak);
                if (res) {
                    canBreak[index] = 1;
                    return true;
                }
            }
        }
        canBreak[index] = -1;
        return false;
    }
}
