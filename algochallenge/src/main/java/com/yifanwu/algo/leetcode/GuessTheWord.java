package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author Yifan.Wu on 4/4/2021
 */
public class GuessTheWord {

    public void findSecretWord(String[] wordlist, Master master) {
        Queue<String> currentWords = new ArrayDeque<>(Arrays.asList(wordlist));
        Queue<String> leftWords = new ArrayDeque<>();

        while (!currentWords.isEmpty()) {
            String guessedWord = currentWords.poll();
            int score = master.guess(guessedWord);
            if (score == 6) return;
            while (!currentWords.isEmpty()) {
                String testWord = currentWords.poll();
                int matchedChar = matchedChar(guessedWord, testWord);
                if (matchedChar == score) leftWords.add(testWord);
            }
            currentWords = leftWords;
        }
    }

    private int matchedChar(String origin, String testWord) {
        int count = 0;
        for (int i = 0; i < origin.length(); i++){
            if (origin.charAt(i ) == testWord.charAt(i)) count++;
        }
        return count;
    }

    private static class Master {
        public int guess(String word) {
            return 0;
        }
    }


}
