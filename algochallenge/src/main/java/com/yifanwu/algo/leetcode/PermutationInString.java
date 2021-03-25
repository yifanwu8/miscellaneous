package com.yifanwu.algo.leetcode;

import java.util.Arrays;

/**
 * @author Yifan.Wu on 3/14/2021
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() <= 0) {
            return true;
        }
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] s1Chars = new int[26];
        for (int i = 0; i < s1.length(); i++) {  // count all chars
            s1Chars[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (s1Chars[s2.charAt(i) - 'a'] > 0) { // letter in s2 is in s1
                int[] copyArray = Arrays.copyOf(s1Chars, s1Chars.length);
                for (int j = 0; j < s1.length(); j++) {
                    copyArray[s2.charAt(i + j) - 'a']--;
                    if (copyArray[s2.charAt(i + j) - 'a'] < 0) { // char in s2 not in s1
                        break;
                    } else if (j == s1.length() - 1) { // matched all letters count
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
