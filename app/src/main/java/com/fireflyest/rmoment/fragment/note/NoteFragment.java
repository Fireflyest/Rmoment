package com.fireflyest.rmoment.fragment.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    private NoteViewModel mViewModel;

    private View root;
    private ImageView imageSort;

    private RecyclerView noteRecyclerView;
    private ScrollView scrollView;

    private List<NoteItem> noteItem = new ArrayList<>();


    private boolean timeRank = true;

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_note, container, false);

        imageSort = (ImageView) root.findViewById(R.id.note_sort_rank);
        imageSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRank = !timeRank;
                imageSort.setImageResource(timeRank ? R.drawable.ic_timerank : R.drawable.ic_namerank);
                ToastUtil.showShort(root.getContext(), timeRank ? R.string.note_timeRank : R.string.note_nameRank);
            }
        });
        imageSort.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内容内容内容内容内容内容内容容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内容内容内内内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容容内容内容内容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内内内容内容内容内容内容内内容内容内容内容容容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));
        noteItem.add(new NoteItem("Test", "内内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容容容", 2018, 0));

        noteRecyclerView = (RecyclerView) root.findViewById((R.id.note_item_list));
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        noteRecyclerView.setLayoutManager(manager);
        NoteItemAdapter noteItemAdapter = new NoteItemAdapter(noteItem);
        noteRecyclerView.setAdapter(noteItemAdapter);



        scrollView = (ScrollView)root.findViewById(R.id.note_scroll);
        noteRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

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

    }


}
