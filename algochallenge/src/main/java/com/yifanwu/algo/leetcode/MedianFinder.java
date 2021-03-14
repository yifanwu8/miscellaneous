package com.yifanwu.algo.leetcode;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * heap
 * @author Yifan.Wu on 3/9/2021
 */
public class MedianFinder {

    private Heap minHeapTopHalf; // for top half
    private Heap maxHeapBottomHalf; // for bottom half
    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        minHeapTopHalf = new Heap(false);
        maxHeapBottomHalf = new Heap(true);
    }

    public void addNum(int num) {
        if (minHeapTopHalf.size() == 0 && maxHeapBottomHalf.size() == 0) {
            minHeapTopHalf.add(num);
            return;
        }
        if (minHeapTopHalf.size() == maxHeapBottomHalf.size()) { // 2 halves equal sizes
            if (num <= minHeapTopHalf.peekRoot()) { // belongs to bottom half
                maxHeapBottomHalf.add(num);
            } else {
                minHeapTopHalf.add(num);
            }
            return;
        } else if (minHeapTopHalf.size() > maxHeapBottomHalf.size()) { // maxheap possible size 0; minHeap must non-empty
            if (num <= minHeapTopHalf.peekRoot()) {
                maxHeapBottomHalf.add(num);
            } else { // num > minimum value in min heap
                maxHeapBottomHalf.add(minHeapTopHalf.pop());
                minHeapTopHalf.add(num);
            }
        } else { // max heap size > min heap size
            if (num >= maxHeapBottomHalf.peekRoot()) {
                minHeapTopHalf.add(num);
            } else { // num < max value in max heap of bottom half
                minHeapTopHalf.add(maxHeapBottomHalf.pop());
                maxHeapBottomHalf.add(num);
            }
        }
    }

    public double findMedian() {
        if (maxHeapBottomHalf.size() == 0 && minHeapTopHalf.size() == 0) return Double.NaN;
        if (maxHeapBottomHalf.size() == minHeapTopHalf.size()) {
            return ((1.0 * maxHeapBottomHalf.peekRoot()) + (1.0 * minHeapTopHalf.peekRoot())) / 2.0;
        }
        if (minHeapTopHalf.size() == maxHeapBottomHalf.size() + 1) {
            return 1.0 * minHeapTopHalf.peekRoot();
        }
        if (maxHeapBottomHalf.size() == minHeapTopHalf.size() + 1) {
            return 1.0 * maxHeapBottomHalf.peekRoot();
        }
        throw new IllegalStateException();
    }

    public static class Heap {
        private final boolean isMaxHeap;
        private int[] nums;
        private int nextHeapIndex = 0;

        public Heap(boolean isMaxHeap) {
            this.isMaxHeap = isMaxHeap;
            nums = new int[100];
        }

        public void add(int value) {
            if (nextHeapIndex >= nums.length) {
                doubleArrayLength();
            }
            nums[nextHeapIndex] = value;
            nextHeapIndex++;
            swim();
        }
        public int pop() {
            if (nextHeapIndex <= 0) {
                throw new IllegalArgumentException();
            }
            nextHeapIndex--;
            exchange(0, nextHeapIndex);
            sink();
            return nums[nextHeapIndex];
        }

        public int size() {
            return nextHeapIndex;
        }
        public int peekRoot() {
            return nums[0];
        }

        // swim from the last position of heap up if necessary

        /**
         * private void swim(int k)
         * {
         *  while (k > 1 && less(k/2, k))
         *  {
         *  exch(k, k/2);
         *  k = k/2;
         *  }
         * }
         */
        private void swim() {
            int index = nextHeapIndex - 1;
            int parent;

            while (index > 0) {
                parent = (index - 1) / 2;
                if (compare(nums[index], nums[parent]) > 0) {
                    exchange(index, parent);
                    index = parent;
                } else {
                    return;
                }
            }
        }

        private int compare(int i, int j) {
            if (isMaxHeap) {
                return Integer.compare(i, j);
            } else {
                return Integer.compare(j, i);
            }
        }

        // sink from the position 0 of heap down if necessry

        /**
         *
         * private void sink(int k)
         * {
         *  while (2*k <= N)
         *  {
         *  int j = 2*k;
         *  if (j < N && less(j, j+1)) j++;
         *  if (!less(k, j)) break;
         *  exch(k, j);
         *  k = j;
         *  }
         * }
         */
        private void sink() {
            int index = 0;

            while (true) {
                int leftChild = index * 2 + 1;
                int rightChild = index * 2 + 2;
                if (leftChild >= nextHeapIndex && rightChild >= nextHeapIndex) { // no child
                    return;
                }
                if (rightChild >= nextHeapIndex) { // only left child exist
                    if (compare(nums[leftChild], nums[index]) > 0) {
                        exchange(index, leftChild);
                    }
                    // heap must be a complete binary tree. so can safely return with only left child
                    return;
                }
                if (leftChild < nextHeapIndex && rightChild < nextHeapIndex) { // both children are in heap; redundant check
                    if (compare(nums[leftChild], nums[rightChild]) > 0) { // left > right in max heap
                        if (compare(nums[leftChild], nums[index]) > 0) { // left > parent in max heap
                            exchange(leftChild, index);
                            index = leftChild;
                            continue;
                        } else { // left <= parent in max heap
                            return;
                        }
                    } else { // right >= left in max heap
                        if (compare(nums[rightChild], nums[index]) > 0) { // right > parent in max heap
                            exchange(rightChild, index);
                            index = rightChild;
                            continue;
                        } else { // right <= parent in max heap
                            return;
                        }
                    }
                }
            }
        }

        private void doubleArrayLength() {
            int[] old = nums;
            nums = new int[nums.length * 2];
            for (int i = 0; i < old.length; i++) {
                nums[i] = old[i];
            }
        }

        private void exchange(int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        double res = medianFinder.findMedian();
        medianFinder.addNum(2);
        res = medianFinder.findMedian();
        medianFinder.addNum(3);
        res = medianFinder.findMedian();
        medianFinder.addNum(4);
        res = medianFinder.findMedian();
        medianFinder.addNum(5);
        res = medianFinder.findMedian();
        medianFinder.addNum(6);
        res = medianFinder.findMedian();
        medianFinder.addNum(7);
        res = medianFinder.findMedian();
        medianFinder.addNum(8);
        res = medianFinder.findMedian();
        medianFinder.addNum(9);
        res = medianFinder.findMedian();
        medianFinder.addNum(10);
        res = medianFinder.findMedian();

    }
}
