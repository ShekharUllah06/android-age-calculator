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

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private TextView txtResult;
    private TextView txtMonthDays;
    private TextView txtWeekDays;
    private TextView txtTotalDays;
    private Button startPickDate;
    private Button endPickDate;
    private Button btnCalculate;
    private Calendar startDate;
    private Calendar endDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    /**
     *
     * @param savedInstanceState
     */
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
        txtMonthDays = (TextView) findViewById(R.id.txtMonthDay);
        txtWeekDays = (TextView) findViewById(R.id.txtWeekDays);
        txtTotalDays = (TextView) findViewById(R.id.txtTotalDays);
        btnCalculate = (Button) findViewById(R.id.btnCalculateAge);
        /* add a click listener to the button   */
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar startDate1=Calendar.getInstance();

                startDate1.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH)+1, startDate.get(Calendar.DAY_OF_MONTH));
                Calendar endDate1=Calendar.getInstance();
                endDate1.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH)+1, endDate.get(Calendar.DAY_OF_MONTH));

                DateCalculator dateCaculator=DateCalculator.calculateAge(startDate1,endDate1);
                String age = "Age: " + dateCaculator.getYear() + " Years " + dateCaculator.getMonth() + " Months " + dateCaculator.getDay()+ " Days";
                int num_weeks = (int) dateCaculator.getTotalDay()/7;
                int num_months = dateCaculator.getYear()*12 + dateCaculator.getMonth();
                System.out.println(dateCaculator.getYear());
                txtResult.setText(age);
                txtTotalDays.setText(""+dateCaculator.getTotalDay()+" Days");
                txtWeekDays.setText(""+num_weeks+" Weeks " + dateCaculator.getTotalDay()%7 + " Days");
                txtMonthDays.setText(""+num_months+" Months "+dateCaculator.getDay()+" Days ");

                //showDateDialog(endDateDisplay, endDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(startDateDisplay, startDate);
        updateDisplay(endDateDisplay, endDate);
    }

    /**
     *  update the date at ui text view
     * @param dateDisplay text view where the date will be shown
     * @param date selected date
     */
    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    /**
     * display the date dialog
     * @param dateDisplay
     * @param date
     */
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
