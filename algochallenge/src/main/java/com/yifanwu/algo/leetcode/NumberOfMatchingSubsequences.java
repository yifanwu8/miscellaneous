package com.yifanwu.algo.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yifan.Wu on 4/15/2021
 */
public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
        // defense from invalid and corner cases
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            letters[ind] = letters[ind] + 1;
        }
        Set<String> rem = new HashSet<>();
        int ct = 0;
        for (String w : words) {
            if (rem.contains(w) || isSubseq(s, w, Arrays.copyOf(letters, letters.length))) {
                ct++;
                rem.add(w);
            }
        }
        return ct;
    }

    private boolean isSubseq(String s, String w, int[] letters) {
        if (w.length() > s.length()) return false; // early termination

        int j = 0; // pointer to s
        for (int i = 0; i < w.length(); i++, j++) { // pointer to w
            int ind = w.charAt(i) - 'a';
            if (letters[ind] <= 0) return false;
            letters[ind] = letters[ind] - 1;
            while (j < s.length() && s.charAt(j) != w.charAt(i)) {
                j++;
            }
            if (j >= s.length()) return false;
        }
        return true;
    }
}
