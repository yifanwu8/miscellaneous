package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/10/2021
 */
public class MaxmumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        // defense from invalid and corner cases
        int sum = 0;
        if (cardPoints.length <= k) {
            for (int i : cardPoints)  {
                sum += i;
            }
            return sum;
        }

        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        int res = sum;
        for (int i = 1; i <= k; i++) {
            sum = sum - cardPoints[k - i] + cardPoints[cardPoints.length - i];
            res = Math.max(res, sum);
        }
        return res;
    }
}
