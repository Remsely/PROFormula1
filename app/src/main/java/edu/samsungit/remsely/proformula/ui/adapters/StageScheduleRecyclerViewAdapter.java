package edu.samsungit.remsely.proformula.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;
import edu.samsungit.remsely.proformula.databinding.StageScheduleRecyclerViewItemBinding;

public class StageScheduleRecyclerViewAdapter extends RecyclerView.Adapter<StageScheduleRecyclerViewAdapter.ViewHolder> {

    private List<StageScheduleDataModel> events = Collections.emptyList();

    public StageScheduleRecyclerViewAdapter(){ }

    public void setEvents(List<StageScheduleDataModel> events){
        this.events = events;
        notifyItemRangeChanged(0, events.size());
    }

    @NonNull
    @Override
    public StageScheduleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StageScheduleRecyclerViewItemBinding binding = StageScheduleRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private StageScheduleRecyclerViewItemBinding binding;

        public ViewHolder(StageScheduleRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(StageScheduleDataModel data){
            binding.soonStageScheduleName.setText(data.getEvent() + " - ");
            binding.soonStageScheduleDate.setText(data.getDate());
        }
    }
}
