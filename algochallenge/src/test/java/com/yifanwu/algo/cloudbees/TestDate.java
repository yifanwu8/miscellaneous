package com.yifanwu.algo.cloudbees;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by WYF on 7/1/2017.
 */
public class TestDate {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void oneYearTest() {
        Assert.assertEquals(DateNonLeap.MIN_DATE.addDays(365),
                new DateNonLeap(DateNonLeap.MIN_YEAR+1, 1, 1));
    }

    @Test
    public void validationTest() {
        exception.expect(IllegalArgumentException.class);
        DateNonLeap.MIN_DATE.addDays(-1);
        throw new IllegalStateException("Test failed.");
    }

    @Test
    public void arbitraryDaysTest() {
        DateInterface oldDate = new DateNonLeap(2017, 10, 15);
        Assert.assertEquals(new DateNonLeap(2017, 3, 29),
                oldDate.addDays(-200));
        Assert.assertEquals(new DateNonLeap(2018, 5, 3),
                oldDate.addDays(200));

        Assert.assertEquals(new DateNonLeap(2017 - 10, 3, 29),
                oldDate.addDays(-200 - DateNonLeap.TOTAL_DAYS_ONE_YEAR * 10));
        Assert.assertEquals(new DateNonLeap(2018 + 10, 5, 3),
                oldDate.addDays(200 + DateNonLeap.TOTAL_DAYS_ONE_YEAR * 10));
    }

    @Test
    public void betweenDaysTest() {
        Assert.assertEquals(-368649634,
                DateNonLeap.MIN_DATE.daysFrom(DateNonLeap.MAX_DATE));
        Assert.assertEquals(368649634,
                DateNonLeap.MAX_DATE.daysFrom(DateNonLeap.MIN_DATE));

        Assert.assertEquals(200,
                new DateNonLeap(2017, 10, 15).daysFrom(
                new DateNonLeap(2017, 3,29)
        ));
        Assert.assertEquals(-200,
                new DateNonLeap(2017, 10, 15).daysFrom(
                        new DateNonLeap(2018, 5,3)
                ));

    }
}
