package com.example.myibdtrackerappattempt2;

import android.content.Context;
import android.graphics.Color;
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
        return new CalendarViewHolder(view, onItemListener, daysOfMonth, selectedDate);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        if (!daysOfMonth.get(position).equals("")){
            String[] date = selectedDate.toString().split("-");
            int month = Integer.valueOf(date[1]);
            int year = Integer.valueOf(date[0]);
            Calendar_cell currentDay = Utils.getInstance(calAdapContext).getDay(Integer.valueOf(daysOfMonth.get(position)), month, year);
            if (currentDay.getRed()) {
                holder.redButton.setBackgroundColor(Color.RED);
            } else {
                holder.redButton.setBackgroundColor(Color.WHITE);
            }
        } else {
            holder.redButton.setBackgroundColor(Color.WHITE);
        }
        holder.cellDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] date = selectedDate.toString().split("-");
                int month = Integer.valueOf(date[1]);
                int year = Integer.valueOf(date[0]);
                Calendar_cell currentDay = Utils.getInstance(view.getContext()).getDay(Integer.valueOf(daysOfMonth.get(position)), month, year);
                Log.d(TAG, "onClick: Red is " + currentDay.getRed());
                if (currentDay.getRed() == false){
                    currentDay.setRed(true);
                    } else {
                    Log.d(TAG, "onClick: Changing to false");
                    currentDay.setRed(false);
                    }
                Log.d(TAG, "onClick: Red is " + currentDay.getRed());
                Utils.getInstance(view.getContext()).editDay(currentDay);
                notifyItemChanged(position);
            }
        });
        

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button redButton;
        private RelativeLayout colouredRecRelLayout;
        private TextView cellDayText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            redButton = itemView.findViewById(R.id.redButton);
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
