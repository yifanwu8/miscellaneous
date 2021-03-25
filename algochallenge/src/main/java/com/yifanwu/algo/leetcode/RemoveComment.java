package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yifan.Wu on 3/25/2021
 */
public class RemoveComment {
    public List<String> removeComments(String[] source) {
        // defend from invalid or corner cases

        List<String> res = new ArrayList<>();
        Deque<String> stringsDeque = new LinkedList<>(Arrays.asList(source.clone()));
        while (!stringsDeque.isEmpty()) {
            String line = stringsDeque.removeFirst();
            boolean skip = false;
            for (int i = 0; i < line.length() - 1; i++) {
                if (line.charAt(i) == '/') {
                    if (line.charAt(i + 1) == '/') { // see simple comment; remove from i to end
                        line = removeSimpleComment(line, i);
                        break;
                    } else if (line.charAt(i + 1) == '*') {
                        // block comment; removed and start line and end lin concatentate together and push back
                        removeBlockComment(line, i, stringsDeque);
                        skip = true;
                        break;
                    }
                }
            }
            if (!skip) {
                if (!line.isEmpty()) {
                    res.add(line);
                }
            }
        }
        return res;
    }

    private String removeSimpleComment(String line, int startIndex) {
        return line.substring(0, startIndex);
    }

    private void removeBlockComment(String line, int startIndex, Deque<String> deque) {
        String startLine = line;
        String eachLine = line;
        int scanStart = startIndex + 2;
        while (Objects.nonNull(eachLine)) {
            for (int i = scanStart; i < eachLine.length() - 1; i++) {
                if (eachLine.charAt(i) == '*') {
                    if (eachLine.charAt(i + 1) == '/') { // find the closing block
                        String newLine;
                        if (i + 2 < eachLine.length()) {
                            newLine = startLine.substring(0, startIndex) + eachLine.substring(i + 2);
                        } else {
                            newLine = startLine.substring(0, startIndex);
                        }
                        deque.addFirst(newLine);
                        return;
                    }
                }
            }
            eachLine = deque.removeFirst();
            scanStart = 0;
        }
        throw new IllegalStateException("no closing block");
    }

}
