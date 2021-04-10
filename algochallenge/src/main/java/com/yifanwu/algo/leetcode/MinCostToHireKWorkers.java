package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Yifan.Wu on 3/30/2021
 */
public class MinCostToHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        if (K > quality.length || quality.length != wage.length) throw new IllegalArgumentException();

        List<DataEntry> qualitySorted = new ArrayList<>(quality.length);
        for (int i = 0; i < quality.length; i ++) {
            qualitySorted.add(new DataEntry(quality[i], wage[i], 1.0d * wage[i] / quality[i], 0 ));
        }
        qualitySorted.sort(new Comparator<DataEntry>() {
            @Override
            public int compare(DataEntry o1, DataEntry o2) {
                return Integer.compare(o1.quality, o2.quality);
            }
        });
        int sumOfSamllQuality = 0;
        for (int i = 0; i < K; i++) {
            sumOfSamllQuality += qualitySorted.get(i).quality;
        }

        // PQ is fine too
        PriorityQueue<DataEntry> ratioSorted = new PriorityQueue<>(quality.length, new Comparator<DataEntry>() {
            @Override
            public int compare(DataEntry o1, DataEntry o2) {
                return Double.compare(o2.ratio, o1.ratio);
            }
        });
        for (int index = 0; index < qualitySorted.size(); index++) {
            DataEntry dataEntry = qualitySorted.get(index);
            ratioSorted.add(new DataEntry(dataEntry.quality, dataEntry.wage, dataEntry.ratio, index));
        }

        double minCost = Double.MAX_VALUE;
        int pointerToQualitySorted = K - 1;
        for (int i = 0; i <= quality.length - K; i++) {
            DataEntry dataEntry = ratioSorted.poll();
            double ratio = dataEntry.ratio;
            if (dataEntry.index <= pointerToQualitySorted) {  // item inside the pointer
                minCost = Math.min(minCost, ratio * sumOfSamllQuality);
                sumOfSamllQuality -= qualitySorted.get(dataEntry.index).quality;
                while (++pointerToQualitySorted < qualitySorted.size() && qualitySorted.get(pointerToQualitySorted).used) {
                }
                if (pointerToQualitySorted < qualitySorted.size()) {
                    sumOfSamllQuality += qualitySorted.get(pointerToQualitySorted).quality;
                }
            } else { // item outside the pointer
                minCost = Math.min(minCost,
                        dataEntry.wage + ratio * (sumOfSamllQuality - qualitySorted.get(pointerToQualitySorted).quality));
                qualitySorted.get(dataEntry.index).used = true;
            }
        }
        return minCost;
    }

    private static class DataEntry {
        private final int quality;
        private final int wage;
        private final double ratio;
        private final int index;
        private boolean used;

        public DataEntry(int quality, int wage, double ratio, int index) {
            this.quality = quality;
            this.wage = wage;
            this.ratio = ratio;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        MinCostToHireKWorkers minCostToHireKWorkers = new MinCostToHireKWorkers();
        double res = minCostToHireKWorkers.mincostToHireWorkers(new int[] {10,20,5}, new int[]{70,50,30}, 2);
    }
}
