package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yifan.Wu on 3/15/2021
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        if (num < 0 || num >= 9) {
            return Collections.emptyList();
        }
        boolean[] onBit = new boolean[10];
        List<String> res = new ArrayList<>();
        readBinaryWatchHelper(res, num, onBit, 9);
        return res;
    }

    private void readBinaryWatchHelper(List<String> res,
                                       int remaining, // how many bits remained need to be assigned
                                       boolean[] onBit,
                                       int pointer    // point from 9 down 0, -1 means done
    ) {
        if (remaining < 0) return;
        if (remaining == 0 && pointer < 0) { // found a possible solution
            int hour = 0;
            for (int i = 0; i < 4; i++) {
                hour += (1 << i) * (onBit[i + 6] ? 1 : 0);
            }
            int min = 0;
            for (int i = 0; i < 6; i++) {
                min += (1 << i) * (onBit[i] ? 1 : 0);
            }
            StringBuilder sb = new StringBuilder(Integer.toString(hour)).append(":")
                    .append(String.format("%02d", min));
            res.add(sb.toString());
            return;
        }
        if (pointer < 0) {  // if pointer out of index but still remaining
            return;
        }
        if (onBit[9] && onBit[8]) { // hour >= 12
            return;
        }
        if (onBit[5] && onBit[4] && onBit[3] && onBit[2]) { // min >= 60
            return;
        }
        readBinaryWatchHelper(res, remaining, onBit, pointer - 1); // move to next without setting current pointer on

        onBit[pointer] = true;
        readBinaryWatchHelper(res, remaining - 1, onBit, pointer - 1); //set current point on and move to next
        onBit[pointer] = false;
    }

    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        binaryWatch.readBinaryWatch(1);
    }
}
