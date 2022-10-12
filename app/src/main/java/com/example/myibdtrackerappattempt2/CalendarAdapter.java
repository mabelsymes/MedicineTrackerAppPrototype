package com.example.myibdtrackerappattempt2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private static final String TAG = "CalendarAdapter";

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, LocalDate selectedDate) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.selectedDate = selectedDate;
    }

    private final ArrayList<String> daysOfMonth;
    private Context calAdapContext;
    private final OnItemListener onItemListener;
    private final LocalDate selectedDate;

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.height = (int)(parent.getHeight()*0.24);
        layoutParams.height = (int)(220);
        calAdapContext = parent.getContext();
        return new CalendarViewHolder(view, onItemListener, daysOfMonth, selectedDate);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        String day = daysOfMonth.get(position);
        Boolean yearView = false;
        int intDay = 0;
        if (!day.equals("")) {
            intDay = Integer.valueOf(day);
        }
        if (intDay > 100){
            Log.d(TAG, "onBindViewHolder: yearView is true");
            yearView = true;
            Log.d(TAG, "onBindViewHolder: intDay is " + intDay);
            holder.dayOfMonth.setText(String.valueOf(intDay-100));
        } else {
            holder.dayOfMonth.setText(day);
        }

        final Boolean finalYearView = yearView;

        // SETTING THE COLOUR OF BUTTONS
        if (!daysOfMonth.get(position).equals("")){
            String[] date = selectedDate.toString().split("-");
            int month = 1;
            if (yearView){
                month = 1;
            } else {
                month = Integer.valueOf(date[1]);
            }
            int year = Integer.valueOf(date[0]);

            Log.d(TAG, "onBindViewHolder: Month is " + month);
            Log.d(TAG, "onBindViewHolder: Year is " + year);
            Log.d(TAG, "onBindViewHolder: Getting the day");
            Calendar_cell currentDay = Utils.getInstance(calAdapContext).getDay(intDay, month, year);
            Log.d(TAG, "onBindViewHolder: Red value is " + currentDay.getRed());

            if (currentDay.getRed()) {
                holder.redButton.setVisibility(View.VISIBLE);
            } else {
                holder.redButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getOrange()){
                holder.orangeButton.setVisibility(View.VISIBLE);
            } else {
                holder.orangeButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getYellow()){
                holder.yellowButton.setVisibility(View.VISIBLE);
            } else {
                holder.yellowButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getGreen()){
                holder.greenButton.setVisibility(View.VISIBLE);
            } else {
                holder.greenButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getBlue()){
                holder.blueButton.setVisibility(View.VISIBLE);
            } else {
                holder.blueButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getPurple()){
                holder.purpleButton.setVisibility(View.VISIBLE);
            } else {
                holder.purpleButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getPink()){
                holder.pinkButton.setVisibility(View.VISIBLE);
            } else {
                holder.pinkButton.setVisibility(View.INVISIBLE);
            }
            if (currentDay.getWhite()){
                holder.whiteButton.setVisibility(View.VISIBLE);
            } else {
                holder.whiteButton.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.redButton.setVisibility(View.INVISIBLE);
            holder.orangeButton.setVisibility(View.INVISIBLE);
            holder.yellowButton.setVisibility(View.INVISIBLE);
            holder.greenButton.setVisibility(View.INVISIBLE);
            holder.blueButton.setVisibility(View.INVISIBLE);
            holder.purpleButton.setVisibility(View.INVISIBLE);
            holder.pinkButton.setVisibility(View.INVISIBLE);
            holder.whiteButton.setVisibility(View.INVISIBLE);
        }

        // SETTING VISIBILTY OF BUTTONS
        ArrayList<String> btnsToShow = new ArrayList<>();
        if (calAdapContext instanceof MainActivity) {
            btnsToShow = ((MainActivity)calAdapContext).selectedCheckButtonsToShow();
        }
        for (String s: btnsToShow){
            Log.d(TAG, "onBindViewHolder: This is selected: " + s);
        }
        Log.d(TAG, "onBindViewHolder: Size: " + btnsToShow.size());
        if (btnsToShow.size() != 0){
            if (!btnsToShow.contains("Red")) {
                holder.redButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Orange")) {
                holder.orangeButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Yellow")) {
                holder.yellowButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Green")) {
                holder.greenButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Blue")) {
                holder.blueButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Purple")) {
                holder.purpleButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("Pink")) {
                holder.pinkButton.setVisibility(View.INVISIBLE);
            }
            if (!btnsToShow.contains("White")) {
                holder.whiteButton.setVisibility(View.INVISIBLE);
            }
        }

        // IF DAY IS PRESSED
        holder.cellDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getContext() instanceof MainActivity) {
                    if (!finalYearView) {
                        ArrayList<String> checkedBoxes = ((MainActivity) view.getContext()).selectedRadioButtons();
                        if (checkedBoxes != null) {

                            String[] date = selectedDate.toString().split("-");
                            int month = Integer.valueOf(date[1]);
                            int year = Integer.valueOf(date[0]);
                            Calendar_cell currentDay = Utils.getInstance(view.getContext()).getDay(Integer.valueOf(daysOfMonth.get(position)), month, year);

                            if (checkedBoxes.contains("Red")) {
                                Boolean red = currentDay.getRed();
                                currentDay.setRed(!red);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Orange")) {
                                Boolean orange = currentDay.getOrange();
                                currentDay.setOrange(!orange);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Yellow")) {
                                Boolean yellow = currentDay.getYellow();
                                currentDay.setYellow(!yellow);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Green")) {
                                Log.d(TAG, "onClick: CheckedBoxes contains Green");
                                Boolean green = currentDay.getGreen();
                                currentDay.setGreen(!green);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Blue")) {
                                Log.d(TAG, "onClick: CheckedBoxes contains Blue");
                                Boolean blue = currentDay.getBlue();
                                currentDay.setBlue(!blue);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Purple")) {
                                Log.d(TAG, "onClick: CheckedBoxes contains Purple");
                                Boolean purple = currentDay.getPurple();
                                currentDay.setPurple(!purple);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("Pink")) {
                                Log.d(TAG, "onClick: CheckedBoxes contains Pink");
                                Boolean pink = currentDay.getPink();
                                currentDay.setPink(!pink);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                            if (checkedBoxes.contains("White")) {
                                Log.d(TAG, "onClick: CheckedBoxes contains White");
                                Boolean white = currentDay.getWhite();
                                currentDay.setWhite(!white);
                                Utils.getInstance(view.getContext()).editDay(currentDay);
                                notifyItemChanged(position);
                            }
                        }
                    }

                }
            }
        });
        

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button redButton, orangeButton;
        private RelativeLayout colouredRecRelLayout;
        private TextView cellDayText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            redButton = itemView.findViewById(R.id.redButton);
            orangeButton = itemView.findViewById(R.id.orangeButton);
            colouredRecRelLayout = itemView.findViewById(R.id.colouredRecRelLayout);
            cellDayText = itemView.findViewById(R.id.cellDayText);
        }
    }



    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String text, String dayText, LocalDate selectedDate);
    }
}
