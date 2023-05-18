package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.CalendarRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.data.models.CalendarItemDataModel;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class CalendarScreenRecyclerViewAdapter extends RecyclerView.Adapter<CalendarScreenRecyclerViewAdapter.ViewHolder> {
    private List<CalendarItemDataModel> calendarItems = Collections.emptyList();
    private int nextStageNumber = -1;
    private LifecycleOwner viewLifecycleOwner;

    public CalendarScreenRecyclerViewAdapter(){
    }

    public void setViewLifecycleOwner(LifecycleOwner viewLifecycleOwner){
        this.viewLifecycleOwner = viewLifecycleOwner;
    }

    public void setCalendarItems(List<CalendarItemDataModel> calendarItems) {
        this.calendarItems = calendarItems;
        notifyItemRangeChanged(0, calendarItems.size());
    }

    public void setNextStageNumber(int nextStageNumber) {
        this.nextStageNumber = nextStageNumber;
        notifyItemChanged(nextStageNumber - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CalendarRecyclerViewItemBinding binding = CalendarRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalendarItemDataModel calendarItemDataModel = calendarItems.get(position);

        calendarItemDataModel.getStageScheduleList().observe(viewLifecycleOwner, schedule -> {
            StageScheduleRecyclerViewAdapter adapter =
                    (StageScheduleRecyclerViewAdapter) holder.stageScheduleRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setEvents(schedule);
            }
        });

        holder.stagePlace.setText(calendarItemDataModel.getStageHeading().getLocation());
        holder.stageName.setText(calendarItemDataModel.getNumber() + ". " +
                calendarItemDataModel.getStageHeading().getName());

        Glide.with(holder.stageFlag.getContext())
                .load(calendarItemDataModel.getStageHeading().getFlag())
                .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                .into(holder.stageFlag);

        if (calendarItemDataModel.getNumber() - 1 == nextStageNumber){
            holder.itemLayout.setBackgroundResource(R.drawable.simple_frame_with_stroke);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(DpToPx.dpToPx(2), 0, DpToPx.dpToPx(2), 0);
            holder.line.setLayoutParams(params);
            holder.line.setMinimumHeight(DpToPx.dpToPx(4));
        }

        else {
            holder.itemLayout.setBackgroundResource(R.drawable.simple_frame);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0,0, 0);
            holder.line.setLayoutParams(params);
            holder.line.setMinimumHeight(DpToPx.dpToPx(4));
        }

    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView stageName;
        private final TextView stagePlace;
        private final ImageView stageFlag;
        private final RecyclerView stageScheduleRecyclerView;
        private final LinearLayout itemLayout;
        private final LinearLayout line;

        public ViewHolder(CalendarRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            stageName = binding.calendarStageName;
            stagePlace = binding.calendarStagePlace;
            stageFlag = binding.calendarStageFlag;
            itemLayout = binding.calendarScreenRecyclerViewItemLayout;
            line = binding.calendarStageTopLine;

            stageScheduleRecyclerView = binding.calendarScreenStageScheduleRecyclerView;
            StageScheduleRecyclerViewAdapter stageScheduleRecyclerViewAdapter = new StageScheduleRecyclerViewAdapter();
            stageScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(stageScheduleRecyclerView.getContext()));
            stageScheduleRecyclerView.setAdapter(stageScheduleRecyclerViewAdapter);
            stageScheduleRecyclerView.setItemAnimator(null);
        }
    }
}
