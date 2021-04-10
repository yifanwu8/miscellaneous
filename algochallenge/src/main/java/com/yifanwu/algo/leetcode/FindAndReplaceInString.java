package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Yifan.Wu on 4/4/2021
 */
public class FindAndReplaceInString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        // defense from invalid and corner cases
        Queue<Replacement> replacements = new PriorityQueue<>(Comparator.comparingInt(o -> o.index));
        for (int i = 0; i < indexes.length; i++) {
            boolean isMatch = isMatch(S, indexes[i], sources[i]);
            if (isMatch) {
                replacements.add(new Replacement(indexes[i], sources[i].length(), targets[i]));
            }
        }
        // output
        StringBuilder sb = new StringBuilder();
        Replacement replace = replacements.poll();
        for (int i = 0; i < S.length();) {
            if (replace != null && replace.index == i) {
                sb.append(replace.replacement);
                i += replace.size;
                replace = replacements.poll();
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    private boolean isMatch(String S, int index, String match) {
        for (int i = 0; i < match.length(); i++) {
            int indexAtS = i + index;
            if (indexAtS >= S.length() || S.charAt(indexAtS) != match.charAt(i)) return false;
        }
        return true;
    }

    private static class Replacement {
        private final int index;
        private final int size;
        private final String replacement;

        public Replacement(int index, int size, String replacement) {
            this.index = index;
            this.size = size;
            this.replacement = replacement;
        }
    }

    public static void main(String[] args) {
        FindAndReplaceInString replaceInString = new FindAndReplaceInString();
        String res = replaceInString.findReplaceString(
                "vmokgggqzp",
                new int[]{3, 5, 1},
                new String[]{"kg", "ggq", "mo"},
                new String[]{"s", "so", "bfr"}
        );
    }
}
