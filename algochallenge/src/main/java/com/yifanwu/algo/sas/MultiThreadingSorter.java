package com.yifanwu.algo.sas;

import java.util.concurrent.ForkJoinPool;

/**
 * A ForkJoinPool differs from other kinds of ExecutorService mainly by virtue of employing work-stealing:
 * all threads in the pool attempt to find and execute tasks submitted to the pool and/or created by other active tasks
 * (eventually blocking waiting for work if none exist). This enables efficient processing when most tasks spawn other
 * subtasks (as do most ForkJoinTasks)
 * Created by WYF on 6/27/2017.
 */
public class MultiThreadingSorter implements Sorter {

    private ForkJoinPool threadpool;

    public MultiThreadingSorter() {
        // use default constructor using available cores
        this.threadpool = new ForkJoinPool();
    }

    public MultiThreadingSorter(int parallelism) {
        this.threadpool = new ForkJoinPool(parallelism);
    }

    public MultiThreadingSorter(ForkJoinPool threadpool) {
        this.threadpool = threadpool;
    }

    @Override
    public void sort(Comparable[] array) {
        RecursiveSortTask sortTask = new RecursiveSortTask(array);
        threadpool.invoke(sortTask);
    }

    @Override
    public void sort(Comparable[] array, int parallelMinSize) {
        RecursiveSortTask sortTask = new RecursiveSortTask(array, parallelMinSize);
        threadpool.invoke(sortTask);
    }

    public ForkJoinPool getThreadpool() {
        return threadpool;
    }

    public MultiThreadingSorter setThreadpool(ForkJoinPool threadpool) {
        this.threadpool = threadpool;
        return this;
    }
}
