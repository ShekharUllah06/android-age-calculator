package com.dev_station.shekhar.agecalculator;

import java.util.Calendar;

/**
 * Created by Shekhar on 3/17/2017.
 */

public final class DateCalculator {
    private final int year;
    private  final int month;
    private final int day;
    private final int totalDay;

    public DateCalculator(int year, int month, int day, int totalDay) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalDay = totalDay;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public static DateCalculator calculateAge(Calendar fromDate,Calendar toDate) {
        int mDay=fromDate.get(Calendar.DAY_OF_MONTH);
        int mMonth=fromDate.get(Calendar.MONTH);
        int mYear=fromDate.get(Calendar.YEAR);
        //Calendar c = Calendar.getInstance();
        int tYear = toDate.get(Calendar.YEAR);
        int tMonth = toDate.get(Calendar.MONTH);
        int tDay = toDate.get(Calendar.DAY_OF_MONTH);

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
        //String age = "Age: " + mYearDiff + " Years " + mMonDiff + " Months " + mDayDiff+ " Days";
        return new DateCalculator(mYearDiff,mMonDiff,mDayDiff,100);
    }
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
}
