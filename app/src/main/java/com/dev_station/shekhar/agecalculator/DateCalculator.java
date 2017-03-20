package com.dev_station.shekhar.agecalculator;

import java.util.Calendar;

/**
 * Created by Shekhar on 3/17/2017.
 */

public final class DateCalculator {
    private final int year;
    private final int month;
    private final int day;
    private final int totalDay;

    /**
     * Construct DateCalculator Class
     * @param year
     * @param month
     * @param day
     * @param totalDay
     */
    public DateCalculator(int year, int month, int day, int totalDay) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalDay = totalDay;
    }

    /**
     *
     * @return years
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @return months
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @return days
     */
    public int getDay() {
        return day;
    }

    /**
     *
     * @return total days
     */
    public int getTotalDay() {
        return totalDay;
    }

    /**
     * Calculate days for given month and year
     * @param tMonth
     * @param tYear
     * @return days of a given month of given year
     */
    public static int MonthsToDays(int tMonth, int tYear) {
        if (tMonth == 1 || tMonth == 3 || tMonth == 5 || tMonth == 7
                || tMonth == 8 || tMonth == 10 || tMonth == 12) {
            return 31;
        } else if (tMonth == 2) {
            if (tYear % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }

    /**
     * Calculate Age in years, months and days and total days
     * @param startDate
     * @param endDate
     * @return DateCalculator
     */
    public static DateCalculator calculateAge(Calendar startDate, Calendar endDate) {
        Calendar mycal1 = (Calendar) startDate.clone();
        Calendar mycal2 = (Calendar) endDate.clone();

        long d1 = mycal1.getTimeInMillis();
        long d2 = mycal2.getTimeInMillis();

        if (d1 > d2) {
            mycal1 = (Calendar) endDate.clone();
            mycal2 = (Calendar) startDate.clone();
        }

        int mDay = mycal1.get(Calendar.DAY_OF_MONTH);
        int mMonth = mycal1.get(Calendar.MONTH);
        int mYear = mycal1.get(Calendar.YEAR);

        int tDay = mycal2.get(Calendar.DAY_OF_MONTH);
        int tMonth = mycal2.get(Calendar.MONTH);
        int tYear = mycal2.get(Calendar.YEAR);

        int totalDays=gregorianDays(tYear,tMonth,tDay) - gregorianDays(mYear,mMonth,mDay);
        int mYearDiff = tYear - mYear;
        int mMonDiff = tMonth - mMonth;

        if (mMonDiff < 0) {
            mYearDiff = mYearDiff - 1;
            mMonDiff = mMonDiff + 12;
        }

        int mDayDiff = tDay - mDay;
        if (mDayDiff < 0) {
            if (mMonDiff > 0) {
                mMonDiff = mMonDiff - 1;
                mDayDiff = mDayDiff + MonthsToDays(tMonth - 1, tYear);
            } else {
                mYearDiff = mYearDiff - 1;
                mMonDiff = 11;
                mDayDiff = mDayDiff + MonthsToDays(tMonth - 1, tYear);;
            }

        }
        //String age = "Age: " + mYearDiff + " Years " + mMonDiff + " Months " + mDayDiff + " Days";
        //System.out.println(age);
        return new DateCalculator(mYearDiff, mMonDiff, mDayDiff, totalDays);
    }

    private static int gregorianDays(int year, int month, int day) {
        month = (month + 9) % 12;
        year = year - month / 10;
        return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 1);

    }


}
