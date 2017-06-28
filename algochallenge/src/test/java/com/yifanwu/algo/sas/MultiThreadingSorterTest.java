package com.yifanwu.algo.sas;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by WYF on 6/28/2017.
 */
public class MultiThreadingSorterTest {

    private MultiThreadingSorter sorter;

    @Before
    public void setUp() {
        sorter = new MultiThreadingSorter();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSmallLengthArray() {
        Integer[] arr = {};
        sorter.sort(arr);
        Assert.assertTrue(isSorted(arr));
        Double[] doubles = {1.5};
        sorter.sort(doubles);
        Assert.assertTrue(isSorted(doubles));
    }

    @Test
    public void testMidLenth() {
        Float[] floats = {5.6f, 4.3f, -0.5f, 7.8f, -100.4f};
        sorter.sort(floats, 4);
        Assert.assertTrue(isSorted(floats));

    }

    @Test
    public void testLarge() {
        int size = 100000;
        Random rand = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }

        sorter.sort(arr, 1024);
        Assert.assertTrue(isSorted(arr));
    }

    private boolean isSorted(Comparable[] arr) {
        if (arr.length <= 1) {
            return true;
        }
        Comparable prev = arr[0];
//        System.out.println(prev);
        for (int i = 1; i < arr.length; i++) {
            if (prev.compareTo(arr[i]) > 0) {
                return false;
            }
            prev = arr[i];
//            System.out.println(prev);
        }
        return true;
    }
}
