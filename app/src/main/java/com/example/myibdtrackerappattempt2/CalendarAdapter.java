package com.example.myibdtrackerappattempt2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
        layoutParams.height = (int)(parent.getHeight() * 0.1666666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        holder.redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] date = selectedDate.toString().split("-");
                int month = Integer.valueOf(date[1]);
                int year = Integer.valueOf(date[0]);
                Calendar_cell currentDay = Utils.getInstance(calAdapContext).getDay(Integer.valueOf(daysOfMonth.get(position)), month, year);
                Log.d(TAG, "onClick: Red is " + currentDay.getRed());
                if (currentDay.getRed() == false){
                    currentDay.setRed(true);
                    Toast.makeText(v.getContext(), "Red is now true", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onClick: Changing to false");
                    currentDay.setRed(false);
                    Toast.makeText(v.getContext(), "Red is now false", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onClick: Red is " + currentDay.getRed());
                Utils.getInstance(v.getContext()).editDay(currentDay);


//                    Event event = events.get(getAdapterPosition());
//                    event.setExpanded(!event.isExpanded());
//                    notifyItemChanged(getAdapterPosition());
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button redButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            redButton = itemView.findViewById(R.id.redButton);
        }
    }



    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}
