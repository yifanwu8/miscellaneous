package com.yifanwu.algo.sas;

/**
 * Created by WYF on 6/27/2017.
 */
public interface Sorter {

    /**
     * sort the array by natural (comparable) order
     * @param array
     */
    void sort(Comparable[] array);

    /**
     * sort the array by natural (comparable) order
     * @param array
     * @param parallelMinSize custom threshold to use parallel algorithm
     */
    void sort(Comparable[] array, int parallelMinSize);
}
