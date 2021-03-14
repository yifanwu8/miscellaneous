package com.yifanwu.algo.leetcode;

import java.util.Objects;

/**
 * Created by Yifan.Wu on 7/11/2017.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode travelNode = root;
        for (int i=0; i<word.length(); i++) {
            travelNode = travelNode.insertChar(word.charAt(i));
        }
        travelNode.setLeaf(true);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return subSearch(root, word, 0);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode travelNode = root;
        for (int i=0; i<prefix.length(); i++) {
            travelNode = travelNode.travelDown(prefix.charAt(i));
            if (travelNode == null) {
                return false;
            }
        }
        return true;
    }

    private boolean subSearch(TrieNode node, String word, int start) {
        if (Objects.isNull(node)) {
            return false;
        }
        if (start == word.length()) {
            return node.isLeaf();
        }
        char ch = word.charAt(start);
        if (ch == '.') {
            for (char i = 'a'; i <= 'z'; i++) {
                boolean tempRes = subSearch(node.travelDown(i), word, start + 1);
                if (tempRes) {
                    return true;
                }
            }
            return false;
        } else {
            return subSearch(node.travelDown(ch), word, start + 1);
        }
    }
}
