package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yifan.Wu on 4/14/2021
 */
public class MinimumAreaRectangleII {
    public double minAreaFreeRect(int[][] points) {
        // defense from invalid and corner cases
        if (points.length < 4) return 0.0d;

        Map<MidPointLenthSquare, List<PairPoints>> map = new HashMap<>();
        double minA = Double.MAX_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                PairPoints pp = new PairPoints(p1, p2);
                MidPointLenthSquare mpl = new MidPointLenthSquare(pp);
                List<PairPoints> ppList = map.getOrDefault(mpl, new ArrayList<>());
                if (ppList.size() == 0) { // first pair
                    map.put(mpl, ppList);
                } else { // can form rectangle with other pairs
                    for (PairPoints eachPp : ppList) {
                        double area = calculateArea(pp, eachPp);
                        if (Double.compare(0.0d, area) < 0) {  // in case duplicate point
                            minA = Math.min(minA, area);
                        }
                    }
                }
                ppList.add(pp);
            }
        }
        return minA == Double.MAX_VALUE ? 0.0d : minA;
    }

    private class MidPointLenthSquare { // mid point and length square of a pair points
        private final double[] midP; // use descriptive name in real code
        private final double lenSq;  // length square between a pair of points; assume no overflow

        public MidPointLenthSquare(PairPoints pair) {
            // implement
            midP = new double[2];
            midP[0] = (pair.p1[0] - pair.p2[0]) / 2.0d + pair.p2[0];
            midP[1] = (pair.p1[1] - pair.p2[1]) / 2.0d + pair.p2[1];
            lenSq = Math.pow((pair.p1[0] - pair.p2[0]), 2) + Math.pow(pair.p1[1] - pair.p2[1], 2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MidPointLenthSquare)) return false;
            MidPointLenthSquare that = (MidPointLenthSquare) o;
            return lenSq == that.lenSq && Arrays.equals(midP, that.midP);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(lenSq);
            result = 31 * result + Arrays.hashCode(midP);
            return result;
        }
    }

    private class PairPoints {
        private final int[] p1;
        private final int[] p2;

        public PairPoints(int[] p1, int[] p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    private double calculateArea(PairPoints pp1, PairPoints pp2) {
        int[] corner = pp2.p1;
        double len1Sq = Math.pow((pp1.p1[0] - corner[0]), 2) + Math.pow(pp1.p1[1] - corner[1], 2); // delta x square  + delta y square
        double len2Sq = Math.pow((pp1.p2[0] - corner[0]), 2) + Math.pow(pp1.p2[1] - corner[1], 2);
        return Math.sqrt(len1Sq * len2Sq);
    }

    public static void main(String[] args) {
        MinimumAreaRectangleII rectangleII = new MinimumAreaRectangleII();
        rectangleII.minAreaFreeRect(new int[][]{{1,2},{2,1},{1,0},{0,1}});
    }
}
