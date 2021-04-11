package com.yifanwu.algo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Yifan.Wu on 4/4/2021
 */
public class GuessTheWord {

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> currentWords = new ArrayList<>(Arrays.asList(wordlist));
        List<String> leftWords = new ArrayList<>();

        Random random = new Random();
        while (!currentWords.isEmpty()) {
            int nextInd = random.nextInt(currentWords.size());
            String guessedWord = currentWords.get(nextInd);
            int score = master.guess(guessedWord);
            if (score == 6) return;
            for (int i = 0; i < currentWords.size(); i++) {
                if (i == nextInd) continue;
                String testWord = currentWords.get(i);
                int matchedChar = matchedChar(guessedWord, testWord);
                if (matchedChar == score) leftWords.add(testWord);
            }
            currentWords = leftWords;
            leftWords = new ArrayList<>();
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
