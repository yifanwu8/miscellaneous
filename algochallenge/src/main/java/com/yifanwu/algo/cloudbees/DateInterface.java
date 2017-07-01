package com.yifanwu.algo.cloudbees;

/**
 * Created by WYF on 6/30/2017.
 */
public interface DateInterface {

    /**
     * add days to current date.
     * @param days days to add to current date (can be negative)
     * @return a new date
     */
    DateInterface addDays(int days);

}
