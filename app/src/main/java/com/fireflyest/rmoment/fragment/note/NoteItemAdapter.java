package com.fireflyest.rmoment.fragment.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.util.CalendarUtil;

import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {


    private List<NoteItem>items;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView moment;
        TextView content;
        ConstraintLayout card;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.note_item_title);
            moment = (TextView) view.findViewById(R.id.note_item_moment);
            content = (TextView) view.findViewById(R.id.note_item_content);
            card = (ConstraintLayout) view.findViewById(R.id.note_background);
        }
    }

    public NoteItemAdapter(List<NoteItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NoteItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteItemAdapter.ViewHolder holder, int position) {
        NoteItem noteItem = items.get(position);
        holder.title.setText(noteItem.getTitle());
        holder.moment.setText(CalendarUtil.getTime(noteItem.getMoment()));
        holder.content.setText(noteItem.getContent());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
