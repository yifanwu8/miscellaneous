package com.yifanwu.algo.leetcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yifan.Wu on 7/11/2017.
 */
public class NextPermutationTest {

    private NextPermutation nextPermutation = new NextPermutation();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void nextPermutation() throws Exception {
        int[] nums = {2,3,1};
        nextPermutation.nextPermutation(nums);
        System.out.println(nums);
    }

}