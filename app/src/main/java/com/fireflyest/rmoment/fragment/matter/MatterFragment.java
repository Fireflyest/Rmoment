package com.fireflyest.rmoment.fragment.matter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;

public class MatterFragment extends Fragment {

    private MatterViewModel mViewModel;
    private List<MatterDay> matterDay = new ArrayList<>();
    private List<MatterItem> matterItem = new ArrayList<>();

    public static MatterFragment newInstance() {
        return new MatterFragment();
    }

    private View root;

    private RecyclerView dayRecyclerView;
    private RecyclerView itemRecyclerView;
    private ScrollView scrollView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_matter, container, false);

        int nowadays = CalendarUtil.getDay();
        int nowDayOfWeek = CalendarUtil.getDayOfWeek()-1;
        for (int i = -3; i < 8; i++) {
            int dayOfWeek = nowDayOfWeek + i;
            int nowDay = nowadays+i;
            if(dayOfWeek < 0){
                dayOfWeek += 7;
            }else if(dayOfWeek >= 7){
                dayOfWeek -= 7;
            }
            if(nowDay > CalendarUtil.getMaxDay()){
                nowDay -= CalendarUtil.getMaxDay();
            }
            matterDay.add(new MatterDay(nowDay, dayOfWeek));
        }

        dayRecyclerView = (RecyclerView) root.findViewById((R.id.matter_week_days));
        LinearLayoutManager manager1 = new LinearLayoutManager(root.getContext());
        dayRecyclerView.setLayoutManager(manager1);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        MatterDayAdapter matterDayAdapter = new MatterDayAdapter(matterDay);
        dayRecyclerView.setAdapter(matterDayAdapter);



        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505数学课s在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋505", 649444, 2018));
        matterItem.add(new MatterItem("Test", "数学课", "数学课在二栋502", 649444, 2018));

        itemRecyclerView = (RecyclerView) root.findViewById((R.id.matter_items));
        LinearLayoutManager manager2 = new LinearLayoutManager(root.getContext());
        itemRecyclerView.setLayoutManager(manager2);
        MatterItemAdapter matterItemAdapter = new MatterItemAdapter(matterItem);
        itemRecyclerView.setAdapter(matterItemAdapter);


        scrollView = (ScrollView)root.findViewById(R.id.matter_scroll);
        itemRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean hide = false;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                hide = dy > 0;
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollView.fullScroll(hide ? ScrollView.FOCUS_DOWN : ScrollView.FOCUS_UP);
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


}
