package com.yifanwu.algo.leetcode;

/**
 *   *Design a data structure that supports the following two operations:
 *void addWord(word)
 *bool search(word)
 *search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *For example:
 *addWord("bad")
 *addWord("dad")
 *addWord("mad")
 *search("pad") -> false
 *search("bad") -> true
 *search(".ad") -> true
 *search("b..") -> true
 *Note:
 *You may assume that all words are consist of lowercase letters a-z.
 *
 *
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 *
 * Created by Yifan.Wu on 7/11/2017.
 */
public class WordDictionary {

    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.trie = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        trie.insert(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return trie.search(word);
    }
}
