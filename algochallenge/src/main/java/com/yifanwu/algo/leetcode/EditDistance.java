package com.yifanwu.algo.leetcode;

/**
 * DP dynamic programming
 * https://leetcode.com/problems/edit-distance/
 * @author Yifan.Wu on 3/19/2021
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int[][] table = new int[word1.length()][word2.length()];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j<table[0].length; j++) {
                table[i][j] = -1;
            }
        }
        return topDownRecursiveDp(word1, 0, word2, 0, table);
    }

    // top-down recursive DP has more overhead and constant factor
    private int topDownRecursiveDp(String word1, int index1, String word2, int index2, int[][] table) {
        if (index1 >= word1.length()) {
            return word2.length() - index2;
        }
        if (index2 >= word2.length()) {
            return word1.length() - index1;
        }
        if (table[index1][index2] >= 0) {
            return table[index1][index2];
        }
        int save = 0;
        if (word1.charAt(index1) == word2.charAt(index2)) {
            save = topDownRecursiveDp(word1, index1 + 1, word2, index2 + 1, table);
        } else {
            save = Math.min(
                    1 + topDownRecursiveDp(word1, index1 + 1, word2, index2 + 1, table),  // replace
                    1 + topDownRecursiveDp(word1, index1, word2, index2 + 1, table)              // delete word2 letter
            );
            save = Math.min(save,
                    1 + topDownRecursiveDp(word1, index1 + 1, word2, index2, table));                // insert into word2
        }
        table[index1][index2] = save;
        return save;
    }

    public static void main(String[] args) {

    }
}
