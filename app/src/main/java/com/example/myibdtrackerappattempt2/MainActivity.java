package com.example.myibdtrackerappattempt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private static final String TAG = "MainActivity";

    private TextView monthYearText;
    private RecyclerView calendarRecView;
    private LocalDate selectedDate;
    private Button btnPrev, btnNext, updateBtn;
    private CheckBox redCheckBox, orangeCheckBox, yellowCheckBox;
    private CheckBox showRedCheckBox, showOrangeCheckBox, showYellowCheckBox;
    private EditText redMeaningEditTxt, orangeMeaningEditTxt, yellowMeaningEditTxt;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        Log.d(TAG, "onCreate: Selected date is " + selectedDate);
        setMonthView();
    }

    // WHERE UTILS IS CALLED SO DAYS CAN BE ADDED
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        for (String d: daysInMonth) {
            if (!d.equals("")) {
                int day = Integer.valueOf(d);
                String[] date = selectedDate.toString().split("-");
                int month = Integer.valueOf(date[1]);
                int year = Integer.valueOf(date[0]);
                Log.d(TAG, "setMonthView: Yoooo The date is " + day + month + year);
                Calendar_cell calendar_cell = new Calendar_cell();
                calendar_cell.setDay(day);
                calendar_cell.setMonth(month);
                calendar_cell.setYear(year);
                Log.d(TAG, "setMonthView: About to get Utils");
                if (Utils.getInstance(this).addDay(calendar_cell).equals("old")){
                    break;
                }
            }
        }

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, selectedDate);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecView.setLayoutManager(layoutManager);
        calendarRecView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i=1; i<=42; i++){
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecView = findViewById(R.id.calendarRecView);
        monthYearText = findViewById(R.id.monthYearTV);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        redCheckBox = findViewById(R.id.redCheckBox);
        redMeaningEditTxt = findViewById(R.id.redMeaningEditTxt);
        updateBtn = findViewById(R.id.updateBtn);
        orangeCheckBox = findViewById(R.id.orangeCheckBox);
        orangeMeaningEditTxt = findViewById(R.id.orangeMeaningEditTxt);
        yellowCheckBox = findViewById(R.id.yellowCheckBox);
        yellowMeaningEditTxt = findViewById(R.id.yellowMeaningEditTxt);

        showRedCheckBox = findViewById(R.id.showRedCheckBox);
        showOrangeCheckBox = findViewById(R.id.showOrangeCheckBox);
        showYellowCheckBox = findViewById(R.id.showYellowCheckBox);

        redMeaningEditTxt.setText(Utils.getInstance(this).getKey().get(0));
        orangeMeaningEditTxt.setText(Utils.getInstance(this).getKey().get(1));
        yellowMeaningEditTxt.setText(Utils.getInstance(this).getKey().get(2));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getInstance(context).updateKey(0,redMeaningEditTxt.getText().toString());
                Utils.getInstance(context).updateKey(1,orangeMeaningEditTxt.getText().toString());
                Utils.getInstance(context).updateKey(2,yellowMeaningEditTxt.getText().toString());
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousMonthAction(view);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextMonthAction(view);
            }
        });
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText, String day, LocalDate selectedDate) {
        if (dayText.equals("")){

        } else {
//            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<String> selectedRadioButtons(){
        ArrayList<String> toReturn = new ArrayList<>();
        if (redCheckBox.isChecked()){
           toReturn.add("Red");
        }
        if (orangeCheckBox.isChecked()){
            toReturn.add("Orange");
        }
        if (yellowCheckBox.isChecked()){
            toReturn.add("Yellow");
        }
        return toReturn;
    }

    public ArrayList<String> selectedCheckButtonsToShow(){
        ArrayList<String> toReturn = new ArrayList<>();
        if (showRedCheckBox.isChecked()){
            toReturn.add("Red");
        }
        if (showOrangeCheckBox.isChecked()){
            toReturn.add("Orange");
        }
        if (showYellowCheckBox.isChecked()){
            toReturn.add("Yellow");
        }
        return toReturn;
    }
}