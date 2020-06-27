package com.fireflyest.rmoment.fragment.matter;

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

public class MatterItemAdapter extends RecyclerView.Adapter<MatterItemAdapter.ViewHolder> {


    private List<MatterItem>items;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView moment;
        TextView content;
        ConstraintLayout card;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.matter_item_title);
            moment = (TextView) view.findViewById(R.id.matter_item_moment);
            content = (TextView) view.findViewById(R.id.matter_item_content);
            card = (ConstraintLayout) view.findViewById(R.id.matter_item_card);
        }
    }

    public MatterItemAdapter(List<MatterItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MatterItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_matter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatterItemAdapter.ViewHolder holder, int position) {
        MatterItem matterItem = items.get(position);
        holder.title.setText(matterItem.getTitle());
        holder.moment.setText(CalendarUtil.getTime(matterItem.getMoment()));
        holder.content.setText(matterItem.getContent());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
