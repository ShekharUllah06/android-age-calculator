package com.dev_station.shekhar.agecalculator;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button3 = (Button)findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                DialogFragment dialogfragment = new DatePickerDialogTheme3();

                dialogfragment.show(getFragmentManager(), "Theme 3");


            }
        });


    }


    public static class DatePickerDialogTheme3 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_DARK,this,year,month,day);

            return  datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){

            TextView textview = (TextView)getActivity().findViewById(R.id.textView1);

            //textview.setText(day + ":" + (month+1) + ":" + year);
            textview.setText(calculateAge(day,month+1,year)+"\n");
            //textview.append(day + ":" + (month+1) + ":" + year);

        }
        public static String calculateAge(int mDay, int mMonth, int mYear) {
            Calendar c = Calendar.getInstance();
            int tYear = c.get(Calendar.YEAR);
            int tMonth = c.get(Calendar.MONTH)+1;
            int tDay = c.get(Calendar.DAY_OF_MONTH);

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
            String age = "Age: " + mYearDiff + " Years " + mMonDiff + " Months " + mDayDiff+ " Days";
            return age;
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




}
