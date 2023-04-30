package edu.samsungit.remsely.proformula.ui.calendar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.CalendarRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.ui.adapters.StageScheduleRecyclerViewAdapter;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class CalendarScreenRecyclerViewAdapter extends RecyclerView.Adapter<CalendarScreenRecyclerViewAdapter.ViewHolder> {
    private final List<CalendarItemDataModel> calendarItems;
    private final int nextStageNumber;

    public CalendarScreenRecyclerViewAdapter(List<CalendarItemDataModel> calendarItems, int nextStageNumber){
        this.calendarItems = calendarItems;
        this.nextStageNumber = nextStageNumber;
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

        RecyclerView nestedRecyclerView = holder.stageScheduleRecyclerView;
        StageScheduleRecyclerViewAdapter nestedRecyclerViewAdapter =
                new StageScheduleRecyclerViewAdapter(calendarItemDataModel.getStageScheduleList());
        nestedRecyclerView.setAdapter(nestedRecyclerViewAdapter);
        nestedRecyclerView.setLayoutManager(new LinearLayoutManager(nestedRecyclerView.getContext()));
        nestedRecyclerViewAdapter.notifyDataSetChanged();

        holder.stagePlace.setText(calendarItemDataModel.getStageHeading().getLocation());
        holder.stageName.setText(calendarItemDataModel.getNumber() + ". " +
                calendarItemDataModel.getStageHeading().getName());

        Glide.with(holder.stageFlag.getContext())
                .load(calendarItemDataModel.getStageHeading().getFlag())
                .transform(new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                .into(holder.stageFlag);

//        if (calendarItemDataModel.getNumber() - 1 == nextStageNumber){
//            holder.itemLayout.setBackgroundColor(ContextCompat.getColor(holder.itemLayout.getContext(), R.color.light_grey));
//        }
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

        public ViewHolder(CalendarRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            stageName = binding.calendarStageName;
            stagePlace = binding.calendarStagePlace;
            stageFlag = binding.calendarStageFlag;
            stageScheduleRecyclerView = binding.calendarScreenStageScheduleRecyclerView;
            itemLayout = binding.calendarScreenRecyclerViewItemLayout;
        }
    }
}
