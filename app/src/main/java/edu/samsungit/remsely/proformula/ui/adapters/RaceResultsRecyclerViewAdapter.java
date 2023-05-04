package edu.samsungit.remsely.proformula.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.data.models.RaceResultsDataModel;
import edu.samsungit.remsely.proformula.databinding.RaceResultsRecyclerViewItemBinding;

public class RaceResultsRecyclerViewAdapter extends RecyclerView.Adapter<RaceResultsRecyclerViewAdapter.ViewHolder> {
    private List<RaceResultsDataModel> raceResults = Collections.emptyList();

    public RaceResultsRecyclerViewAdapter(){ }
    public void setRaceResults(List<RaceResultsDataModel> raceResults) {
        this.raceResults = raceResults;
        notifyItemRangeChanged(0, raceResults.size());
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

        String points = raceResultsDataModel.getPoints();
        holder.binding.stageResultsPoints.setText(points);
        if (Objects.equals(points, "+26") || Objects.equals(points, "+19") || Objects.equals(points, "+16")
                || Objects.equals(points, "+13") || Objects.equals(points, "+11") || Objects.equals(points, "+9")
                || (Objects.equals(points, "+7") && raceResultsDataModel.getP() == 7)
                || (Objects.equals(points, "+5") && raceResultsDataModel.getP() == 8)
                || (Objects.equals(points, "+3") && raceResultsDataModel.getP() == 9)
                || (Objects.equals(points, "+2") && raceResultsDataModel.getP() == 10))
            holder.binding.stageResultsPoints.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.purple));

        Glide.with(holder.itemView.getContext()).load(raceResultsDataModel.getPilotFlag()).into(holder.binding.stageResultsPilotFlag);
        Glide.with(holder.itemView.getContext()).load(raceResultsDataModel.getTeamLogo()).into(holder.binding.stageResultsTeamLogo);
    }

    @Override
    public int getItemCount() {
        return raceResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final RaceResultsRecyclerViewItemBinding binding;

        public ViewHolder(RaceResultsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
