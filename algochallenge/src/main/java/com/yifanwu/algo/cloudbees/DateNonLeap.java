package com.yifanwu.algo.cloudbees;

import java.io.Serializable;
import java.util.Objects;

/**
 * Immutable and thread-safe.
 * Not considering any leap year. It has a limited support range.
 * Created by WYF on 6/30/2017.
 */
public class DateNonLeap implements DateInterface, Comparable<DateNonLeap>, Serializable {

    private static final long serialVersionUID = 8329763542007256036L;
    /**
     * min year supported
     */
    public static final int MIN_YEAR = -9999;
    /**
     * min date supported
     */
    public static final DateNonLeap MIN_DATE = new DateNonLeap(MIN_YEAR, 1, 1);
    /**
     * max year supported
     */
    public static final int MAX_YEAR = 999999;
    public static final DateNonLeap MAX_DATE = new DateNonLeap(MAX_YEAR, 12, 31);
    /**
     * total number of days for a non-leap year
     */
    public static final int TOTAL_DAYS_ONE_YEAR = 365;
    // index is the month number; value is the first day of that month to Jan 01
    private static final int[] DAYS_UPTO_MONTH = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private final int year;
    // consider using byte or short for memory saving; consider using enum
    private final int month;
    // consider using byte or short for memory saving;
    private final int day;

    public DateNonLeap(int year, int month, int day) {
        validate(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public DateInterface addDays(int days) {
        long daysToMin = daysToMinDate();
        daysToMin += days;
        if (daysToMin > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Date out of support.");
        }
        return getDatefromDaysToMinDate((int)daysToMin);
    }

    /**
     * calculate days between thatDate to current date
     * negative if thatDate is after the this date object
     * @param thatDate
     * @return
     */
    public long daysFrom(DateNonLeap thatDate) {
        return this.daysToMinDate() - thatDate.daysToMinDate();
    }

    @Override
    public int compareTo(DateNonLeap o) {
        return Math.toIntExact(this.daysFrom(o));
    }

    // using long in the hope that future supports wider range of years
    protected long daysToMinDate() {
        long totalDays = (long)TOTAL_DAYS_ONE_YEAR * (year - MIN_YEAR);
        totalDays += DAYS_UPTO_MONTH[month];
        totalDays += day - 1;
        return totalDays;
    }

    private static DateNonLeap getDatefromDaysToMinDate(int daysToMinDate) {
        if (daysToMinDate < 0) {
            throw new IllegalArgumentException("Date Earlier than supported");
        }
        int year = MIN_YEAR + daysToMinDate / TOTAL_DAYS_ONE_YEAR;
        int daysLeft = daysToMinDate % TOTAL_DAYS_ONE_YEAR;

        int month = 0;
        int day = 0;
        for (int i = DAYS_UPTO_MONTH.length - 1; i > 0; i--) {
            if (daysLeft >= DAYS_UPTO_MONTH[i]) {
                month = i;
                day = daysLeft - DAYS_UPTO_MONTH[i] + 1;
                break;
            }
        }
        return new DateNonLeap(year, month, day);
    }

    //validate it is in the supported range.
    private void validate(int year, int month, int day) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Year out of range supported.");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month out of range.");
        }
        if (day < 1) {
            throw new IllegalArgumentException("Day out of range.");
        }
        switch (month) {
            case 2:
                if (day > 28) {
                    throw new IllegalArgumentException("Day out of range.");
                }
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day > 31) {
                    throw new IllegalArgumentException("Day out of range.");
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 30) {
                    throw new IllegalArgumentException("Day out of range.");
                }
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateNonLeap)) return false;
        DateNonLeap that = (DateNonLeap) o;
        return year == that.year &&
                month == that.month &&
                day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateNonLeap{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append('}');
        return sb.toString();
    }
}
