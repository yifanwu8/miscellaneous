package com.yifanwu.algo.leetcode;

/**
 * Created by Yifan.Wu on 7/11/2017.
 */
public class TrieNode {
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
