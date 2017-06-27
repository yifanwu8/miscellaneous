package com.yifanwu.algo.leetcode;


import org.junit.Test;

/**
 * Created by WYF on 6/27/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource("classpath:canplaceflowers.properties")
public class CanPlaceFlowersTest {

//    @Value("${flowerbeds}")
    private int[] flowerbeds = {1,0,0,0,1};

    @Test
    public void testSim() {
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
        System.out.println(canPlaceFlowers.canPlaceFlowers(flowerbeds, 1));
        System.out.println(1 << 13);
    }
}
