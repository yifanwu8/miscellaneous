package com.yifanwu.algo.tanium;

import org.junit.Test;

/**
 * Created by WYF on 7/14/2017.
 */
public class TaniumTest {


    @Test
    public void simpleTest() throws Exception {
        for (int students = 1; students <= 20; students++) {

            int res = Tanium.circle(students);
            System.out.println(res);
        }

    }
}
