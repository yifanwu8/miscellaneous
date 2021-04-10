package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yifan.Wu on 3/27/2021
 */
public class StickersToSpellWord {
    public int minStickers(String[] stickers, String target) {
        // defense from invalide and corner cases
        Map<Character, Integer> remaining = new HashMap<>();
        List<Character> targetLetters = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            targetLetters.add(target.charAt(i));
        }
        populateRemaining(remaining, targetLetters);
        return helper(stickers, 0, remaining);
    }

    private int helper(String[] stickers, int index, Map<Character, Integer> remaining) {
        if (remaining.isEmpty()) return 0;
        if (index >= stickers.length) return -1;

        int res = Integer.MAX_VALUE;
        List<Character> removed = new ArrayList<>();
        List<List<Character>> allRemoved = new ArrayList<>();
        for (int i = 0; ; i++) {
            int sub = helper(stickers, index + 1, remaining);
            if (sub != -1) {
                res = Math.min(res, i + sub);
            }
            if (res <= i) break;
            removed = getRemaining(stickers[index], remaining);
            if (removed.isEmpty()) break;
            allRemoved.add(removed);
        }
        for (List<Character> letters : allRemoved) {
            populateRemaining(remaining, letters);
        }
        if (res == Integer.MAX_VALUE) return -1;
        return res;
    }

    private void populateRemaining(Map<Character, Integer> remaining, List<Character> letters) {
        for (Character letter : letters) {
            if (remaining.containsKey(letter)) {
                remaining.put(letter, remaining.get(letter) + 1);
            } else {
                remaining.put(letter, 1);
            }
        }
    }

    // return removed chars
    private List<Character> getRemaining(String word, Map<Character, Integer> origin) {
        List<Character> removed = new ArrayList<>();
        for (int j = 0; j < word.length(); j++) {
            if (origin.containsKey(word.charAt(j))) {
                int t = origin.get(word.charAt(j));
                t--;
                removed.add(word.charAt(j));
                if (t <= 0) {
                    origin.remove(word.charAt(j));
                } else {
                    origin.put(word.charAt(j), t);
                }
            }
        }
        return removed;
    }

    public static void main(String[] args) {
        StickersToSpellWord stickersToSpellWord = new StickersToSpellWord();
        stickersToSpellWord.minStickers(new String[] {"with","example","science"}, "thehat");
    }
}
