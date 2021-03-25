package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 3/22/2021
 */
public class MyCalendarTwo {
    private final List<Booking> bookings = new ArrayList<>();
    private final TreeMap<Integer, Booking> doubleBooking = new TreeMap<>(); // double booked intervals key by the start time

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Booking> prevDoubleBooking = doubleBooking.floorEntry(start);
        Map.Entry<Integer, Booking> nextDoubleBooking = doubleBooking.ceilingEntry(start);
        if (Objects.nonNull(prevDoubleBooking) && (start < prevDoubleBooking.getValue().end)) return false;
        if (Objects.nonNull(nextDoubleBooking) && (end > nextDoubleBooking.getValue().start)) return false;
        Booking newBooking = new Booking(start, end);
        for (Booking eachBooking : bookings) {
            Booking overlapped = getOverlap(eachBooking, newBooking);
            if (Objects.nonNull(overlapped)) doubleBooking.put(overlapped.start, overlapped);
        }
        bookings.add(newBooking);
        return true;
    }

    private Booking getOverlap(Booking existingBooking, Booking newBooking) {
        Booking earlyStart, lateStart;
        if (existingBooking.start <= newBooking.start) {
            earlyStart = existingBooking;
            lateStart = newBooking;
        } else {
            earlyStart = newBooking;
            lateStart = existingBooking;
        }
        if (lateStart.start >= earlyStart.end) return null; // no overlap
        return new Booking(lateStart.start, Math.min(earlyStart.end, lateStart.end));
    }

    private static class Booking {
        private final int start;
        private final int end;

        public Booking(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        boolean res;
        res = myCalendarTwo.book(5,12);
        res = myCalendarTwo.book(42,50);
        res = myCalendarTwo.book(4,9);
        res = myCalendarTwo.book(33,41);
        res = myCalendarTwo.book(2,7);
        res = myCalendarTwo.book(16,25);
        res = myCalendarTwo.book(7,16);
        res = myCalendarTwo.book(6,11);
        res = myCalendarTwo.book(13,18);
        res = myCalendarTwo.book(38,43);
        res = myCalendarTwo.book(49,50);
        res = myCalendarTwo.book(6,15);
        res = myCalendarTwo.book(5,13);
        res = myCalendarTwo.book(35,42);
        res = myCalendarTwo.book(19,24);
        res = myCalendarTwo.book(46,50);
        res = myCalendarTwo.book(39,44);
        res = myCalendarTwo.book(28,36);
        res = myCalendarTwo.book(28,37);
        res = myCalendarTwo.book(20,29);
        res = myCalendarTwo.book(41,49);
        res = myCalendarTwo.book(11,19);
        res = myCalendarTwo.book(41,46);
        res = myCalendarTwo.book(28,37);
        res = myCalendarTwo.book(17,23);
        res = myCalendarTwo.book(22,31);
        res = myCalendarTwo.book(4,10);
        res = myCalendarTwo.book(31,40);
        res = myCalendarTwo.book(4,12);
        res = myCalendarTwo.book(19,26);
    }
}
