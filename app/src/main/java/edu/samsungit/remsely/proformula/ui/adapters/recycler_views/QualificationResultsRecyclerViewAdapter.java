package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.data.models.QualificationResultsDataModel;
import edu.samsungit.remsely.proformula.databinding.RaceAgainstTimeRecyclerViewItemBinding;

public class QualificationResultsRecyclerViewAdapter extends
        RecyclerView.Adapter<QualificationResultsRecyclerViewAdapter.ViewHolder>{

    private List<QualificationResultsDataModel> qualificationResults = Collections.emptyList();

    public QualificationResultsRecyclerViewAdapter(){}

    public void setQualificationResults(List<QualificationResultsDataModel> qualificationResults) {
        this.qualificationResults = qualificationResults;
        notifyItemRangeChanged(0, qualificationResults.size());
    }

    @NonNull
    @Override
    public QualificationResultsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RaceAgainstTimeRecyclerViewItemBinding binding = RaceAgainstTimeRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QualificationResultsRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QualificationResultsRecyclerViewAdapter.ViewHolder holder, int position) {
        QualificationResultsDataModel qualificationResultsItem = qualificationResults.get(position);
        holder.p.setText(String.valueOf(qualificationResultsItem.getP()));
        switch (qualificationResultsItem.getP()){
            case 1:
                holder.p.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gold));
                break;
            case 2:
                holder.p.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.silver));
                break;
            case 3:
                holder.p.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.bronze));
                break;
            default:
                holder.p.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                break;
        }

        holder.pilotName.setText(qualificationResultsItem.getPilot());
        holder.teamName.setText(qualificationResultsItem.getTeam());
        holder.time.setText(qualificationResultsItem.getTime());

        Glide.with(holder.itemView.getContext()).load(qualificationResultsItem.getPilotFlag()).into(holder.pilotFlag);
        Glide.with(holder.itemView.getContext()).load(qualificationResultsItem.getTeamLogo()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return qualificationResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView p;
        private final TextView pilotName;
        private final TextView teamName;
        private final TextView time;
        private final ImageView pilotFlag;
        private final ImageView teamLogo;

        public ViewHolder(RaceAgainstTimeRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            p = binding.raceAgainstTimeNumber;
            pilotName = binding.raceAgainstTimePilotName;
            pilotFlag = binding.raceAgainstTimePilotFlag;
            teamName = binding.raceAgainstTimeTeamName;
            teamLogo = binding.raceAgainstTimeTeamLogo;
            time = binding.raceAgainstTimeTime;
        }
    }
}
