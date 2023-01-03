package com.example.medicineTrackerApp;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth;
    public Button redButton, orangeButton, yellowButton, greenButton, blueButton, purpleButton, pinkButton, whiteButton;
    public RelativeLayout colouredRecRelLayout;
    private final CalendarAdapter.OnItemListener onItemListener;
    public ArrayList<String> daysOfMonth;
    public LocalDate selectedDate;
    public TextView cellDayText;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<String> daysOfMonth, LocalDate selectedDate) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        redButton = itemView.findViewById(R.id.redButton);
        orangeButton = itemView.findViewById(R.id.orangeButton);
        yellowButton = itemView.findViewById(R.id.yellowButton);
        greenButton = itemView.findViewById(R.id.greenButton);
        blueButton = itemView.findViewById(R.id.blueButton);
        purpleButton = itemView.findViewById(R.id.purpleButton);
        pinkButton = itemView.findViewById(R.id.pinkButton);
        whiteButton = itemView.findViewById(R.id.whiteButton);
        colouredRecRelLayout = itemView.findViewById(R.id.colouredRecRelLayout);
        cellDayText = itemView.findViewById(R.id.cellDayText);

        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.selectedDate = selectedDate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText(), daysOfMonth.get(getAdapterPosition()), selectedDate);
    }
}
