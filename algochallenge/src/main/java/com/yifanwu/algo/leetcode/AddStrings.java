package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 3/18/2021
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        String longer, shorter;
        if (num1.length() >= num2.length()) {longer = num1; shorter = num2;}
        else {shorter = num1; longer = num2;}

        boolean count = false;
        StringBuilder sb = new StringBuilder();
        for (int i = longer.length() - 1, j = shorter.length() - 1; i >= 0; i--, j--) {
            int d = (longer.charAt(i) - '0') + ((j >= 0) ? (shorter.charAt(j) - '0') : 0) + (count ? 1 : 0);
            count = d >= 10;
            sb.append((d % 10));
        }
        if (count) sb.append(1);
        return sb.reverse().toString();
    }
}
