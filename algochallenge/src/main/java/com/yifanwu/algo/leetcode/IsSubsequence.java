package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/20/2021
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int i = 0,j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }
}
