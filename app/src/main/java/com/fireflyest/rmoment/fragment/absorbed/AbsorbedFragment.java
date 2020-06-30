package com.fireflyest.rmoment.fragment.absorbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.util.CalendarUtil;
import com.fireflyest.rmoment.view.CircleBarView;

public class AbsorbedFragment extends Fragment {

    private AbsorbedViewModel mViewModel;
    private View root;
    private CircleBarView circleBar;
    private ProgressBar progressBar;
    private TextView day;
    private TextView week;
    private String weekDay[] = {"MON","TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private String dayText = "%d月%d日";

    public static AbsorbedFragment newInstance() {
        return new AbsorbedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_absorbed, container, false);

        circleBar = (CircleBarView)root.findViewById(R.id.absorbed_study_progress);
        circleBar.setProgressNum(60, 1000);
        progressBar = (ProgressBar)root.findViewById(R.id.absorbed_progress);
        progressBar.setProgress(80);

        day = (TextView)root.findViewById(R.id.absorbed_day);
        day.setText(String.format(dayText, CalendarUtil.getMonth(), CalendarUtil.getDay()));
        week = (TextView)root.findViewById(R.id.absorbed_week);
        int nowDayOfWeek = CalendarUtil.getDayOfWeek()-1;
        if(nowDayOfWeek < 0) nowDayOfWeek += 7;
        week.setText(weekDay[nowDayOfWeek]);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AbsorbedViewModel.class);
        // TODO: Use the ViewModel
    }


}
