package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yifan.Wu on 4/14/2021
 */
public class ConfusingNumberII {
    private final List<Integer> nonMsd = Arrays.asList(0,1,6,8,9);
    private final List<Integer> msd = Arrays.asList(1,6,8,9);
    private final Map<Integer, Integer> map = new HashMap<>();

    public int confusingNumberII(int N) {
        // defense from invalid and corner case reminder
        if (N <= 1) return 0;
        // use descriptive name in real-code
        map.put(0,0);
        map.put(1,1);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);

        int dM = 0;
        for (int nN = N; nN > 0; nN = nN / 10) {
            dM++;
        }
        List<Long> numList = new ArrayList<>(); // all valid rotatable numbers
        for (int i = 1; i <= dM; i++) {
            List<Integer> digList = new ArrayList<>(i);   // 0: MSD; size - 1: LSD
            for (int j = 0; j < i; j++) { // initialize list
                digList.add(-1);
            }
            generate(numList, digList, digList.size() - 1, N);  // move back ward
        }
        int ct = 0;
        for (long num : numList) {
            if (isConfusing(num)) {
                ct++;
            }
        }
        return ct;
    }

    // use char instead of int to save space; terminate the outer recursion once go out of Max because of increasing order
    private void generate(List<Long> numList, List<Integer> digList, int ind, int max) {
        if (ind < 0) { // terminate condition
            long num = 0;
            for (int i : digList) {
                num *= 10;
                num += i;
            }
            if (num <= max) numList.add(num);
            return;
        }

        if (ind == 0) { // MSD
            for (int i : msd) {
                digList.set(ind, i);
                generate(numList, digList, ind - 1, max);
            }
        } else {
            for (int i : nonMsd) { // not MSD
                digList.set(ind, i);
                generate(numList, digList, ind - 1, max);
            }
        }
        digList.set(ind, -1);
    }

    private boolean isConfusing(long num) {
        int rot = 0;
        for (long dn = num; dn > 0; dn = dn/10) {
            rot *= 10;
            int dig = (int) dn % 10; // LSD
            rot += map.get(dig);
        }
        return rot != num;
    }

    public static void main(String[] args) {
        ConfusingNumberII confusingNumberII = new ConfusingNumberII();
        int res = confusingNumberII.confusingNumberII(1000000000);
    }
}
