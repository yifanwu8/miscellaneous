package com.yifanwu.algo.leetcode;

import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 3/22/2021
 */
public class MyCalendar {
    private final NavigableMap<Integer, Booking> bookingsBystartTime = new TreeMap<>();

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Booking> prevBooking = bookingsBystartTime.floorEntry(start);
        Map.Entry<Integer, Booking> nextBooking = bookingsBystartTime.ceilingEntry(start);
        if (Objects.nonNull(prevBooking) && (start < prevBooking.getValue().end)) {
            return false;
        }
        if (Objects.nonNull(nextBooking) && (end > nextBooking.getValue().start)) {
            return false;
        }
        bookingsBystartTime.put(start, new Booking(start, end));
        return true;
    }

    private static class Booking {
        private final int start;
        private final int end;

        public Booking(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
