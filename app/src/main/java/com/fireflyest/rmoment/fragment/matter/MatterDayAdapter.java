package com.fireflyest.rmoment.fragment.matter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.util.CalendarUtil;

import java.util.List;

public class MatterDayAdapter extends RecyclerView.Adapter<MatterDayAdapter.ViewHolder> {


    private List<MatterDay>days;
    private Button lastCheck;
    private Button todayCheck;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView dayOfWeek;
        Button day;

        public ViewHolder(@NonNull View view) {
            super(view);
            dayOfWeek = (TextView) view.findViewById(R.id.matter_day_of_week);
            day = (Button) view.findViewById(R.id.matter_day_button);
        }
    }

    public MatterDayAdapter(List<MatterDay> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public MatterDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_matter_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatterDayAdapter.ViewHolder holder, int position) {
        String[] weekDay = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        MatterDay matterDay = days.get(position);
        holder.dayOfWeek.setText(weekDay[matterDay.getDayOfWeek()]);
        holder.day.setText(String.valueOf(matterDay.getDay()));
        final boolean today = matterDay.getDay() == CalendarUtil.getDay();
        holder.day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!v.equals(lastCheck)){
                    setChecked((Button)v, todayCheck.equals(lastCheck));
                }
            }
        });
        if(today){
            setChecked(holder.day, false);
            todayCheck = holder.day;
        }
    }


    public void setChecked(Button button, boolean today){
        button.setBackgroundResource(R.drawable.circle_primary);
        button.setTextColor(button.getResources().getColor(R.color.foreground));
        if(lastCheck != null){
            lastCheck.setBackgroundResource(R.drawable.circle_foreground);
            if(today){
                lastCheck.setTextColor(lastCheck.getResources().getColor(R.color.colorPrimaryDark));
            }else {
                lastCheck.setTextColor(lastCheck.getResources().getColor(R.color.colorText));
            }
        }
        lastCheck = button;
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}
