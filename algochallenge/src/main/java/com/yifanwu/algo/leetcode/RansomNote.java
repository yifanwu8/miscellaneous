package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/17/2021
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] alphabetTabe = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            alphabetTabe[magazine.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            if (alphabetTabe[index] <= 0) {
                return false;
            } else {
                alphabetTabe[index] -= 1;
            }
        }
        return true;
    }
}
