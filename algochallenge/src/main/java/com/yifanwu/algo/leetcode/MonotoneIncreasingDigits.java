package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/20/2021
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            if (N == 0) return 1;
            return N;
        }
        String nStr = Integer.toString(N);
        int i = 1;
        for (; i < nStr.length(); i++) {
            if (nStr.charAt(i) < nStr.charAt(i - 1)) { // sweep from MSD -> LSD; find the first occurance i > i-1
                break;
            }
        }
        if (i == nStr.length()) return N;
        StringBuilder sb = new StringBuilder();
        int k = i - 2;
        for (; k >= 0; k--) {
            if (nStr.charAt(k) < nStr.charAt(k + 1)) { // sweep from LSD to MSD
                break;
            }
        }
        for (int j = 0; j < nStr.length(); j++) {
            if (j <= k) {
                sb.append(nStr.charAt(j));
            } else if (j == k + 1) {
                    sb.append(Character.toChars(nStr.charAt(j) - 1));
            } else {
                sb.append('9');
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits monotoneIncreasingDigits = new MonotoneIncreasingDigits();
        monotoneIncreasingDigits.monotoneIncreasingDigits(10);
    }
}
