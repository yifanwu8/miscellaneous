package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/1/2021
 */
public class DecodeString {
    public String decodeString(String s) {
        // defense from invalid and corner cases
        if (s == null || s.isEmpty()) return s;
        return helper(s, 0).result;
    }

    private StrAndIndex helper(String s, int index) {
        StringBuilder sb = new StringBuilder();
        int i = index;
        while (true) {
            if (isDigit(s, i)) {
                int j = i+ 1;
                for (; isDigit(s, j); j++ ) {}
                int repeat = Integer.parseInt(s.substring(i, j));
                // j must stop at '['
                StrAndIndex si = helper(s, j + 1);
                for (int r = 0; r < repeat; r++) {
                    sb.append(si.result);
                }
                i = si.index;
            } else if (isLetter(s, i)) {
                int j = i+ 1;
                // outbound, ] , digit
                for (; j < s.length() && isLetter(s, j); j++) {}
                sb.append(s, i, j);
                i = j;
            }
            // finish when outbound or ]
            if (i == s.length()) return new StrAndIndex(sb.toString(), i);
            if (s.charAt(i) == ']') return new StrAndIndex(sb.toString(), ++i);
        }
    }

    private boolean isDigit(String s, int index) {
        return s.charAt(index) <= '9' && s.charAt(index) >= '0';
    }
    private boolean isLetter(String s, int index) {
        return s.charAt(index) <= 'z' && s.charAt(index) >= 'a';
    }


    private static class StrAndIndex {
        private final String result;
        private final int index;

        public StrAndIndex(String result, int index) {
            this.result = result;
            this.index = index;
        }
    }
}
