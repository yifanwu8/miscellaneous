package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * https://leetcode.com/problems/stream-of-characters/
 * Trie
 * @author Yifan.Wu on 3/17/2021
 */
public class StreamChecker {
    private final TrieNode root;
    private final List<Character> letters = new ArrayList<>();

    public StreamChecker(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            int index;
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                index = word.charAt(i) - 'a';
                if (Objects.isNull(node.children[index])) node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.leaf = true;
        }
    }

    public boolean query(char letter) {
        letters.add(letter);
        TrieNode node = root;
        int index;
        for (int i = letters.size() - 1; i >= 0; i--) {
            index = letters.get(i) - 'a';
            if (Objects.isNull(node.children[index])) return false;
            node = node.children[index];
            if (node.isLeaf()) return true;
        }
        return false;
    }

    public static class TrieNode {
        // Initialize your data structure here.
        private TrieNode[] children = new TrieNode[26];
        private boolean leaf = false;

        public TrieNode() {
        }

        public TrieNode insertChar(char letter) {
            int ind = letter - 'a';
            if (ind<0 || ind>25) {return null;}
            if (children[ind] == null) {
                children[ind] = new TrieNode();
            }
            return children[ind];
        }

        public TrieNode travelDown(char letter) {
            int ind = letter - 'a';
            if (ind<0 || ind>25) {return null;}
            return children[ind];
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }
    }
}
