package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/10/2021
 */
public class MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        // defense from invalild and corner case

        int[][] memoi = new int[S.length()][T.length()];
        int min = Integer.MAX_VALUE;
        int leftInd = S.length();
        for (int i = 0; i < S.length() - T.length() + 1; i++) {
            if (S.charAt(i) == T.charAt(0)) {
                int s = helper(S, i, T, 0, memoi);
                if (s != -1 && s < min) {
                    leftInd = i;
                    min = s;
                }
            }
        }
        if (leftInd < S.length()) {
            return S.substring(leftInd, leftInd + min);
        } else {
            return "";
        }
    }

    private int helper(String S, int indS, String T, int indT, int[][] memoi) {
        if (indT == T.length()) return 0;
        if (indS == S.length()) return -1;

        if (memoi[indS][indT] != 0) return memoi[indS][indT];

        int sub;
        if (S.charAt(indS) == T.charAt(indT)) {
            sub = helper(S, indS + 1, T, indT + 1, memoi);
        } else {
            sub = helper(S, indS + 1, T, indT, memoi);
        }
        if (sub == -1) {
            memoi[indS][indT] = -1;
        } else {
            memoi[indS][indT] = 1 + sub;
        }
        return memoi[indS][indT];
    }
}
