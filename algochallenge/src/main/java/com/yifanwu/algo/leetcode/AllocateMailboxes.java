package com.yifanwu.algo.leetcode;

import java.util.Arrays;

/**
 * 3D array DP
 * https://leetcode.com/problems/allocate-mailboxes/
 * @author Yifan.Wu on 3/19/2021
 */
public class AllocateMailboxes {

    public int minDistance(int[] houses, int k) {
        int[][][] table = new int[houses.length][houses.length][k + 1];
        for (int i= 0; i < houses.length; i++) {
            for (int j = 0; j < houses.length; j++) {
                for (int kk = 1; kk <= k; kk++)
                table[i][j][kk] = -1;
            }
        }
        Arrays.sort(houses);
        return helper(houses, k, 0, houses.length - 1, table);
    }

    private int helper(int[] houses, int k, int start, int end, int[][][] table) {
        if (start > end) return 0;
        if (table[start][end][k] >= 0) {
            return table[start][end][k];
        }
        if (k == end - start + 1) {
            table[start][end][k] = 0;
            return 0;
        }
        int save = Integer.MAX_VALUE;
        if (k == 1) {
            save = houses[end] - houses[start] + helper(houses, 1, start + 1, end - 1, table);
            table[start][end][1] = save;
            return save;
        }

        for (int ink = 1; ink < k; ink++) {
            for (int mid = start + ink - 1; mid <= end - (k - ink); mid++) {
                int calculated = helper(houses, ink, start, mid, table) + helper(houses, k - ink, mid + 1, end, table);
                save = Math.min(save, calculated);
            }
        }
        table[start][end][k] = save;
        return save;
    }

    public static void main(String[] args) {
        AllocateMailboxes allocateMailboxes = new AllocateMailboxes();
        allocateMailboxes.minDistance(new int[] {1,4,8,10,20}, 3);

    }
}
