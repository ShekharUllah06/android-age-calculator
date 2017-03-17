package com.dev_station.shekhar.agecalculator;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import android.app.DatePickerDialog.OnDateSetListener;

public class MainActivity extends AppCompatActivity {

    /*Button button3;
    Button btnToDate;
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

        btnToDate=(Button)findViewById(R.id.btnToDate);
        btnToDate.setOnClickListener(new View.OnClickListener() {

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



*/


    /////////////////////////////////

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private TextView txtResult;
    private Button startPickDate;
    private Button endPickDate;
    private Button btnCalculate;
    private Calendar startDate;
    private Calendar endDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  capture our View elements for the start date function   */
        startDateDisplay = (TextView) findViewById(R.id.startDateDisplay);
        startPickDate = (Button) findViewById(R.id.btnStartDate);

        /* get the current date */
        startDate = Calendar.getInstance();

        /* add a click listener to the button   */
        startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(startDateDisplay, startDate);
            }
        });

        /* capture our View elements for the end date function */
        endDateDisplay = (TextView) findViewById(R.id.endDateDisplay);
        endPickDate = (Button) findViewById(R.id.btnEndDate);

        /* get the current date */
        endDate = Calendar.getInstance();

        /* add a click listener to the button   */
        endPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(endDateDisplay, endDate);
            }
        });

        txtResult = (TextView) findViewById(R.id.txtResult);
        btnCalculate = (Button) findViewById(R.id.btnCalculateAge);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //DateCalculator dateCalculator=DateCalculator.calculateAge(6,9,1988);
                //txtResult.setText(dateCalculator.getYear());
               //startDate.get(Calendar.DATE);
                DateCalculator dateCaculator=DateCalculator.calculateAge(startDate,endDate);
                String age = "Age: " + dateCaculator.getYear() + " Years " + dateCaculator.getMonth() + " Months " + dateCaculator.getDay()+ " Days";
                System.out.println(dateCaculator.getYear());
                txtResult.setText(age);


                //showDateDialog(endDateDisplay, endDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(startDateDisplay, startDate);
        updateDisplay(endDateDisplay, endDate);
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }


    private OnDateSetListener dateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }


}
