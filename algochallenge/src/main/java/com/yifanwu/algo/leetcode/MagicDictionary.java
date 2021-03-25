package com.yifanwu.algo.leetcode;

import java.util.Objects;

/**
 * https://leetcode.com/problems/implement-magic-dictionary/
 * Trie
 * @author Yifan.Wu on 3/17/2021
 */
public class MagicDictionary {
    private final TrieNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            int index;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (Objects.isNull(node.children[index])) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.leaf = true;
        }
    }

    public boolean search(String searchWord) {
        return oneMissSearch(searchWord, 0, root);
    }

    private boolean oneMissSearch(String searchWord, int charAt, TrieNode node) {
        if (Objects.isNull(node)) return false;
        if (charAt == searchWord.length()) return false;

        int index = searchWord.charAt(charAt) - 'a';
        boolean res;
        if (Objects.nonNull(node.children[index])) {
            res = oneMissSearch(searchWord, charAt + 1, node.children[index]);
            if (res) return true;
        }
        for (int i = 0; i < 26; i++) {
            if (i == index || Objects.isNull(node.children[i])) continue;
            res = noMissSearch(searchWord, charAt + 1, node.children[i]);
            if (res) return true;
        }
        return false;
    }

    private boolean noMissSearch(String searchWord, int charAt, TrieNode node) {
        if (Objects.isNull(node)) return false;
        if (charAt == searchWord.length()) return node.isLeaf();

        int index = searchWord.charAt(charAt) - 'a';
//        if (Objects.isNull(node.children[index])) return false;
        return noMissSearch(searchWord, charAt + 1, node.children[index]);
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
