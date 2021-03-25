package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan.Wu on 3/19/2021
 */
public class StringCompression {
    public int compress(char[] chars) {
        if (chars.length == 0) return 0;

        List<Character> res = new ArrayList<>();
        int ct = 1;
        char letter = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != letter) { // no match
                res.add(letter);
                if (ct > 1) {
                    for (char ctChar : Integer.toString(ct).toCharArray()) {
                        res.add(ctChar);
                    }
                }
                // reset
                letter = chars[i];
                ct = 1;
            } else { // match
                ct++;
            }
        }
        res.add(letter);
        if (ct > 1) {
            for (char ctChar : Integer.toString(ct).toCharArray()) {
                res.add(ctChar);
            }
        }
        for (int i = 0; i < res.size(); i++) {
            chars[i]  = res.get(i);
        }
        return res.size();
    }
}
