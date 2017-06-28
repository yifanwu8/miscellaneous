package com.yifanwu.algo.sas;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * To reduce the scope of the problem, we assume sorting Array of objects instead of list and primitives.
 * Thus, merge sort is chosen because it is stable and Array random index access is constant.
 * We also assume all objects are non-null. Please see note in merge method for processing null value
 *
 * A ForkJoinTask is a thread-like entity that is much lighter weight than a normal thread. Huge numbers of tasks and
 * subtasks may be hosted by a small number of actual threads in a ForkJoinPool,
 * at the price of some usage limitations.
 * Created by WYF on 6/27/2017.
 */
public class RecursiveSortTask extends RecursiveAction {

    private int parallelMinSize = 1024;

    private final Comparable[] array;
    private final int lo, hi;

    public RecursiveSortTask(Comparable[] array) {
        this(array, 0, array.length);
    }

    public RecursiveSortTask(Comparable[] array, int parallelMinSize) {
        this(array, 0, array.length);
        setParallelMinSize(parallelMinSize);
    }

    /**
     *
     * @param array
     * @param lo inclusive; start of the index
     * @param hi exclusive; end of the index
     */
    private RecursiveSortTask(Comparable[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    protected void compute() {
        if (array.length == 0) {  // corner case
            return;
        }

        if (isUsingParallel()) {
            // not using bit operation; let compiler do it
            int mid = (lo + hi) / 2;
            RecursiveSortTask task1 = (new RecursiveSortTask(array, lo, mid)).setParallelMinSize(getParallelMinSize());
            RecursiveSortTask task2 = (new RecursiveSortTask(array, mid, hi)).setParallelMinSize(getParallelMinSize());
            ForkJoinTask.invokeAll(task1, task2);
            // MARKHERE: merge is not parallelized here. It can be improved by parallelized merge algorithm
            merge(lo, mid, hi);
        } else {
            nonParellelSort(lo, hi);
        }
    }

    private boolean isUsingParallel() {
        return hi - lo >= parallelMinSize;
    }

    /**
     * simple serial sorting
     * @param lo inclusive; start of the index
     * @param hi exclusive; end of the index
     */
    private void nonParellelSort(int lo, int hi) {
        // either use an built-in sort or custom sort
//         Arrays.sort(array, lo, hi);
        // For completeness, I will use a custom merge sort
        if (lo < hi - 1) {
            // Find the middle point
            int mid = (lo + hi) / 2;

            // Sort first and second halves
            nonParellelSort(lo, mid);
            nonParellelSort(mid, hi);

            // Merge the sorted halves
            merge(lo, mid, hi);
        }
    }

    /**
     * Merge 2 subarrays
     * @param lo
     * @param mid
     * @param hi
     */
    private void merge(int lo, int mid, int hi) {
        // MARKHERE: additional space O(n) for merge
        Comparable[] tempArray = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < tempArray.length; j++) {
            //MARKHERE: if we want to also sort null object, we can do something here.
            if (k == hi ||               // index k reach the end faster than i
                    tempArray[i].compareTo(array[k]) <= 0) { // copy the smaller of the two
                array[j] = tempArray[i++];
            } else {
                array[j] = array[k++];
            }
        }
    }

    public Comparable[] getArray() {
        return array;
    }

    public int getLo() {
        return lo;
    }

    public int getHi() {
        return hi;
    }

    public int getParallelMinSize() {
        return parallelMinSize;
    }

    public RecursiveSortTask setParallelMinSize(int parallelMinSize) {
        this.parallelMinSize = parallelMinSize;
        return this;
    }
}
