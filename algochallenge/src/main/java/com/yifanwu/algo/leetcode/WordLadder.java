package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Yifan.Wu on 4/2/2021
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // defense from invalid/corner cases
        if (!wordList.contains(endWord)) return 0;
        // BFS
        Queue<String> curQ = new ArrayDeque<>();
        Queue<String> nextQ = new ArrayDeque<>();
        curQ.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int count = 2;
        while (!curQ.isEmpty()) {
            String wd = curQ.poll();
            for (String nextWd : wordList) {
                if (!visited.contains(nextWd) && isDifferOneLetter(wd, nextWd)) {
                    if (endWord.equals(nextWd)) return count;
                    nextQ.add(nextWd);
                    visited.add(nextWd);
                }
            }
            if (curQ.isEmpty()) {
                count++;
                curQ = nextQ;
                nextQ = new ArrayDeque<>();
            }
        }
        return 0;
    }

    private boolean isDifferOneLetter(String w1, String w2) {
        int dif = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                dif++;
                if (dif >= 2) return false;
            }
        }
        return dif == 1;
    }
}
