package edu.samsungit.remsely.proformula.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.RaceResultsRecyclerViewItemBinding;

public class RaceResultsRecyclerViewAdapter extends RecyclerView.Adapter<RaceResultsRecyclerViewAdapter.ViewHolder> {
    private List<RaceResultsDataModel> raceResults;

    public RaceResultsRecyclerViewAdapter(List<RaceResultsDataModel> raceResults){
        this.raceResults = raceResults;
    }

    @NonNull
    @Override
    public RaceResultsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RaceResultsRecyclerViewItemBinding binding = RaceResultsRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceResultsRecyclerViewAdapter.ViewHolder holder, int position) {
        RaceResultsDataModel raceResultsDataModel = raceResults.get(position);
        holder.binding.stageResultsPosition.setText(String.valueOf(raceResultsDataModel.getP()));
        switch (raceResultsDataModel.getP()){
            case 1:
                holder.binding.stageResultsPosition.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gold));
                break;
            case 2:
                holder.binding.stageResultsPosition.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.silver));
                break;
            case 3:
                holder.binding.stageResultsPosition.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.bronze));
                break;
        }
        holder.binding.stageResultsPilotName.setText(raceResultsDataModel.getPilot());
        holder.binding.stageResultsTeamName.setText(raceResultsDataModel.getTeam());
        holder.binding.stageResultsTime.setText(raceResultsDataModel.getTime());
        holder.binding.stageResultsPoints.setText(raceResultsDataModel.getPoints());
        Glide.with(holder.itemView.getContext()).load(raceResultsDataModel.getPilotFlag()).into(holder.binding.stageResultsPilotFlag);
        Glide.with(holder.itemView.getContext()).load(raceResultsDataModel.getTeamLogo()).into(holder.binding.stageResultsTeamLogo);
    }

    @Override
    public int getItemCount() {
        return raceResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private RaceResultsRecyclerViewItemBinding binding;

        public ViewHolder(RaceResultsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
